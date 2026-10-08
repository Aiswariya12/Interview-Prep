package com.interviewprep.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username:root}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Value("${spring.datasource.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String driverClassName;

    @Bean
    @Primary
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "mysql", matchIfMissing = true)
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();

        String resolvedUrl = url;
        String resolvedUser = username;
        String resolvedPass = password;

        // Auto-convert standard cloud mysql:// URLs (e.g. Railway, Clever Cloud, Aiven, Render) to jdbc:mysql://
        if (resolvedUrl != null && resolvedUrl.startsWith("mysql://")) {
            try {
                URI uri = new URI(resolvedUrl.replace("mysql://", "http://"));
                String host = uri.getHost();
                int port = uri.getPort() == -1 ? 3306 : uri.getPort();
                String path = uri.getPath(); // /dbname
                String query = uri.getQuery();

                if (uri.getUserInfo() != null) {
                    String[] userInfo = uri.getUserInfo().split(":", 2);
                    resolvedUser = userInfo[0];
                    if (userInfo.length > 1) {
                        resolvedPass = userInfo[1];
                    }
                }

                resolvedUrl = "jdbc:mysql://" + host + ":" + port + path + (query != null ? "?" + query : "");
                logger.info("Converted cloud mysql:// URL to JDBC format: {}", resolvedUrl.replaceAll(":[^/@]+@", ":****@"));
            } catch (Exception e) {
                logger.warn("Could not parse cloud mysql:// URL as URI, prepending jdbc:: {}", e.getMessage());
                resolvedUrl = "jdbc:" + resolvedUrl;
            }
        }

        ds.setJdbcUrl(resolvedUrl);
        ds.setUsername(resolvedUser);
        ds.setPassword(resolvedPass);
        ds.setDriverClassName(driverClassName);
        ds.setMaximumPoolSize(10);
        ds.setMinimumIdle(2);
        ds.setConnectionTimeout(6000);
        ds.setIdleTimeout(600000);
        ds.setMaxLifetime(1800000);

        try (java.sql.Connection conn = ds.getConnection()) {
            logger.info("Connected to MySQL DataSource with URL: {}", resolvedUrl != null ? resolvedUrl.replaceAll(":[^/@]+@", ":****@") : "null");
            return ds;
        } catch (Exception e) {
            logger.warn("Could not connect to MySQL at {}. Activating zero-config embedded database fallback. Error: {}",
                    resolvedUrl != null ? resolvedUrl.replaceAll(":[^/@]+@", ":****@") : "null", e.getMessage());
            try {
                ds.close();
            } catch (Exception ignored) {}

            HikariDataSource fallback = new HikariDataSource();
            fallback.setJdbcUrl("jdbc:h2:file:./data/interviewprep_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE");
            fallback.setDriverClassName("org.h2.Driver");
            fallback.setUsername("sa");
            fallback.setPassword("");
            fallback.setMaximumPoolSize(10);
            fallback.setMinimumIdle(2);
            fallback.setConnectionTimeout(30000);
            logger.info("Fallback persistent H2 database activated successfully.");
            return fallback;
        }
    }
}

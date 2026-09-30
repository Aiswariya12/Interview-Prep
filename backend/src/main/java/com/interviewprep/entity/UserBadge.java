package com.interviewprep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_badges", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "badge_key"})
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"password", "hibernateLazyInitializer", "handler"})
    private User user;

    @Column(name = "badge_key", nullable = false)
    private String badgeKey;

    @Column(nullable = false)
    private String badgeName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String icon;

    @Column(nullable = false, updatable = false)
    private LocalDateTime earnedAt = LocalDateTime.now();

    public UserBadge() {}

    public UserBadge(User user, String badgeKey, String badgeName, String description, String icon) {
        this.user = user;
        this.badgeKey = badgeKey;
        this.badgeName = badgeName;
        this.description = description;
        this.icon = icon;
        this.earnedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getBadgeKey() { return badgeKey; }
    public void setBadgeKey(String badgeKey) { this.badgeKey = badgeKey; }

    public String getBadgeName() { return badgeName; }
    public void setBadgeName(String badgeName) { this.badgeName = badgeName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public LocalDateTime getEarnedAt() { return earnedAt; }
    public void setEarnedAt(LocalDateTime earnedAt) { this.earnedAt = earnedAt; }
}

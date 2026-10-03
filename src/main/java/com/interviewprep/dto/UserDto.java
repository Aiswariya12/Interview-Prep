package com.interviewprep.dto;

import com.interviewprep.entity.User;
import java.time.LocalDateTime;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String role;
    private String college;
    private String degree;
    private String branch;
    private Integer graduationYear;
    private Integer streakDays;
    private Integer totalTests = 0;
    private Double averageScore = 0.0;
    private Double bestScore = 0.0;
    private LocalDateTime createdAt;

    public UserDto() {}

    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        dto.setCollege(user.getCollege());
        dto.setDegree(user.getDegree());
        dto.setBranch(user.getBranch());
        dto.setGraduationYear(user.getGraduationYear());
        dto.setStreakDays(user.getStreakDays());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getCollege() { return college; }
    public void setCollege(String college) { this.college = college; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public Integer getGraduationYear() { return graduationYear; }
    public void setGraduationYear(Integer graduationYear) { this.graduationYear = graduationYear; }

    public Integer getStreakDays() { return streakDays; }
    public void setStreakDays(Integer streakDays) { this.streakDays = streakDays; }

    public Integer getTotalTests() { return totalTests; }
    public void setTotalTests(Integer totalTests) { this.totalTests = totalTests; }

    public Double getAverageScore() { return averageScore; }
    public void setAverageScore(Double averageScore) { this.averageScore = averageScore; }

    public Double getBestScore() { return bestScore; }
    public void setBestScore(Double bestScore) { this.bestScore = bestScore; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

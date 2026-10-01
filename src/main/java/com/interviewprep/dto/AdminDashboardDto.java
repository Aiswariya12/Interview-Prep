package com.interviewprep.dto;

import java.util.List;

public class AdminDashboardDto {

    private Long totalStudents;
    private Long totalQuestions;
    private Long totalMockTests;
    private Long totalSubjects;
    private Double averagePlatformScore;
    private List<MockTestSummaryDto> recentTests;
    private List<SubjectPerformanceDto> subjectStats;

    public AdminDashboardDto() {}

    public Long getTotalStudents() { return totalStudents; }
    public void setTotalStudents(Long totalStudents) { this.totalStudents = totalStudents; }

    public Long getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(Long totalQuestions) { this.totalQuestions = totalQuestions; }

    public Long getTotalMockTests() { return totalMockTests; }
    public void setTotalMockTests(Long totalMockTests) { this.totalMockTests = totalMockTests; }

    public Long getTotalSubjects() { return totalSubjects; }
    public void setTotalSubjects(Long totalSubjects) { this.totalSubjects = totalSubjects; }

    public Double getAveragePlatformScore() { return averagePlatformScore; }
    public void setAveragePlatformScore(Double averagePlatformScore) { this.averagePlatformScore = averagePlatformScore; }

    public List<MockTestSummaryDto> getRecentTests() { return recentTests; }
    public void setRecentTests(List<MockTestSummaryDto> recentTests) { this.recentTests = recentTests; }

    public List<SubjectPerformanceDto> getSubjectStats() { return subjectStats; }
    public void setSubjectStats(List<SubjectPerformanceDto> subjectStats) { this.subjectStats = subjectStats; }
}

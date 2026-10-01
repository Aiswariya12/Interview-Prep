package com.interviewprep.dto;

import java.util.List;

public class StudentDashboardDto {

    private Long totalTests;
    private Double averageScore;
    private Double accuracy;
    private Long questionsSolved;
    private Double bestScore;
    private Integer streakDays;
    private Integer badgesCount;
    private List<MockTestSummaryDto> recentTests;
    private List<WeakTopicDto> weakTopics;
    private List<SubjectPerformanceDto> subjectPerformances;

    public StudentDashboardDto() {}

    public Long getTotalTests() { return totalTests != null ? totalTests : 0L; }
    public void setTotalTests(Long totalTests) { this.totalTests = totalTests; }

    public Double getAverageScore() { return averageScore != null ? averageScore : 0.0; }
    public void setAverageScore(Double averageScore) { this.averageScore = averageScore; }

    public Double getAccuracy() { return accuracy != null ? accuracy : 0.0; }
    public void setAccuracy(Double accuracy) { this.accuracy = accuracy; }

    public Long getQuestionsSolved() { return questionsSolved != null ? questionsSolved : 0L; }
    public void setQuestionsSolved(Long questionsSolved) { this.questionsSolved = questionsSolved; }

    public Double getBestScore() { return bestScore != null ? bestScore : 0.0; }
    public void setBestScore(Double bestScore) { this.bestScore = bestScore; }

    public Integer getStreakDays() { return streakDays != null ? streakDays : 0; }
    public void setStreakDays(Integer streakDays) { this.streakDays = streakDays; }

    public Integer getBadgesCount() { return badgesCount != null ? badgesCount : 0; }
    public void setBadgesCount(Integer badgesCount) { this.badgesCount = badgesCount; }

    public List<MockTestSummaryDto> getRecentTests() { return recentTests; }
    public void setRecentTests(List<MockTestSummaryDto> recentTests) { this.recentTests = recentTests; }

    public List<WeakTopicDto> getWeakTopics() { return weakTopics; }
    public void setWeakTopics(List<WeakTopicDto> weakTopics) { this.weakTopics = weakTopics; }

    public List<SubjectPerformanceDto> getSubjectPerformances() { return subjectPerformances; }
    public void setSubjectPerformances(List<SubjectPerformanceDto> subjectPerformances) { this.subjectPerformances = subjectPerformances; }
}

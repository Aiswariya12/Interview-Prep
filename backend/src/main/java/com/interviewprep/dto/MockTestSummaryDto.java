package com.interviewprep.dto;

import com.interviewprep.entity.MockTest;
import java.time.LocalDateTime;

public class MockTestSummaryDto {

    private Long id;
    private String subjectName;
    private String topicName;
    private String difficulty;
    private Integer totalQuestions;
    private Double score;
    private Double maxScore;
    private Double percentage;
    private Double accuracy;
    private Integer correctCount;
    private Integer wrongCount;
    private Integer skippedCount;
    private Integer timeTakenSeconds;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    public MockTestSummaryDto() {}

    public static MockTestSummaryDto fromEntity(MockTest test) {
        MockTestSummaryDto dto = new MockTestSummaryDto();
        dto.setId(test.getId());
        dto.setSubjectName(test.getSubject().getName());
        dto.setTopicName(test.getTopic() != null ? test.getTopic().getName() : "All Topics");
        dto.setDifficulty(test.getDifficulty().name());
        dto.setTotalQuestions(test.getTotalQuestions());
        dto.setScore(test.getScore());
        dto.setMaxScore(test.getMaxScore());
        dto.setPercentage(test.getPercentage());
        dto.setAccuracy(test.getAccuracy());
        dto.setCorrectCount(test.getCorrectCount());
        dto.setWrongCount(test.getWrongCount());
        dto.setSkippedCount(test.getSkippedCount());
        dto.setTimeTakenSeconds(test.getTimeTakenSeconds());
        dto.setStatus(test.getStatus().name());
        dto.setCreatedAt(test.getCreatedAt());
        dto.setCompletedAt(test.getCompletedAt());
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public Integer getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(Integer totalQuestions) { this.totalQuestions = totalQuestions; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public Double getMaxScore() { return maxScore; }
    public void setMaxScore(Double maxScore) { this.maxScore = maxScore; }

    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }

    public Double getAccuracy() { return accuracy; }
    public void setAccuracy(Double accuracy) { this.accuracy = accuracy; }

    public Integer getCorrectCount() { return correctCount; }
    public void setCorrectCount(Integer correctCount) { this.correctCount = correctCount; }

    public Integer getWrongCount() { return wrongCount; }
    public void setWrongCount(Integer wrongCount) { this.wrongCount = wrongCount; }

    public Integer getSkippedCount() { return skippedCount; }
    public void setSkippedCount(Integer skippedCount) { this.skippedCount = skippedCount; }

    public Integer getTimeTakenSeconds() { return timeTakenSeconds; }
    public void setTimeTakenSeconds(Integer timeTakenSeconds) { this.timeTakenSeconds = timeTakenSeconds; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}

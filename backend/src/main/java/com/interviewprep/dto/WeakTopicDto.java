package com.interviewprep.dto;

public class WeakTopicDto {

    private Long topicId;
    private String topicName;
    private Long subjectId;
    private String subjectName;
    private Integer totalQuestionsAttempted;
    private Integer correctCount;
    private Double accuracyPercentage;
    private String recommendation;

    public WeakTopicDto() {}

    public WeakTopicDto(Long topicId, String topicName, Long subjectId, String subjectName,
                        Integer totalQuestionsAttempted, Integer correctCount, Double accuracyPercentage,
                        String recommendation) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.totalQuestionsAttempted = totalQuestionsAttempted;
        this.correctCount = correctCount;
        this.accuracyPercentage = accuracyPercentage;
        this.recommendation = recommendation;
    }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }

    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public Integer getTotalQuestionsAttempted() { return totalQuestionsAttempted; }
    public void setTotalQuestionsAttempted(Integer totalQuestionsAttempted) { this.totalQuestionsAttempted = totalQuestionsAttempted; }

    public Integer getCorrectCount() { return correctCount; }
    public void setCorrectCount(Integer correctCount) { this.correctCount = correctCount; }

    public Double getAccuracyPercentage() { return accuracyPercentage; }
    public void setAccuracyPercentage(Double accuracyPercentage) { this.accuracyPercentage = accuracyPercentage; }

    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
}

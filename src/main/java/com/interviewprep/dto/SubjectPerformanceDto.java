package com.interviewprep.dto;

public class SubjectPerformanceDto {

    private Long subjectId;
    private String subjectName;
    private String icon;
    private String color;
    private Integer testsTaken;
    private Integer totalQuestionsAttempted;
    private Integer correctCount;
    private Double accuracyPercentage;

    public SubjectPerformanceDto() {}

    public SubjectPerformanceDto(Long subjectId, String subjectName, String icon, String color,
                                 Integer testsTaken, Integer totalQuestionsAttempted,
                                 Integer correctCount, Double accuracyPercentage) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.icon = icon;
        this.color = color;
        this.testsTaken = testsTaken;
        this.totalQuestionsAttempted = totalQuestionsAttempted;
        this.correctCount = correctCount;
        this.accuracyPercentage = accuracyPercentage;
    }

    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Integer getTestsTaken() { return testsTaken; }
    public void setTestsTaken(Integer testsTaken) { this.testsTaken = testsTaken; }

    public Integer getTotalQuestionsAttempted() { return totalQuestionsAttempted; }
    public void setTotalQuestionsAttempted(Integer totalQuestionsAttempted) { this.totalQuestionsAttempted = totalQuestionsAttempted; }

    public Integer getCorrectCount() { return correctCount; }
    public void setCorrectCount(Integer correctCount) { this.correctCount = correctCount; }

    public Double getAccuracyPercentage() { return accuracyPercentage; }
    public void setAccuracyPercentage(Double accuracyPercentage) { this.accuracyPercentage = accuracyPercentage; }
}

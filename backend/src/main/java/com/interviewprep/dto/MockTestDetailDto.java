package com.interviewprep.dto;

import com.interviewprep.entity.MockTest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MockTestDetailDto {

    private Long id;
    private Long subjectId;
    private String subjectName;
    private String topicName;
    private String difficulty;
    private Integer totalQuestions;
    private Integer durationMinutes;
    private Boolean isNegativeMarking;
    private Double negativeMarkValue;
    private String status;
    private Integer timeTakenSeconds;
    private LocalDateTime createdAt;
    private List<MockQuestionDto> questions;

    public MockTestDetailDto() {}

    public static MockTestDetailDto fromEntity(MockTest test) {
        MockTestDetailDto dto = new MockTestDetailDto();
        dto.setId(test.getId());
        dto.setSubjectId(test.getSubject().getId());
        dto.setSubjectName(test.getSubject().getName());
        if (test.getTopic() != null) {
            dto.setTopicName(test.getTopic().getName());
        } else {
            dto.setTopicName("All Topics");
        }
        dto.setDifficulty(test.getDifficulty().name());
        dto.setTotalQuestions(test.getTotalQuestions());
        dto.setDurationMinutes(test.getDurationMinutes());
        dto.setIsNegativeMarking(test.getIsNegativeMarking());
        dto.setNegativeMarkValue(test.getNegativeMarkValue());
        dto.setStatus(test.getStatus().name());
        dto.setTimeTakenSeconds(test.getTimeTakenSeconds());
        dto.setCreatedAt(test.getCreatedAt());
        if (test.getMockQuestions() != null) {
            dto.setQuestions(test.getMockQuestions().stream()
                    .map(MockQuestionDto::fromEntity)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public Integer getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(Integer totalQuestions) { this.totalQuestions = totalQuestions; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public Boolean getIsNegativeMarking() { return isNegativeMarking; }
    public void setIsNegativeMarking(Boolean negativeMarking) { isNegativeMarking = negativeMarking; }

    public Double getNegativeMarkValue() { return negativeMarkValue; }
    public void setNegativeMarkValue(Double negativeMarkValue) { this.negativeMarkValue = negativeMarkValue; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getTimeTakenSeconds() { return timeTakenSeconds; }
    public void setTimeTakenSeconds(Integer timeTakenSeconds) { this.timeTakenSeconds = timeTakenSeconds; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<MockQuestionDto> getQuestions() { return questions; }
    public void setQuestions(List<MockQuestionDto> questions) { this.questions = questions; }
}

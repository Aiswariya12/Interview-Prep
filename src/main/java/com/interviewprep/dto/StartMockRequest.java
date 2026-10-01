package com.interviewprep.dto;

import com.interviewprep.entity.Difficulty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class StartMockRequest {

    @NotNull(message = "Subject ID is required")
    private Long subjectId;

    private Long topicId;

    private Difficulty difficulty = Difficulty.ALL;

    @Min(value = 1, message = "Minimum 1 question")
    @Max(value = 50, message = "Maximum 50 questions")
    private Integer numberOfQuestions = 10;

    @Min(value = 1, message = "Minimum 1 minute duration")
    @Max(value = 180, message = "Maximum 180 minutes duration")
    private Integer durationMinutes = 15;

    private Boolean negativeMarking = true;

    public StartMockRequest() {}

    public Long getSubjectId() { return subjectId; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }

    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public Difficulty getDifficulty() { return difficulty != null ? difficulty : Difficulty.ALL; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }

    public Integer getNumberOfQuestions() { return numberOfQuestions != null ? numberOfQuestions : 10; }
    public void setNumberOfQuestions(Integer numberOfQuestions) { this.numberOfQuestions = numberOfQuestions; }

    public Integer getDurationMinutes() { return durationMinutes != null ? durationMinutes : 15; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public Boolean getNegativeMarking() { return negativeMarking != null ? negativeMarking : true; }
    public void setNegativeMarking(Boolean negativeMarking) { this.negativeMarking = negativeMarking; }
}

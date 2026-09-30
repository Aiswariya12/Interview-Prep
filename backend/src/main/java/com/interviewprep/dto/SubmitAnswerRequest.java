package com.interviewprep.dto;

public class SubmitAnswerRequest {

    private Long mockQuestionId;
    private String selectedOption; // "A", "B", "C", "D" or null
    private Boolean isMarkedForReview;

    public SubmitAnswerRequest() {}

    public Long getMockQuestionId() { return mockQuestionId; }
    public void setMockQuestionId(Long mockQuestionId) { this.mockQuestionId = mockQuestionId; }

    public String getSelectedOption() { return selectedOption; }
    public void setSelectedOption(String selectedOption) { this.selectedOption = selectedOption; }

    public Boolean getIsMarkedForReview() { return isMarkedForReview; }
    public void setIsMarkedForReview(Boolean isMarkedForReview) { this.isMarkedForReview = isMarkedForReview; }
}

package com.interviewprep.dto;

import java.util.List;

public class SubmitMockRequest {

    private Long mockTestId;
    private Integer timeTakenSeconds;
    private List<SubmitAnswerRequest> answers;

    public SubmitMockRequest() {}

    public Long getMockTestId() { return mockTestId; }
    public void setMockTestId(Long mockTestId) { this.mockTestId = mockTestId; }

    public Integer getTimeTakenSeconds() { return timeTakenSeconds; }
    public void setTimeTakenSeconds(Integer timeTakenSeconds) { this.timeTakenSeconds = timeTakenSeconds; }

    public List<SubmitAnswerRequest> getAnswers() { return answers; }
    public void setAnswers(List<SubmitAnswerRequest> answers) { this.answers = answers; }
}

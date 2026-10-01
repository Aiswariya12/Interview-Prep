package com.interviewprep.dto;

import com.interviewprep.entity.MockQuestion;
import com.interviewprep.entity.Question;

public class MockQuestionDto {

    private Long mockQuestionId;
    private Long questionId;
    private Integer questionOrder;
    private String questionText;
    private String codeSnippet;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String selectedOption;
    private Boolean isMarkedForReview;
    private Double marks;

    public MockQuestionDto() {}

    public static MockQuestionDto fromEntity(MockQuestion mq) {
        MockQuestionDto dto = new MockQuestionDto();
        dto.setMockQuestionId(mq.getId());
        Question q = mq.getQuestion();
        dto.setQuestionId(q.getId());
        dto.setQuestionOrder(mq.getQuestionOrder());
        dto.setQuestionText(q.getQuestionText());
        dto.setCodeSnippet(q.getCodeSnippet());
        dto.setOptionA(q.getOptionA());
        dto.setOptionB(q.getOptionB());
        dto.setOptionC(q.getOptionC());
        dto.setOptionD(q.getOptionD());
        dto.setSelectedOption(mq.getSelectedOption());
        dto.setIsMarkedForReview(mq.getIsMarkedForReview());
        dto.setMarks(q.getMarks());
        return dto;
    }

    public Long getMockQuestionId() { return mockQuestionId; }
    public void setMockQuestionId(Long mockQuestionId) { this.mockQuestionId = mockQuestionId; }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public Integer getQuestionOrder() { return questionOrder; }
    public void setQuestionOrder(Integer questionOrder) { this.questionOrder = questionOrder; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getCodeSnippet() { return codeSnippet; }
    public void setCodeSnippet(String codeSnippet) { this.codeSnippet = codeSnippet; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getOptionD() { return optionD; }
    public void setOptionD(String optionD) { this.optionD = optionD; }

    public String getSelectedOption() { return selectedOption; }
    public void setSelectedOption(String selectedOption) { this.selectedOption = selectedOption; }

    public Boolean getIsMarkedForReview() { return isMarkedForReview; }
    public void setIsMarkedForReview(Boolean markedForReview) { isMarkedForReview = markedForReview; }

    public Double getMarks() { return marks; }
    public void setMarks(Double marks) { this.marks = marks; }
}

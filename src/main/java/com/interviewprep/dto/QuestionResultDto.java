package com.interviewprep.dto;

import com.interviewprep.entity.MockQuestion;
import com.interviewprep.entity.Question;

public class QuestionResultDto {

    private Long mockQuestionId;
    private Long questionId;
    private Integer questionOrder;
    private String topicName;
    private String questionText;
    private String codeSnippet;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String selectedOption;
    private String correctOption;
    private Boolean isCorrect;
    private Boolean isSkipped;
    private Double marksObtained;
    private String explanation;
    private Boolean isBookmarked = false;

    public QuestionResultDto() {}

    public static QuestionResultDto fromEntity(MockQuestion mq, boolean bookmarked) {
        QuestionResultDto dto = new QuestionResultDto();
        dto.setMockQuestionId(mq.getId());
        Question q = mq.getQuestion();
        dto.setQuestionId(q.getId());
        dto.setQuestionOrder(mq.getQuestionOrder());
        if (q.getTopic() != null) {
            dto.setTopicName(q.getTopic().getName());
        } else {
            dto.setTopicName("General");
        }
        dto.setQuestionText(q.getQuestionText());
        dto.setCodeSnippet(q.getCodeSnippet());
        dto.setOptionA(q.getOptionA());
        dto.setOptionB(q.getOptionB());
        dto.setOptionC(q.getOptionC());
        dto.setOptionD(q.getOptionD());
        dto.setSelectedOption(mq.getSelectedOption());
        dto.setCorrectOption(q.getCorrectOption());
        dto.setIsCorrect(mq.getIsCorrect());
        dto.setIsSkipped(mq.getIsSkipped());
        dto.setMarksObtained(mq.getMarksObtained());
        dto.setExplanation(q.getExplanation());
        dto.setIsBookmarked(bookmarked);
        return dto;
    }

    public Long getMockQuestionId() { return mockQuestionId; }
    public void setMockQuestionId(Long mockQuestionId) { this.mockQuestionId = mockQuestionId; }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public Integer getQuestionOrder() { return questionOrder; }
    public void setQuestionOrder(Integer questionOrder) { this.questionOrder = questionOrder; }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }

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

    public String getCorrectOption() { return correctOption; }
    public void setCorrectOption(String correctOption) { this.correctOption = correctOption; }

    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean correct) { isCorrect = correct; }

    public Boolean getIsSkipped() { return isSkipped; }
    public void setIsSkipped(Boolean skipped) { isSkipped = skipped; }

    public Double getMarksObtained() { return marksObtained; }
    public void setMarksObtained(Double marksObtained) { this.marksObtained = marksObtained; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    public Boolean getIsBookmarked() { return isBookmarked; }
    public void setIsBookmarked(Boolean bookmarked) { isBookmarked = bookmarked; }
}

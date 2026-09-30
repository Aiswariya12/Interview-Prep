package com.interviewprep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "mock_questions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MockQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mock_test_id", nullable = false)
    @JsonIgnoreProperties({"mockQuestions", "hibernateLazyInitializer", "handler"})
    private MockTest mockTest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Question question;

    @Column(nullable = false)
    private Integer questionOrder;

    private String selectedOption; // A, B, C, D or null
    private Boolean isCorrect;
    private Boolean isMarkedForReview = false;
    private Boolean isSkipped = true;
    private Double marksObtained = 0.0;

    public MockQuestion() {}

    public MockQuestion(MockTest mockTest, Question question, Integer questionOrder) {
        this.mockTest = mockTest;
        this.question = question;
        this.questionOrder = questionOrder;
        this.isMarkedForReview = false;
        this.isSkipped = true;
        this.marksObtained = 0.0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public MockTest getMockTest() { return mockTest; }
    public void setMockTest(MockTest mockTest) { this.mockTest = mockTest; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public Integer getQuestionOrder() { return questionOrder; }
    public void setQuestionOrder(Integer questionOrder) { this.questionOrder = questionOrder; }

    public String getSelectedOption() { return selectedOption; }
    public void setSelectedOption(String selectedOption) { this.selectedOption = selectedOption; }

    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean correct) { isCorrect = correct; }

    public Boolean getIsMarkedForReview() { return isMarkedForReview != null ? isMarkedForReview : false; }
    public void setIsMarkedForReview(Boolean markedForReview) { isMarkedForReview = markedForReview; }

    public Boolean getIsSkipped() { return isSkipped != null ? isSkipped : true; }
    public void setIsSkipped(Boolean skipped) { isSkipped = skipped; }

    public Double getMarksObtained() { return marksObtained != null ? marksObtained : 0.0; }
    public void setMarksObtained(Double marksObtained) { this.marksObtained = marksObtained; }
}

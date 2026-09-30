package com.interviewprep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnoreProperties({"topics", "hibernateLazyInitializer", "handler"})
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    @JsonIgnoreProperties({"subject", "hibernateLazyInitializer", "handler"})
    private Topic topic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty = Difficulty.MEDIUM;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionText;

    @Column(columnDefinition = "TEXT")
    private String codeSnippet;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String optionA;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String optionB;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String optionC;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String optionD;

    @Column(nullable = false, length = 5)
    private String correctOption; // A, B, C, D

    @Column(columnDefinition = "TEXT", nullable = false)
    private String explanation;

    @Column(nullable = false)
    private Double marks = 1.0;

    @Column(nullable = false)
    private Double negativeMarks = 0.25;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Question() {}

    public Question(Subject subject, Topic topic, Difficulty difficulty, String questionText,
                    String codeSnippet, String optionA, String optionB, String optionC, String optionD,
                    String correctOption, String explanation, Double marks, Double negativeMarks) {
        this.subject = subject;
        this.topic = topic;
        this.difficulty = difficulty;
        this.questionText = questionText;
        this.codeSnippet = codeSnippet;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
        this.explanation = explanation;
        this.marks = marks != null ? marks : 1.0;
        this.negativeMarks = negativeMarks != null ? negativeMarks : 0.25;
        this.active = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public Topic getTopic() { return topic; }
    public void setTopic(Topic topic) { this.topic = topic; }

    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }

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

    public String getCorrectOption() { return correctOption; }
    public void setCorrectOption(String correctOption) { this.correctOption = correctOption; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    public Double getMarks() { return marks; }
    public void setMarks(Double marks) { this.marks = marks; }

    public Double getNegativeMarks() { return negativeMarks; }
    public void setNegativeMarks(Double negativeMarks) { this.negativeMarks = negativeMarks; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
        if (this.marks == null) this.marks = 1.0;
        if (this.negativeMarks == null) this.negativeMarks = 0.25;
        if (this.active == null) this.active = true;
    }
}

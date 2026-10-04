package com.interviewprep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mock_tests")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MockTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"password", "hibernateLazyInitializer", "handler"})
    private User user;

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
    private Difficulty difficulty = Difficulty.ALL;

    @Column(nullable = false)
    private Integer totalQuestions;

    @Column(nullable = false)
    private Integer durationMinutes;

    private Boolean isNegativeMarking = true;
    private Double negativeMarkValue = 0.25;

    private Double score = 0.0;
    private Double maxScore = 0.0;
    private Double percentage = 0.0;
    private Double accuracy = 0.0;

    private Integer correctCount = 0;
    private Integer wrongCount = 0;
    private Integer skippedCount = 0;
    private Integer timeTakenSeconds = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MockTestStatus status = MockTestStatus.IN_PROGRESS;

    @OneToMany(mappedBy = "mockTest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("questionOrder ASC")
    @JsonIgnoreProperties("mockTest")
    private List<MockQuestion> mockQuestions = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime completedAt;

    public MockTest() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public Topic getTopic() { return topic; }
    public void setTopic(Topic topic) { this.topic = topic; }

    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }

    public Integer getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(Integer totalQuestions) { this.totalQuestions = totalQuestions; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public Boolean getIsNegativeMarking() { return isNegativeMarking != null ? isNegativeMarking : true; }
    public void setIsNegativeMarking(Boolean negativeMarking) { isNegativeMarking = negativeMarking; }

    public Double getNegativeMarkValue() { return negativeMarkValue != null ? negativeMarkValue : 0.25; }
    public void setNegativeMarkValue(Double negativeMarkValue) { this.negativeMarkValue = negativeMarkValue; }

    public Double getScore() { return score != null ? score : 0.0; }
    public void setScore(Double score) { this.score = score; }

    public Double getMaxScore() { return maxScore != null ? maxScore : 0.0; }
    public void setMaxScore(Double maxScore) { this.maxScore = maxScore; }

    public Double getPercentage() { return percentage != null ? percentage : 0.0; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }

    public Double getAccuracy() { return accuracy != null ? accuracy : 0.0; }
    public void setAccuracy(Double accuracy) { this.accuracy = accuracy; }

    public Integer getCorrectCount() { return correctCount != null ? correctCount : 0; }
    public void setCorrectCount(Integer correctCount) { this.correctCount = correctCount; }

    public Integer getWrongCount() { return wrongCount != null ? wrongCount : 0; }
    public void setWrongCount(Integer wrongCount) { this.wrongCount = wrongCount; }

    public Integer getSkippedCount() { return skippedCount != null ? skippedCount : 0; }
    public void setSkippedCount(Integer skippedCount) { this.skippedCount = skippedCount; }

    public Integer getTimeTakenSeconds() { return timeTakenSeconds != null ? timeTakenSeconds : 0; }
    public void setTimeTakenSeconds(Integer timeTakenSeconds) { this.timeTakenSeconds = timeTakenSeconds; }

    public MockTestStatus getStatus() { return status; }
    public void setStatus(MockTestStatus status) { this.status = status; }

    public List<MockQuestion> getMockQuestions() { return mockQuestions; }
    public void setMockQuestions(List<MockQuestion> mockQuestions) {
        this.mockQuestions.clear();
        if (mockQuestions != null) {
            this.mockQuestions.addAll(mockQuestions);
        }
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = MockTestStatus.IN_PROGRESS;
    }
}

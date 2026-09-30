package com.interviewprep.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "daily_challenges")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DailyChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private LocalDate challengeDate;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer rewardPoints = 50;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    @JsonIgnoreProperties({"topics", "hibernateLazyInitializer", "handler"})
    private Subject subject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "daily_challenge_questions",
        joinColumns = @JoinColumn(name = "challenge_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> questions = new LinkedHashSet<>();

    public DailyChallenge() {}

    public DailyChallenge(LocalDate challengeDate, String title, String description, Subject subject) {
        this.challengeDate = challengeDate;
        this.title = title;
        this.description = description;
        this.subject = subject;
        this.rewardPoints = 50;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getChallengeDate() { return challengeDate; }
    public void setChallengeDate(LocalDate challengeDate) { this.challengeDate = challengeDate; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getRewardPoints() { return rewardPoints; }
    public void setRewardPoints(Integer rewardPoints) { this.rewardPoints = rewardPoints; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public Set<Question> getQuestions() { return questions; }
    public void setQuestions(Set<Question> questions) { this.questions = questions; }
}

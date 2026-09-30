package com.interviewprep.repository;

import com.interviewprep.entity.Difficulty;
import com.interviewprep.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findBySubjectIdAndActiveTrue(Long subjectId);

    List<Question> findByTopicIdAndActiveTrue(Long topicId);

    long countByActiveTrue();

    long countBySubjectId(Long subjectId);

    @Query("SELECT q FROM Question q WHERE q.active = true " +
           "AND (:subjectId IS NULL OR q.subject.id = :subjectId) " +
           "AND (:topicId IS NULL OR (q.topic IS NOT NULL AND q.topic.id = :topicId)) " +
           "AND (:difficulty IS NULL OR :difficulty = 'ALL' OR q.difficulty = :diffEnum)")
    List<Question> findMatchingQuestions(
        @Param("subjectId") Long subjectId,
        @Param("topicId") Long topicId,
        @Param("difficulty") String difficulty,
        @Param("diffEnum") Difficulty diffEnum
    );

    @Query("SELECT q FROM Question q WHERE " +
           "(:subjectId IS NULL OR q.subject.id = :subjectId) AND " +
           "(:difficulty IS NULL OR q.difficulty = :difficulty) AND " +
           "(:search IS NULL OR LOWER(q.questionText) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Question> searchQuestions(
        @Param("subjectId") Long subjectId,
        @Param("difficulty") Difficulty difficulty,
        @Param("search") String search,
        Pageable pageable
    );
}

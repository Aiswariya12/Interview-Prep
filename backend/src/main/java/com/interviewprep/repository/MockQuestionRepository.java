package com.interviewprep.repository;

import com.interviewprep.entity.MockQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MockQuestionRepository extends JpaRepository<MockQuestion, Long> {

    List<MockQuestion> findByMockTestIdOrderByQuestionOrderAsc(Long mockTestId);

    @Query("SELECT mq FROM MockQuestion mq WHERE mq.mockTest.user.id = :userId")
    List<MockQuestion> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(mq) FROM MockQuestion mq WHERE mq.mockTest.user.id = :userId AND mq.isSkipped = false")
    Long countAttemptedQuestionsByUserId(@Param("userId") Long userId);
}

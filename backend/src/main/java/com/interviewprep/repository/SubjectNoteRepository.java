package com.interviewprep.repository;

import com.interviewprep.entity.SubjectNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectNoteRepository extends JpaRepository<SubjectNote, Long> {

    @Query("SELECT n FROM SubjectNote n WHERE n.subject.id = :subjectId ORDER BY n.id ASC")
    List<SubjectNote> findBySubjectIdOrderByIdAsc(@Param("subjectId") Long subjectId);
}


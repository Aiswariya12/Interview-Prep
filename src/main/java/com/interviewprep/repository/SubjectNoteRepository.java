package com.interviewprep.repository;

import com.interviewprep.entity.SubjectNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectNoteRepository extends JpaRepository<SubjectNote, Long> {
    List<SubjectNote> findBySubjectIdOrderByIdAsc(Long subjectId);
}

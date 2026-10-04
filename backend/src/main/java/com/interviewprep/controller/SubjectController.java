package com.interviewprep.controller;

import com.interviewprep.dto.ApiResponse;
import com.interviewprep.entity.Subject;
import com.interviewprep.entity.SubjectNote;
import com.interviewprep.entity.Topic;
import com.interviewprep.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@Tag(name = "Subjects & Topics", description = "Endpoints for retrieving subjects and topics")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    @Operation(summary = "Get all active subjects")
    public ResponseEntity<ApiResponse<List<Subject>>> getActiveSubjects() {
        return ResponseEntity.ok(ApiResponse.ok(subjectService.getAllActiveSubjects()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get subject by ID")
    public ResponseEntity<ApiResponse<Subject>> getSubjectById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(subjectService.getSubjectById(id)));
    }

    @GetMapping("/{id}/topics")
    @Operation(summary = "Get all topics under a specific subject")
    public ResponseEntity<ApiResponse<List<Topic>>> getTopicsBySubject(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(subjectService.getTopicsBySubjectId(id)));
    }

    @GetMapping("/{id}/notes")
    @Operation(summary = "Get all note links under a specific subject")
    public ResponseEntity<ApiResponse<List<SubjectNote>>> getNotesBySubject(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(subjectService.getNotesBySubjectId(id)));
    }
}

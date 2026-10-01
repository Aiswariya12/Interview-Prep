package com.interviewprep.controller;

import com.interviewprep.dto.ApiResponse;
import com.interviewprep.entity.Difficulty;
import com.interviewprep.entity.Question;
import com.interviewprep.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
@Tag(name = "Questions", description = "Endpoints for exploring and querying questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    @Operation(summary = "Search questions with pagination and filters")
    public ResponseEntity<ApiResponse<Page<Question>>> getQuestions(
            @RequestParam(required = false) Long subjectId,
            @RequestParam(required = false) Difficulty difficulty,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Question> questions = questionService.getQuestions(subjectId, difficulty, search, pageable);
        return ResponseEntity.ok(ApiResponse.ok(questions));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get question details by ID")
    public ResponseEntity<ApiResponse<Question>> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(questionService.getQuestionById(id)));
    }
}

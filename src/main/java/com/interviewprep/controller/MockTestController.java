package com.interviewprep.controller;

import com.interviewprep.dto.*;
import com.interviewprep.service.MockTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mock")
@Tag(name = "Mock Tests", description = "Endpoints for starting, taking, and evaluating mock tests")
public class MockTestController {

    private final MockTestService mockTestService;

    public MockTestController(MockTestService mockTestService) {
        this.mockTestService = mockTestService;
    }

    @PostMapping("/start")
    @Operation(summary = "Start a new randomized mock test")
    public ResponseEntity<ApiResponse<MockTestDetailDto>> startMockTest(@Valid @RequestBody StartMockRequest req) {
        MockTestDetailDto dto = mockTestService.startMockTest(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Mock test started", dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get mock test details for an ongoing test")
    public ResponseEntity<ApiResponse<MockTestDetailDto>> getMockTestById(@PathVariable Long id) {
        MockTestDetailDto dto = mockTestService.getMockTestById(id);
        return ResponseEntity.ok(ApiResponse.ok(dto));
    }

    @PostMapping("/{id}/answer")
    @Operation(summary = "Record answer or flag question for review during test")
    public ResponseEntity<ApiResponse<Void>> saveAnswer(
            @PathVariable Long id,
            @RequestBody SubmitAnswerRequest req
    ) {
        mockTestService.saveAnswer(id, req);
        return ResponseEntity.ok(ApiResponse.ok("Answer updated", null));
    }

    @PostMapping("/submit")
    @Operation(summary = "Submit mock test for automatic grading and result calculation")
    public ResponseEntity<ApiResponse<MockTestResultDto>> submitMockTest(@RequestBody SubmitMockRequest req) {
        MockTestResultDto result = mockTestService.submitMockTest(req);
        return ResponseEntity.ok(ApiResponse.ok("Test evaluated successfully", result));
    }

    @GetMapping("/results/{id}")
    @Operation(summary = "Get detailed result analysis and question explanations")
    public ResponseEntity<ApiResponse<MockTestResultDto>> getMockTestResult(@PathVariable Long id) {
        MockTestResultDto result = mockTestService.getMockTestResult(id);
        return ResponseEntity.ok(ApiResponse.ok(result));
    }

    @GetMapping("/history")
    @Operation(summary = "Get list of completed mock tests by the student")
    public ResponseEntity<ApiResponse<List<MockTestSummaryDto>>> getStudentHistory() {
        List<MockTestSummaryDto> history = mockTestService.getStudentHistory();
        return ResponseEntity.ok(ApiResponse.ok(history));
    }
}

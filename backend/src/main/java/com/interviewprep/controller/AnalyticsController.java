package com.interviewprep.controller;

import com.interviewprep.dto.ApiResponse;
import com.interviewprep.dto.StudentDashboardDto;
import com.interviewprep.dto.SubjectPerformanceDto;
import com.interviewprep.dto.WeakTopicDto;
import com.interviewprep.service.AnalyticsService;
import com.interviewprep.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@Tag(name = "Analytics", description = "Endpoints for performance metrics, dashboards, and weak topic detection")
public class AnalyticsController {

    private final AnalyticsService analyticsService;
    private final AuthService authService;

    public AnalyticsController(AnalyticsService analyticsService, AuthService authService) {
        this.analyticsService = analyticsService;
        this.authService = authService;
    }

    @GetMapping("/dashboard")
    @Operation(summary = "Get aggregated metrics for student dashboard")
    public ResponseEntity<ApiResponse<StudentDashboardDto>> getStudentDashboard() {
        StudentDashboardDto dto = analyticsService.getStudentDashboardData();
        return ResponseEntity.ok(ApiResponse.ok(dto));
    }

    @GetMapping("/weak-topics")
    @Operation(summary = "Get detected weak topics with recommended practice quizzes")
    public ResponseEntity<ApiResponse<List<WeakTopicDto>>> getWeakTopics() {
        Long userId = authService.getCurrentAuthenticatedUser().getId();
        List<WeakTopicDto> weakTopics = analyticsService.detectWeakTopics(userId);
        return ResponseEntity.ok(ApiResponse.ok(weakTopics));
    }

    @GetMapping("/subjects")
    @Operation(summary = "Get subject-wise performance and accuracy breakdown")
    public ResponseEntity<ApiResponse<List<SubjectPerformanceDto>>> getSubjectPerformance() {
        Long userId = authService.getCurrentAuthenticatedUser().getId();
        List<SubjectPerformanceDto> list = analyticsService.getSubjectPerformance(userId);
        return ResponseEntity.ok(ApiResponse.ok(list));
    }
}

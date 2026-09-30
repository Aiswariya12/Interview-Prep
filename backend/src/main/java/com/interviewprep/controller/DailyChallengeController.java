package com.interviewprep.controller;

import com.interviewprep.dto.ApiResponse;
import com.interviewprep.entity.DailyChallenge;
import com.interviewprep.service.DailyChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/daily-challenge")
@Tag(name = "Daily Challenge", description = "Endpoints for daily technical challenge")
public class DailyChallengeController {

    private final DailyChallengeService dailyChallengeService;

    public DailyChallengeController(DailyChallengeService dailyChallengeService) {
        this.dailyChallengeService = dailyChallengeService;
    }

    @GetMapping("/today")
    @Operation(summary = "Get today's daily challenge")
    public ResponseEntity<ApiResponse<DailyChallenge>> getTodayChallenge() {
        return ResponseEntity.ok(ApiResponse.ok(dailyChallengeService.getOrCreateTodayChallenge()));
    }
}

package com.interviewprep.controller;

import com.interviewprep.dto.ApiResponse;
import com.interviewprep.entity.Bookmark;
import com.interviewprep.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookmarks")
@Tag(name = "Bookmarks", description = "Endpoints for bookmarking questions to review later")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/toggle/{questionId}")
    @Operation(summary = "Toggle bookmark on a question")
    public ResponseEntity<ApiResponse<Map<String, Object>>> toggleBookmark(
            @PathVariable Long questionId,
            @RequestBody(required = false) Map<String, String> body
    ) {
        String notes = body != null ? body.get("notes") : null;
        Bookmark b = bookmarkService.toggleBookmark(questionId, notes);
        boolean bookmarked = (b != null);
        return ResponseEntity.ok(ApiResponse.ok(
                bookmarked ? "Question bookmarked" : "Bookmark removed",
                Map.of("bookmarked", bookmarked)
        ));
    }

    @GetMapping
    @Operation(summary = "Get list of bookmarked questions for the logged-in student")
    public ResponseEntity<ApiResponse<List<Bookmark>>> getMyBookmarks() {
        return ResponseEntity.ok(ApiResponse.ok(bookmarkService.getMyBookmarks()));
    }
}

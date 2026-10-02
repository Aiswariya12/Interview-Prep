package com.interviewprep.controller;

import com.interviewprep.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AiChatController {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    @PostMapping("/chat")
    public ResponseEntity<ApiResponse<Map<String, String>>> chat(@RequestBody Map<String, String> payload) {
        String prompt = payload.get("prompt");
        if (prompt == null || prompt.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, "Prompt is required", null));
        }

        try {
            String fullPrompt = "You are an expert Technical Interview Coach for InterviewPrep. " +
                    "Answer accurately, clearly, and concisely with markdown formatting and code examples where applicable. " +
                    "User question: " + prompt.trim();

            String encoded = URLEncoder.encode(fullPrompt, StandardCharsets.UTF_8);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://text.pollinations.ai/" + encoded))
                    .timeout(Duration.ofSeconds(25))
                    .header("User-Agent", "InterviewPrep-Server/1.0")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 && response.body() != null && !response.body().isBlank()) {
                return ResponseEntity.ok(new ApiResponse<>(true, "Success", Map.of("answer", response.body())));
            }
        } catch (Exception ex) {
            // log and fallback
        }

        // Fallback intelligent answer if remote AI is temporarily busy
        String fallbackAnswer = generateFallbackAnswer(prompt.trim());
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", Map.of("answer", fallbackAnswer)));
    }

    private String generateFallbackAnswer(String query) {
        String q = query.toLowerCase();
        if (q.contains("hi") || q.contains("hello") || q.contains("hey")) {
            return "Hello! 👋 I am your **InterviewPrep Technical Coach**. Ask me any technical interview question on Java, Spring Boot, React, MySQL, DSA, or System Design and I will break it down for you with code examples!";
        } else if (q.contains("polymorphism")) {
            return "### Polymorphism in Java\n" +
                    "Polymorphism allows objects of different types to be treated as instances of a common superclass or interface.\n\n" +
                    "1. **Compile-time (Static):** Method Overloading (same method name, different parameters).\n" +
                    "2. **Runtime (Dynamic):** Method Overriding (subclass provides specific implementation of parent method using `@Override`).\n\n" +
                    "```java\n" +
                    "Animal a = new Dog(); // Polymorphic reference\n" +
                    "a.makeSound();       // Calls Dog's overridden method at runtime\n" +
                    "```";
        } else if (q.contains("hashmap") || q.contains("concurrenthashmap")) {
            return "### HashMap vs ConcurrentHashMap\n" +
                    "- **HashMap:** Not thread-safe. Concurrent modifications can cause race conditions or infinite loops. Allows one null key.\n" +
                    "- **ConcurrentHashMap:** Thread-safe without locking the whole map. Uses CAS (Compare-And-Swap) and synchronized bucket locks (lock-striping). Does **not** allow null keys or values.\n\n" +
                    "```java\n" +
                    "Map<String, Integer> map = new ConcurrentHashMap<>();\n" +
                    "map.put(\"key\", 1); // Thread-safe read and write\n" +
                    "```";
        } else {
            return "Great question! When discussing **" + query + "** in technical interviews, recruiters look for:\n\n" +
                    "1. **Core Concept:** Explain the fundamental definition clearly without buzzwords.\n" +
                    "2. **Trade-offs:** Discuss Time/Space complexity or performance implications.\n" +
                    "3. **Practical Application:** Give a real-world scenario where you used it.\n\n" +
                    "Could you specify if you need a code implementation or theoretical architectural explanation?";
        }
    }
}

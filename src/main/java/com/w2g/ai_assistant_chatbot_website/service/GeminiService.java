package com.w2g.ai_assistant_chatbot_website.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class GeminiService {
    @Value("${gemini.api-key}")
    private String API_KEY;


    private final RestTemplate restTemplate;

    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendRequestToGemini(String userQuestion, String fileContent) {
        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key="+ API_KEY;  // Thay thế bằng URL thực của Gemini API

        // Tạo headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Tạo body JSON cho "parts"
        Map<String, Object> parts = new HashMap<>();
        parts.put("text", fileContent + "/n" + " Client's question: " + userQuestion + "/n" + "Gemini plays the role of Mr. Toan Nguyen and please answer in a medium or short, polite manner. Note that Gemini determines the language of the user's question and answers in the corresponding language!");

        // Tạo body JSON cho "contents"
        Map<String, Object> contents = new HashMap<>();
        contents.put("role", "user");
        contents.put("parts", new Object[]{parts});

        // Tạo "generationConfig"
        Map<String, Object> generationConfig = new HashMap<>();
        generationConfig.put("temperature", 1);
        generationConfig.put("topK", 64);
        generationConfig.put("topP", 0.95);
        generationConfig.put("maxOutputTokens", 8192);
        generationConfig.put("responseMimeType", "text/plain");

        // Tạo request body chính
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", new Object[]{contents});
        requestBody.put("generationConfig", generationConfig);

        // Đóng gói request
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // Gửi request
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                String.class
        );

        return response.getBody();
    }
}

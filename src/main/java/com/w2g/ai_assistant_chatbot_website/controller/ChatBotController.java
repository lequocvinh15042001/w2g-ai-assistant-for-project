package com.w2g.ai_assistant_chatbot_website.controller;
import com.w2g.ai_assistant_chatbot_website.service.GeminiService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
public class ChatBotController {
    private GeminiService geminiService;

    // Biến toàn cục để lưu nội dung file sau khi đọc
    private String fileContent;

    @Value("${path.data}")
    private String PATH_DATA;

    // Đọc file khi ứng dụng khởi động
    @PostConstruct
    public void init() {
        try {
            // Đọc nội dung từ file và lưu vào biến fileContent
            fileContent = new String(Files.readAllBytes(Paths.get(PATH_DATA)));
            System.out.println("File content loaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    public ChatBotController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/ask")
    public String askGemini(@RequestBody Map<String, String> requestBody) {
        String question = requestBody.get("question"); // Nhận câu hỏi từ body request
        try {
            // Đọc nội dung file .txt
            // Truyền câu hỏi và nội dung file vào hàm của GeminiService
            // Gửi request tới Gemini và trả kết quả về
            return geminiService.sendRequestToGemini(question, fileContent);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}

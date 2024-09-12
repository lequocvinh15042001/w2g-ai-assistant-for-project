package com.w2g.ai_assistant_chatbot_website.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveFileException extends RuntimeException{
    private String message;
}
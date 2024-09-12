package com.w2g.ai_assistant_chatbot_website.service.imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileServiceImp {
    boolean saveFile(MultipartFile file);

    Resource load(String fileName);

    String readFile(String filePath) throws IOException;
}

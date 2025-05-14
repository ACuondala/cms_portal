package com.example.demo.Dtos;

import com.example.demo.Enums.StatusEnum;
import org.springframework.web.multipart.MultipartFile;

public record NewsRequestDto(String title, String description, StatusEnum status, MultipartFile file) {
}

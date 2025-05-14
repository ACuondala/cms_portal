package com.example.demo.Dtos;

import com.example.demo.Enums.StatusEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public record EventRequestDto(String title, String address, Date eventDate, StatusEnum status, String description, MultipartFile file) {
}

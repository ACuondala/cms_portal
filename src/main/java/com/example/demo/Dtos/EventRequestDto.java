package com.example.demo.Dtos;

import com.example.demo.Enums.StatusEnum;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;

public record EventRequestDto(String title, String address, LocalDate eventDate, StatusEnum status, String description, MultipartFile file) {
}

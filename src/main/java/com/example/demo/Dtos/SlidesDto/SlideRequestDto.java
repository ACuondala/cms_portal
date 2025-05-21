package com.example.demo.Dtos.SlidesDto;

import com.example.demo.Enums.StatusEnum;
import com.example.demo.Models.Events;
import com.example.demo.Models.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record SlideRequestDto(String title, String subTitle, MultipartFile file, StatusEnum status, UUID news, UUID events) {
}

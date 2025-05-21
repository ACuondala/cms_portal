package com.example.demo.Dtos.SlidesDto;

import com.example.demo.Dtos.EventDto;
import com.example.demo.Dtos.NewsDto;
import com.example.demo.Models.Events;
import com.example.demo.Models.News;

import java.util.UUID;

public record SlideDto(UUID id, String title, String subTitle, String file, String status, NewsDto news, EventDto events) {
}

package com.example.demo.Dtos;

import com.example.demo.Enums.StatusEnum;

import java.time.Instant;
import java.util.UUID;

public record NewsDto(UUID id, String title, String description, String status, String file, Instant createdAt) {
}

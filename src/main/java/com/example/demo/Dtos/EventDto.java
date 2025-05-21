package com.example.demo.Dtos;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record EventDto(UUID id, String title, String address, LocalDate eventDate, String description, String file, Instant createdAt) {
}

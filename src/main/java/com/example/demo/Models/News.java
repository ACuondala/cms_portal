package com.example.demo.Models;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="news")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class News {

    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    @Column(nullable = true)
    private String file;

    @Column(name="created_at")
    @CreationTimestamp
    private Instant createdAt;

}

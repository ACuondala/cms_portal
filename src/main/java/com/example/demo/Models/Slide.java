package com.example.demo.Models;

import com.example.demo.Enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "slides")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String subTitle;
    private String file;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @OneToOne
    @JoinColumn(name = "news_id", referencedColumnName="id", nullable=true)
    private News news;

    @OneToOne
    @JoinColumn(name="event_id", referencedColumnName="id",nullable=true)
    private Events event;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}

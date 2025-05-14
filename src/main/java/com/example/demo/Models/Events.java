package com.example.demo.Models;

import com.example.demo.Enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="events")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String address;

    @Column(name="event_date")
    private Date eventDate;

    private String description;

    private String file;

    private StatusEnum status;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
}

package com.krishna.library_management.entities;

import jakarta.persistence.*;
import lombok.*;



@Entity(name = "Books")
@Data
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ISBN;
    @Column(nullable = false)
    private String bookName;
    @Column(nullable = false)
    private String authorName;
    @Column(nullable = false)
    private Long bookPrice;
    @Column(nullable = false)
    private Integer stockQuantity;
}

package com.example.feedback.Entity;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "feedback")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;
    
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Description cannot be blank")
    @Column(name = "feedback_description")
    private String feedbackDescription;

    @NotNull(message = "Rating is required")
    @Column(name = "rating")
    private Integer rating;

    @NotBlank(message = "Category is required")
    @Column(name = "category")
    private String category;

    @Column(name = "feedback_date")
    private LocalDateTime feedbackDate;

    @PrePersist
    protected void onCreate() {
        if (feedbackDate == null) {
            feedbackDate = LocalDateTime.now(); // Set date
        }
        if (userId == null) {
            userId = 1L; // TEMPORARY dummy user ID, replace later when you integrate user module
        }
    }
}

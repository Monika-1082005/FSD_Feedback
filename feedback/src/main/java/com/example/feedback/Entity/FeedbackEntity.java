package com.example.feedback.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId; // Change to Integer for nullable handling

    // Matching the foreign key style from the user table
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
            feedbackDate = LocalDateTime.now(); // Set feedbackDate to current time if not provided
        }
    }

    // Getters and Setters
    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(LocalDateTime feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}

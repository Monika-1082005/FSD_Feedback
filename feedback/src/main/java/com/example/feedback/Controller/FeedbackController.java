package com.example.feedback.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.feedback.Entity.FeedbackEntity;
import com.example.feedback.Repository.FeedbackRepository;
import com.example.feedback.Service.FeedbackService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
// @RequestMapping("/feedback/api")
@Tag(name = "Feedback Controller", description = "Contains REST services for feedback management")
public class FeedbackController {

    private static final Logger log = LoggerFactory.getLogger(FeedbackController.class);

    private final FeedbackRepository feedbackRepository;
    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackRepository feedbackRepository, FeedbackService feedbackService) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Feedback API";
    }

    @GetMapping("/test")
    @Operation(summary = "Test method")
    public String testMethod() {
        return "Hello Monika!";
    }

    @GetMapping("/feedbacks")
    public List<FeedbackEntity> getAllFeedbacks() {
        log.info("Fetching all feedback records");
        return feedbackRepository.findAll();
    }

    @GetMapping("/feedbacks/{id}")
    public ResponseEntity<FeedbackEntity> getFeedbackById(@PathVariable("id") Integer id) {
        Optional<FeedbackEntity> feedback = feedbackRepository.findById(id);
        return feedback.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null)); // Return 404 if not found
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitFeedback(@RequestBody FeedbackEntity feedback) {
        try {
            System.out.println("=== SUBMITTING FEEDBACK ===");
            System.out.println("Payload: " + feedback);

            if (feedback.getUserId() == null) {
                feedback.setUserId(1L); // Default user ID
            }
            feedback.setFeedbackDate(LocalDateTime.now());

            FeedbackEntity savedFeedback = feedbackService.saveFeedback(feedback);
            return ResponseEntity.ok(savedFeedback);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error submitting feedback: " + e.getMessage());
        }
    }

    @PutMapping("/feedbacks/{id}")
    public String updateFeedback(@PathVariable("id") Integer id, @RequestBody FeedbackEntity feedback) {
        Optional<FeedbackEntity> existingFeedback = feedbackRepository.findById(id);

        if (existingFeedback.isPresent()) {
            feedback.setFeedbackId(id);
            feedbackRepository.save(feedback);
            log.info("Feedback with ID {} updated successfully", id);
            return "Feedback " + id + " updated successfully.";
        } else {
            feedbackRepository.save(feedback);
            log.info("Feedback with ID {} not found. Creating new feedback.", id);
            return "Feedback " + id + " not found. A new entry was created.";
        }
    }

    @DeleteMapping("/feedbacks/{id}")
    public String deleteFeedback(@PathVariable("id") Integer id) {
        log.info("Attempting to delete feedback with ID: {}", id);
        Optional<FeedbackEntity> feedback = feedbackRepository.findById(id);

        if (feedback.isPresent()) {
            feedbackRepository.deleteById(id);
            log.info("Feedback {} deleted successfully", id);
            return "Feedback " + id + " deleted successfully.";
        } else {
            log.warn("Feedback {} not found, cannot delete", id);
            return "Feedback " + id + " not found.";
        }
    }
}

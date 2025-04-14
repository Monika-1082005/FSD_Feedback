package com.example.feedback.service;

import com.example.feedback.Entity.FeedbackEntity;
import com.example.feedback.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Instance method to save feedback
    public FeedbackEntity saveFeedback(FeedbackEntity feedbackEntity) {
        return feedbackRepository.save(feedbackEntity); // No static reference here
    }
}

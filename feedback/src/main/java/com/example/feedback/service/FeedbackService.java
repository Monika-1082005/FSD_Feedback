package com.example.feedback.Service;

import com.example.feedback.Entity.FeedbackEntity;
import com.example.feedback.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public FeedbackEntity saveFeedback(FeedbackEntity feedbackEntity) {
        return feedbackRepository.save(feedbackEntity);
    }
}


package com.example.feedback.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feedback.Entity.FeedbackEntity;

public interface FeedbackRepository  extends JpaRepository<FeedbackEntity,Integer>{
    
}

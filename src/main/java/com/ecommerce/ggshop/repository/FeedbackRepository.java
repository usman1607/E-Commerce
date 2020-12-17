package com.ecommerce.ggshop.repository;

import com.ecommerce.ggshop.model.Feedback;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
    List<Feedback> findFeedbacksByCustomerId(long id);
}

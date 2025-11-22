package com.awais.Reviewms.Reviews.messaging;

import com.awais.Reviewms.Reviews.Review;
import com.awais.Reviewms.Reviews.dto.ReviewMessage;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Data
@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Review review) {
        ReviewMessage reviewMessage = new ReviewMessage();

        reviewMessage.setId(review.getId());
        reviewMessage.setTittle(review.getTittle());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setRating(review.getRating());
        reviewMessage.setCompanyId(review.getCompanyId());
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
    }
}

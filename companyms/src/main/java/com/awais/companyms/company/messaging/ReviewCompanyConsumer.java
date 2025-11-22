package com.awais.companyms.company.messaging;

import com.awais.companyms.company.CompanyService;
import com.awais.companyms.company.dto.ReviewMessage;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Data
public class ReviewCompanyConsumer {
    private final CompanyService companyService;

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {
        companyService.updateCompanyRating(reviewMessage);
    }
}

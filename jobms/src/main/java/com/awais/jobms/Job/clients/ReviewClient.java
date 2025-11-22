package com.awais.jobms.Job.clients;

import com.awais.jobms.Job.external.Company;
import com.awais.jobms.Job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Reviewms", url = "${review-service.url}")
public interface ReviewClient {
    @GetMapping("/reviews")
    List<Review> getCompanyReview(@RequestParam Long companyId);
}

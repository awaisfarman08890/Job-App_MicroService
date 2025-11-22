package com.awais.Reviewms.Reviews.impl;


import com.awais.Reviewms.Reviews.Review;
import com.awais.Reviewms.Reviews.ReviewReponsitory;
import com.awais.Reviewms.Reviews.ReviewService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewReponsitory reviewReponsitory;
//    private CompanyService companyService;
    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewReponsitory.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long CompanyId, Review review) {
        if(CompanyId != null && review != null) {
            review.setCompanyId(CompanyId);
            reviewReponsitory.save(review);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Review getReview(Long ReviewId) {
        return reviewReponsitory.findById(ReviewId).orElse(null);

    }

    @Override
    public boolean updateReview(Long ReviewId, Review updatedReview) {
        Review review = reviewReponsitory.findById(ReviewId).orElse(null);
        if(ReviewId != null) {
            review.setTittle(updatedReview.getTittle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewReponsitory.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewReponsitory.findById(reviewId).orElse(null);
        if(review != null) {
            reviewReponsitory.delete(review);
            return true;
        }
        return false;
    }
}

package com.awais.Reviewms.Reviews;

import java.util.List;

public interface ReviewService {
  List<Review> getAllReviews(Long companyId);
  boolean addReview(Long CompanyId, Review review);
  Review getReview(Long ReviewId);
  boolean updateReview(Long ReviewId, Review review);
  boolean deleteReview(Long reviewId);
}

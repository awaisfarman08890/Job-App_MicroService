package com.awais.Reviewms.Reviews;

import com.awais.Reviewms.Reviews.messaging.ReviewMessageProducer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewMessageProducer reviewMessageProducer;

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,
                                            @RequestBody Review review) {
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if (isReviewSaved) {
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review Added SuccessFully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Added SuccessFully", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(
                                                @PathVariable Long reviewId,
                                               @RequestBody Review review) {
        boolean isReviewSaved = reviewService.updateReview(reviewId, review);
        if (isReviewSaved) {
            return new ResponseEntity<>("Review Updated SuccessFully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Updated SuccessFully", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);
        if (isReviewDeleted) {
            return new ResponseEntity<>("Review Deleted SuccessFully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Deleted SuccessFully", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/averageRating")
    public Double getAverageRating(@RequestParam Long companyId) {
        List<Review> reviewList = reviewService.getAllReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }


}

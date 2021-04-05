package fr.rayane.repetitionreviewer.service;

import fr.rayane.repetitionreviewer.constant.QualityConstant;
import fr.rayane.repetitionreviewer.entity.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewAlgorithmService {

  public Review calculateNextReview(Review review) {
    if (review.quality >= QualityConstant.CORRECT_RESPONSE_DIFFICULT_TO_RECALL.ordinal()) {
      switch (review.repetitions) {
        case 0:
          review.interval = 1;
          break;
        case 1:
          review.interval = 6;
          break;
        default:
          review.interval = (int) Math.round(review.interval * review.easeFactor);
      }

      review.repetitions++;
      review.easeFactor =
          (review.easeFactor + (0.1 - (5 - review.quality) * (0.08 + (5 - review.quality) * 0.02)));
    } else {
      review.repetitions = 0;
      review.interval = 1;
    }

    if (review.easeFactor < 1.3) {
      review.easeFactor = 1.3;
    }

    return review;
  }
}

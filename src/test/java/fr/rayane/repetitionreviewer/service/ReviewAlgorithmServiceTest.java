package fr.rayane.repetitionreviewer.service;

import fr.rayane.repetitionreviewer.entity.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class ReviewAlgorithmServiceTest {

  static ReviewAlgorithmService reviewAlgorithmService = new ReviewAlgorithmService();

  @ParameterizedTest
  @ValueSource(ints = {3, 4, 5})
  public void firstReviewCorrectResponse(int quality) {
    // GIVEN
    Review firstReview = new Review(quality, 0, 1.3, 0);

    // WHEN
    Review newReview = reviewAlgorithmService.calculateNextReview(firstReview);

    // THEN
    assertThat(newReview.interval).isEqualTo(1);
    assertThat(newReview.repetitions).isEqualTo(1);
    assertThat(newReview.quality).isEqualTo(quality);
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2})
  public void firstReviewWrongResponse(int quality) {
    // GIVEN
    Review firstReview = new Review(quality, 0, 1.3, 0);

    // WHEN
    Review newReview = reviewAlgorithmService.calculateNextReview(firstReview);

    // THEN
    assertThat(newReview.interval).isEqualTo(1);
    assertThat(newReview.repetitions).isEqualTo(0);
    assertThat(newReview.quality).isEqualTo(quality);
  }

  @ParameterizedTest
  @ValueSource(ints = {3, 4, 5})
  public void verifyEaseFactorCalculationLowerThanMin(int quality) {
    // GIVEN
    Review firstReview = new Review(quality, 0, 1.10, 0);

    // WHEN
    Review newReview = reviewAlgorithmService.calculateNextReview(firstReview);

    // THEN
    assertThat(newReview.easeFactor).isEqualTo(1.3);
  }

  @ParameterizedTest
  @ValueSource(ints = {3, 4, 5})
  public void verifyEaseFactorCalculationOverMin(int quality) {
    // GIVEN
    // We choose a high easeFactor so that we are sure the formula doesn't make him inferior to 1.3
    Review firstReview = new Review(quality, 0, 5, 0);
    double newEaseFactor =
        (firstReview.easeFactor
            + (0.1 - (5 - firstReview.quality) * (0.08 + (5 - firstReview.quality) * 0.02)));

    // WHEN
    Review newReview = reviewAlgorithmService.calculateNextReview(firstReview);

    // THEN
    assertThat(newReview.easeFactor).isEqualTo(newEaseFactor);
  }

  @ParameterizedTest
  @ValueSource(ints = {3, 4, 5})
  public void secondReviewCorrectResponse(int quality) {
    // GIVEN
    Review secondReview = new Review(quality, 1, 1.3, 6);

    // WHEN
    Review newReview = reviewAlgorithmService.calculateNextReview(secondReview);

    // THEN
    assertThat(newReview.interval).isEqualTo(6);
    assertThat(newReview.repetitions).isEqualTo(2);
    assertThat(newReview.quality).isEqualTo(quality);
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2})
  public void secondReviewWrongResponse(int quality) {
    // GIVEN
    Review secondReview = new Review(quality, 1, 1.3, 6);

    // WHEN
    Review newReview = reviewAlgorithmService.calculateNextReview(secondReview);

    // THEN
    assertThat(newReview.interval).isEqualTo(1);
    assertThat(newReview.repetitions).isEqualTo(0);
    assertThat(newReview.quality).isEqualTo(quality);
  }

  @ParameterizedTest
  @ValueSource(ints = {3, 4, 5})
  public void overSecondReviewCorrectResponse(int quality) {
    // GIVEN
    Review overSecondReview = new Review(quality, 2, 1.3, 6);
    int newInterval = (int) Math.round(overSecondReview.interval * overSecondReview.easeFactor);

    // WHEN
    Review newReview = reviewAlgorithmService.calculateNextReview(overSecondReview);

    // THEN
    assertThat(newReview.interval).isEqualTo(newInterval);
    assertThat(newReview.repetitions).isEqualTo(3);
    assertThat(newReview.quality).isEqualTo(quality);
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2})
  public void overSecondReviewWrongResponse(int quality) {
    // GIVEN
    Review overSecondReview = new Review(quality, 2, 1.3, 6);

    // WHEN
    Review newReview = reviewAlgorithmService.calculateNextReview(overSecondReview);

    // THEN
    assertThat(newReview.interval).isEqualTo(1);
    assertThat(newReview.repetitions).isEqualTo(0);
    assertThat(newReview.quality).isEqualTo(quality);
  }
}

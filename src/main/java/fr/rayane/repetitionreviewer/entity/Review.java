package fr.rayane.repetitionreviewer.entity;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

@Data
public class Review {
  @Min(value = 0, message = "Quality has to be superior or eaquel to 0")
  @Max(value = 5, message = "Quality can't be superior to 5")
  int quality; // Answer quality. Should be between 0 and 5 and is referencing how good the review
  // was. Anything below 3 means an error

  @PositiveOrZero int repetitions; // How many times we reproduce a review with success

  @DecimalMin(value = "1.3", message = "Ease factor can't be inferior to 1.3")
  float easeFactor; // Used to determine the number of day between each review

  @PositiveOrZero int interval; // Interval before next review
}

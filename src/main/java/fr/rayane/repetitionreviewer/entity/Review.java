package fr.rayane.repetitionreviewer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
  @Min(value = 0, message = "Quality has to be superior or eaquel to 0")
  @Max(value = 5, message = "Quality can't be superior to 5")
  public int
      quality; // Answer quality. Should be between 0 and 5 and is referencing how good the review
  // was. Anything below 3 means an error

  @PositiveOrZero public int repetitions; // How many times we reproduce a review with success

  @PositiveOrZero
  public double easeFactor; // Used to determine the number of day between each review

  @PositiveOrZero public int interval; // Interval before next review in days
}

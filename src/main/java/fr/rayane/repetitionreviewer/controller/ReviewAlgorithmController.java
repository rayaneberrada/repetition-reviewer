package fr.rayane.repetitionreviewer.controller;

import fr.rayane.repetitionreviewer.entity.Review;
import fr.rayane.repetitionreviewer.exception.WrongRevueValues;
import fr.rayane.repetitionreviewer.service.ReviewAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/calculator")
public class ReviewAlgorithmController {

  @Autowired ReviewAlgorithmService reviewAlgorithmService;

  @PostMapping("/nextReview")
  @ResponseBody
  public Review calculateNextReview(@Valid @RequestBody Review review, BindingResult result)
      throws WrongRevueValues {
    if (result.hasErrors()) {
      throw new WrongRevueValues();
    }

    return reviewAlgorithmService.calculateNextReview(review);
  }
}

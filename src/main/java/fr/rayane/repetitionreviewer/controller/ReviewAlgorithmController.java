package fr.rayane.repetitionreviewer.controller;

import fr.rayane.repetitionreviewer.entity.Review;
import fr.rayane.repetitionreviewer.exception.WrongRevueValues;
import fr.rayane.repetitionreviewer.service.ReviewAlgorithmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Api(value = "Controller to apply calculation on reviews")
@RestController
@RequestMapping("/calculator")
public class ReviewAlgorithmController {

  @Autowired ReviewAlgorithmService reviewAlgorithmService;

  @ApiOperation(value = "Receive a Review and calculte when the next review should occur")
  @PostMapping(
      value = "/nextReview",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Review calculateNextReview(@Valid @RequestBody Review review, BindingResult result)
      throws WrongRevueValues {
    if (result.hasErrors()) {
      throw new WrongRevueValues();
    }

    return reviewAlgorithmService.calculateNextReview(review);
  }
}

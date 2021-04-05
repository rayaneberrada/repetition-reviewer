package fr.rayane.repetitionreviewer;

import fr.rayane.repetitionreviewer.controller.ReviewAlgorithmController;
import fr.rayane.repetitionreviewer.entity.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ReviewControllerIT {

  @Autowired ReviewAlgorithmController reviewAlgorithmController;

  @Autowired MockMvc mockMvc;

  @Test
  public void nextReviewSucceed() throws Exception {
    mockMvc
        .perform(
            post("/calculator/nextReview")
                .contentType("application/json")
                .content("{\"quality\":5, \"repetitions\":0, \"easeFactor\":1.3, \"interval\":0}"))
        .andExpect(status().isOk());
  }

  @Test
  public void nextReviewFail() throws Exception {
    mockMvc
        .perform(
            post("/calculator/nextReview")
                .contentType("application/json")
                .content("{\"quality\":5, \"repetitions\":0, \"easeFactor\":1.3, \"interval\":-1}"))
        .andExpect(status().isBadRequest());
  }
}

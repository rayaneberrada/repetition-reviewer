package fr.rayane.repetitionreviewer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WrongRevueValues extends ResponseStatusException {

  public WrongRevueValues() {
    super(
        HttpStatus.BAD_REQUEST,
        "Review need to have quality between 0 and 5, repetitions, ease factor and interval equal or superior to 0");
  }
}

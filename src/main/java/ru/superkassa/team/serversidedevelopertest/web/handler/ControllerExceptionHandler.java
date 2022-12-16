package ru.superkassa.team.serversidedevelopertest.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.superkassa.team.serversidedevelopertest.exceptions.UserNotFoundException;
import ru.superkassa.team.serversidedevelopertest.web.response.BaseWebResponse;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseWebResponse> handelException(Exception e) {

    log.error(e.getMessage());

    BaseWebResponse response = new BaseWebResponse(e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<BaseWebResponse> handelNotFoundException(Exception e) {

    log.error(e.getMessage());

    BaseWebResponse response = new BaseWebResponse(e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
}

package ru.superkassa.team.serversidedevelopertest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class TeapotException extends RuntimeException {

  public TeapotException() {

    super("418 I'm a teapot");
  }
}

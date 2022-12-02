package ru.superkassa.team.serversidedevelopertest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(Integer id) {

    super(String.format("%s%d%s", "User with id ", id, " doesn't exist"));
  }
}

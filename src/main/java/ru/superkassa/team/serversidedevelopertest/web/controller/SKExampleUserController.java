package ru.superkassa.team.serversidedevelopertest.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.superkassa.team.serversidedevelopertest.service.SKExampleUserService;
import ru.superkassa.team.serversidedevelopertest.web.request.SKExampleUserRequest;
import ru.superkassa.team.serversidedevelopertest.web.response.SKExampleUserResponse;
import ru.superkassa.team.serversidedevelopertest.web.response.SKExampleUserValueResponse;

@RestController
@RequestMapping("/api/v1/user")
public class SKExampleUserController {

  private final SKExampleUserService skExampleUserService;

  public SKExampleUserController(SKExampleUserService skExampleUserService) {

    this.skExampleUserService = skExampleUserService;
  }

  @PostMapping("/modify")
  public SKExampleUserValueResponse modifyUser(@RequestBody SKExampleUserRequest userRequest) {

    return skExampleUserService.modify(userRequest);
  }

  @GetMapping("/{id}")
  public SKExampleUserResponse getUser(@PathVariable("id") Integer id) {

    return skExampleUserService.getUser(id);
  }
}

package ru.superkassa.team.serversidedevelopertest.service;

import org.springframework.transaction.annotation.Transactional;
import ru.superkassa.team.serversidedevelopertest.web.request.SKExampleUserRequest;
import ru.superkassa.team.serversidedevelopertest.web.response.SKExampleUserResponse;
import ru.superkassa.team.serversidedevelopertest.web.response.SKExampleUserValueResponse;

public interface SKExampleUserService {

  @Transactional
  SKExampleUserValueResponse modify(SKExampleUserRequest userRequest);

  SKExampleUserResponse getUser(Integer id);
}

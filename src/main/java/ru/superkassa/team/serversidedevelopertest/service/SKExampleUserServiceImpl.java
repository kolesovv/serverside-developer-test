package ru.superkassa.team.serversidedevelopertest.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.superkassa.team.serversidedevelopertest.entity.SKExampleUser;
import ru.superkassa.team.serversidedevelopertest.exceptions.UserNotFoundException;
import ru.superkassa.team.serversidedevelopertest.repository.SKExampleUserRepository;
import ru.superkassa.team.serversidedevelopertest.web.request.SKExampleUserRequest;
import ru.superkassa.team.serversidedevelopertest.web.response.SKExampleUserResponse;
import ru.superkassa.team.serversidedevelopertest.web.response.SKExampleUserValueResponse;

@Slf4j
@Service
public class SKExampleUserServiceImpl implements SKExampleUserService {

  public static final String CURRENT = "current";
  private final SKExampleUserRepository userRepository;

  public SKExampleUserServiceImpl(SKExampleUserRepository userRepository) {

    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public SKExampleUserValueResponse modify(SKExampleUserRequest userRequest) {

    log.info("Got request body {}", userRequest);

    Integer id = userRequest.getId();
    SKExampleUser exampleUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

    Integer currentValueOfUser = exampleUser.getObj().get(CURRENT).intValue();
    Integer addValue = userRequest.getAdd();
    Integer newValueOfUser = increaseCurrentValue(currentValueOfUser, addValue);

    log.info("Increased value's user form {} to {}", currentValueOfUser, newValueOfUser);

    ((ObjectNode) exampleUser.getObj()).put(CURRENT, newValueOfUser);
    userRepository.save(exampleUser);

    SKExampleUserValueResponse response = SKExampleUserValueResponse.builder()
        .current(newValueOfUser)
        .build();

    log.info("Created response {}", response);
    return response;
  }

  @Override
  public SKExampleUserResponse getUser(Integer id) {

    log.info("Got user id {}", id);

    SKExampleUser exampleUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

    Integer currentValueOfUser = exampleUser.getObj().get(CURRENT).intValue();

    SKExampleUserResponse response = SKExampleUserResponse.builder()
        .id(id)
        .current(currentValueOfUser)
        .build();

    log.info("Created response {}", response);
    return response;
  }

  private Integer increaseCurrentValue(Integer currentValue, Integer addValue) {

    return currentValue + addValue;
  }
}

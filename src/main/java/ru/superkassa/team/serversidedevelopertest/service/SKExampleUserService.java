package ru.superkassa.team.serversidedevelopertest.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.superkassa.team.serversidedevelopertest.entity.SKExampleUser;
import ru.superkassa.team.serversidedevelopertest.exceptions.TeapotException;
import ru.superkassa.team.serversidedevelopertest.exceptions.UserNotFoundException;
import ru.superkassa.team.serversidedevelopertest.repository.SKExampleUserRepository;
import ru.superkassa.team.serversidedevelopertest.web.request.SKExampleUserRequest;
import ru.superkassa.team.serversidedevelopertest.web.response.SKExampleUserResponse;
import ru.superkassa.team.serversidedevelopertest.web.response.SKExampleUserValueResponse;

@Slf4j
@Service
public class SKExampleUserService {

  public static final String CURRENT = "current";
  private final SKExampleUserRepository userRepository;

  public SKExampleUserService(SKExampleUserRepository userRepository) {

    this.userRepository = userRepository;
  }

  @Transactional
  public SKExampleUserValueResponse modify(SKExampleUserRequest userRequest) {

    log.info("Got request body {}", userRequest);

    Integer id = userRequest.getId();
    SKExampleUser exampleUser = userRepository.findById(id)
        .orElseThrow(TeapotException::new);

    Integer currentValueOfUser = exampleUser.getObj()
        .get(CURRENT)
        .intValue();
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

  public SKExampleUserResponse getUser(Integer id) {

    log.info("Got user id {}", id);

    Optional<SKExampleUser> userOptional = userRepository.findById(id);

    if (userOptional.isPresent()) {
      SKExampleUser exampleUser = userOptional.get();
      Integer currentValueOfUser = exampleUser.getObj()
          .get(CURRENT)
          .intValue();

      SKExampleUserResponse response = SKExampleUserResponse.builder()
          .id(id)
          .current(currentValueOfUser)
          .build();
      log.info("Created response {}", response);
      return response;
    }

    throw new UserNotFoundException(id);
  }

  private Integer increaseCurrentValue(Integer currentValue, Integer addValue) {

    return currentValue + addValue;
  }
}

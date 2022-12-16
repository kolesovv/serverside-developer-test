package ru.superkassa.team.serversidedevelopertest.service;

import static ru.superkassa.team.serversidedevelopertest.service.SKExampleUserServiceImpl.CURRENT;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.superkassa.team.serversidedevelopertest.entity.SKExampleUser;
import ru.superkassa.team.serversidedevelopertest.exceptions.UserNotFoundException;
import ru.superkassa.team.serversidedevelopertest.repository.SKExampleUserRepository;
import ru.superkassa.team.serversidedevelopertest.web.request.SKExampleUserRequest;
import ru.superkassa.team.serversidedevelopertest.web.response.SKExampleUserResponse;

@ExtendWith(MockitoExtension.class)
class SKExampleUserServiceTest {

  private static final int USER_ID = 1;
  private static final ObjectNode JSON = new ObjectNode(JsonNodeFactory.instance).put(CURRENT, 150);
  SKExampleUser exampleUser = new SKExampleUser(USER_ID, JSON);

  @Captor
  ArgumentCaptor<SKExampleUser> userArgumentCaptor;
  @InjectMocks
  private SKExampleUserServiceImpl skExampleUserService;
  @Mock
  private SKExampleUserRepository skExampleUserRepository;

  @Test
  void modify_correctUserRequestFormIsGiven_fieldCurrentChangedByFieldAdd() {

    //GIVEN
    int randomValue = 100;
    SKExampleUserRequest request = SKExampleUserRequest.builder()
        .id(USER_ID)
        .add(randomValue)
        .build();

    Mockito.when(skExampleUserRepository.findById(request.getId()))
        .thenReturn(Optional.ofNullable(exampleUser));

    //WHEN
    skExampleUserService.modify(request);

    //THEN
    Mockito.verify(skExampleUserRepository).save(userArgumentCaptor.capture());

    int current = userArgumentCaptor.getValue().getObj().get(CURRENT).intValue();
    int expected = exampleUser.getObj().get(CURRENT).intValue();

    Assertions.assertThat(current).isEqualTo(expected);
  }

  @Test
  void modify_userDoesNotExists_userNotFoundExceptionIsThrown() {

    //GIVEN
    int randomValue = 100;
    SKExampleUserRequest request = SKExampleUserRequest.builder()
        .id(USER_ID)
        .add(randomValue)
        .build();

    Mockito.when(skExampleUserRepository.findById(request.getId())).thenReturn(Optional.empty());

    //WHEN
    RuntimeException thrown = (RuntimeException) Assertions.catchThrowable(() -> skExampleUserService.modify(request));

    //THEN
    Assertions.assertThat(thrown).isInstanceOf(UserNotFoundException.class);
    String errorMessage = String.format("%s%d%s", "User with id ", request.getId(), " doesn't exist");
    Assertions.assertThat(thrown.getMessage()).isEqualTo(errorMessage);
  }

  @Test
  void getUser_userIdIsGiven_userIsReturned() {

    //GIVEN
    Mockito.when(skExampleUserRepository.findById(USER_ID)).thenReturn(Optional.ofNullable(exampleUser));

    //WHEN
    SKExampleUserResponse userResponse = skExampleUserService.getUser(USER_ID);

    //THEN
    int current = exampleUser.getObj().get(CURRENT).intValue();

    Assertions.assertThat(userResponse.getId()).isEqualTo(exampleUser.getId());
    Assertions.assertThat(userResponse.getCurrent()).isEqualTo(current);
  }

  @Test
  void getUser_userDoesNotExists_userNotFoundExceptionIsThrown() {

    //GIVEN
    Mockito.when(skExampleUserRepository.findById(USER_ID)).thenReturn(Optional.empty());

    //WHEN
    RuntimeException thrown = (RuntimeException) Assertions.catchThrowable(() -> skExampleUserService.getUser(USER_ID));

    //THEN
    Assertions.assertThat(thrown).isInstanceOf(UserNotFoundException.class);

    String errorMessage = String.format("%s%d%s", "User with id ", USER_ID, " doesn't exist");
    Assertions.assertThat(thrown.getMessage()).isEqualTo(errorMessage);
  }
}

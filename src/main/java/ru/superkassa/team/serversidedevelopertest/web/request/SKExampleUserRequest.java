package ru.superkassa.team.serversidedevelopertest.web.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SKExampleUserRequest {

  private Integer id;
  private Integer add;
}

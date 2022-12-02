package ru.superkassa.team.serversidedevelopertest.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SKExampleUserResponse {

  private Integer id;
  private Integer current;
}

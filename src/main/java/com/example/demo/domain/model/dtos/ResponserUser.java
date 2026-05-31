package com.example.demo.domain.model.dtos;

import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record ResponserUser(String loginResponse) {
}

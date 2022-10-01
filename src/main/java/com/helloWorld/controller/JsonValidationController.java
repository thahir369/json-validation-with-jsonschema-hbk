package com.helloWorld.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloWorld.dto.Movie;
import com.helloWorld.service.JsonValidationService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JsonValidationController {

  private final JsonValidationService jsonValidationService;
  private final ObjectMapper objectMapper;

  @PostMapping("/validateJson")
  ResponseEntity<String> validateJsonWithJsonSchema(@RequestBody Movie movie) throws IOException {
    log.info("incoming json payload :: {}", objectMapper.writeValueAsString(movie));
    return ResponseEntity.ok(jsonValidationService.validateJsonWithJsonSchema(movie));
  }
}

package com.helloWorld.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.helloWorld.dto.Movie;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsonValidationServiceImplTest {

  @Autowired JsonValidationServiceImpl jsonValidationService;

  @Test
  void validateJsonWithJsonSchemaTest_With_JavaObject_Success() throws IOException {
    Movie movie =
        Movie.builder()
            .movieName("businessman")
            .director("puri")
            .producer("bandla")
            .release("2010")
            .genre("action")
            .cast(Arrays.asList("mahesh", "kajal", "brahmaji"))
            .build();
    String result = jsonValidationService.validateJsonWithJsonSchema(movie);
    assertEquals("No Errors in request payload", result);
  }

  @Test
  void validateJsonWithJsonSchemaTest_With_JavaObject_Exception() throws IOException {
    Movie movie =
        Movie.builder()
            .movieName("businessman")
            .director(null)
            .producer("bandla")
            .release("2010")
            .genre("action")
            .cast(Arrays.asList("mahesh", "kajal", "brahmaji"))
            .build();
    RuntimeException exception =
        assertThrows(
            RuntimeException.class, () -> jsonValidationService.validateJsonWithJsonSchema(movie));
    assertEquals(
        "Errors in the given json payload! \n" + "$.director: null found, string expected\n",
        exception.getMessage());
  }

  @Test
  void validateJsonWithJsonSchemaTest_With_JsonFile_Success() throws Exception {
    String result = jsonValidationService.validateJsonWithJsonSchema("jsonfiles/validPayload.json");
    assertEquals("No Errors in request payload", result);
  }

  @Test
  void validateJsonWithJsonSchemaTest_With_JsonFile_Exception() throws IOException {
    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () ->
                jsonValidationService.validateJsonWithJsonSchema("jsonfiles/invalidPayload.json"));
    assertEquals(
        "Errors in the given json payload! \n" + "$.director: null found, string expected\n",
        exception.getMessage());
  }
}

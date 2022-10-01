package com.helloWorld.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloWorld.dto.Movie;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JsonValidationServiceImpl implements JsonValidationService {

  private final ObjectMapper objectMapper;

  @Override
  public String validateJsonWithJsonSchema(Movie movie) throws IOException {

    String jsonAsString = objectMapper.writeValueAsString(movie);
    log.info("Json as String: {}", jsonAsString);

    InputStream schemaAsStream =
        getClass().getClassLoader().getResourceAsStream("jsonfiles/schema.json");
    log.info("schemaAsStream: " + schemaAsStream);

    JsonSchema schema =
        JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909).getSchema(schemaAsStream);

    // implementation with java object payload
    JsonNode jsonNode = objectMapper.readTree(jsonAsString);
    Set<ValidationMessage> errors = schema.validate(jsonNode);
    String errorsCombined = "";
    for (ValidationMessage error : errors) {
      log.error("Validation Error: {}", error);
      errorsCombined += error.toString() + "\n";
    }

    if (!errors.isEmpty()) {
      throw new RuntimeException("Errors in the given json payload! \n" + errorsCombined);
    }
    return ("No Errors in request payload");
  }

  @Override
  public String validateJsonWithJsonSchema(String payloadPath) throws IOException {
    InputStream schemaAsStream =
        getClass().getClassLoader().getResourceAsStream("jsonfiles/schema.json");
    log.info("schemaAsStream: " + schemaAsStream);

    InputStream payloadStream = getClass().getClassLoader().getResourceAsStream(payloadPath);
    log.info("payloadStream: " + payloadStream);

    JsonSchema schema =
        JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909).getSchema(schemaAsStream);

    // test with json file payload
    JsonNode jsonNode = objectMapper.readTree(payloadStream);
    Set<ValidationMessage> errors = schema.validate(jsonNode);
    String errorsCombined = "";
    for (ValidationMessage error : errors) {
      log.error("Validation Error: {}", error);
      errorsCombined += error.toString() + "\n";
    }

    if (!errors.isEmpty()) {
      throw new RuntimeException("Errors in the given json payload! \n" + errorsCombined);
    }
    return ("No Errors in request payload");
  }
}

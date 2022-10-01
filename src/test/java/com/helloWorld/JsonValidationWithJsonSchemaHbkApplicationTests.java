package com.helloWorld;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class JsonValidationWithJsonSchemaHbkApplicationTests {
  ObjectMapper om = new ObjectMapper();

  @Test
  void demo() throws IOException {

    InputStream schemaAsStream =
        getClass().getClassLoader().getResourceAsStream("jsonfiles/schema.json");
    log.info("schemaAsStream: " + schemaAsStream);

    InputStream payloadStream =
        getClass().getClassLoader().getResourceAsStream("jsonfiles/validPayload.json");
    log.info("payloadStream: " + payloadStream);

    JsonSchema schema =
        JsonSchemaFactory.getInstance(VersionFlag.V201909).getSchema(schemaAsStream);

    // test with json file payload
    JsonNode jsonNode = om.readTree(payloadStream);
    Set<ValidationMessage> errors = schema.validate(jsonNode);
    String errorsCombined = "";
    for (ValidationMessage error : errors) {
      log.error("Validation Error: {}", error);
      errorsCombined += error.toString() + "\n";
    }

    if (errors.size() > 0) {
      throw new RuntimeException("Please fix your json! \n" + errorsCombined);
    }
    log.info("No Errors in request payload");
  }
}

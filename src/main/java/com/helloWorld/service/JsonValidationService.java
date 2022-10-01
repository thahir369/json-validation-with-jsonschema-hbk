package com.helloWorld.service;

import com.helloWorld.dto.Movie;
import java.io.IOException;

public interface JsonValidationService {

  String validateJsonWithJsonSchema(Movie movie) throws IOException;

  String validateJsonWithJsonSchema(String payloadPath) throws IOException;
}

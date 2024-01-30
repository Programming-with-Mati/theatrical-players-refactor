package com.globant.javacodecamp.players.printers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.javacodecamp.players.model.Statement;

public class JsonStatementPrinter implements StatementPrinter {


  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Override
  public String print(Statement statement) {
    try {
      return OBJECT_MAPPER.writeValueAsString(statement);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}

package com.globant.javacodecamp.players.printers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.javacodecamp.players.model.Play;
import com.globant.javacodecamp.players.model.Statement;
import com.globant.javacodecamp.players.model.StatementLine;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonStatementPrinterTest {

  @Test
  void printStatement() throws JsonProcessingException {
    var statement = new Statement(
            "Mati",
            1000,
            50,
            List.of(
                    new StatementLine(new Play("Hamlet", "tragedy"), 1000, 40)
            )
    );

    var jsonStatementPrinter = new JsonStatementPrinter();
    var jsonStringStatement = jsonStatementPrinter.print(statement);
    var simpleTextStatementPrinter = new SimpleTextStatementPrinter();
    var simpleTextPrint = simpleTextStatementPrinter.print(statement);

    var objectMapper = new ObjectMapper();
    var actualStatement = objectMapper.readValue(jsonStringStatement, Statement.class);

    System.out.println(jsonStringStatement);
    System.out.println(simpleTextPrint);

    assertEquals(statement.customer(), actualStatement.customer());
    assertEquals(statement.totalAmount(), actualStatement.totalAmount());

  }
}

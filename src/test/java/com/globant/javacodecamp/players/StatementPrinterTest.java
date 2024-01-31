package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.Invoice;
import com.globant.javacodecamp.players.model.Performance;
import com.globant.javacodecamp.players.model.Play;
import com.globant.javacodecamp.players.model.Statement;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatementPrinterTest {

  @Test
  void statementForSinglePlay() {
    Map<String, Play> plays = Map.of(
            "othello", new Play("Othello", "tragedy"));

    Invoice invoice = new Invoice("BigCo", List.of(
            new Performance("othello", 40)));

    StatementPrinter statementPrinter = new StatementPrinter();
    var statement = StatementGenerator.generateStatement(plays, invoice);
    var actualStatement = statementPrinter.print(statement);

    var expectedStatement = """
            Statement for BigCo
              Othello: $500.00 (40 seats)
            Amount owed is $500.00
            You earned 10 credits""";

    assertEquals(expectedStatement, actualStatement);
  }
  @Test
  void statementForThreePlays() {
    Map<String, Play> plays = Map.of(
            "hamlet",  new Play("Hamlet", "tragedy"),
            "as-like", new Play("As You Like It", "comedy"),
            "othello", new Play("Othello", "tragedy"));

    Invoice invoice = new Invoice("BigCo", List.of(
            new Performance("hamlet", 55),
            new Performance("as-like", 35),
            new Performance("othello", 40)));

    StatementPrinter statementPrinter = new StatementPrinter();
    var statement = StatementGenerator.generateStatement(plays, invoice);
    var actualStatement = statementPrinter.print(statement);

    var expectedStatement = """
            Statement for BigCo
              Hamlet: $650.00 (55 seats)
              As You Like It: $580.00 (35 seats)
              Othello: $500.00 (40 seats)
            Amount owed is $1,730.00
            You earned 47 credits""";

    assertEquals(expectedStatement, actualStatement);
  }

  @Test
  void statementForThreePlaysForParticularType() {
    Map<String, Play> plays = Map.of(
            "hamlet",  new Play("Hamlet", "tragedy"),
            "as-like", new Play("As You Like It", "comedy"),
            "othello", new Play("Othello", "tragedy"));

    Invoice invoice = new Invoice("BigCo", List.of(
            new Performance("hamlet", 55),
            new Performance("as-like", 35),
            new Performance("othello", 40)));

    StatementPrinter statementPrinter = new StatementPrinter();
    var statement = StatementGenerator.generateStatementForSpecificPlayType(plays, invoice, "tragedy");
    var actualStatement = statementPrinter.print(statement);

    var expectedStatement = """
            Statement for BigCo
              Hamlet: $650.00 (55 seats)
              Othello: $500.00 (40 seats)
            Amount owed is $1,150.00
            You earned 35 credits""";

    assertEquals(expectedStatement, actualStatement);
  }

}

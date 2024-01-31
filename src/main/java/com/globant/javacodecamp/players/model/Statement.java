package com.globant.javacodecamp.players.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public record Statement(
        String customer,
        int totalAmount,
        int credits,
        List<StatementLine> statementLines
) {

  public static Statement empty(String customer) {
    return new Statement(customer,
            0,
            0,
            Collections.emptyList());
  }

  public Statement add(Statement other) {
    return new Statement(
            this.customer,
            this.totalAmount + other.totalAmount,
            this.credits + other.credits,
            Stream.concat(this.statementLines.stream(), other.statementLines.stream()).toList()
    );
  }
}

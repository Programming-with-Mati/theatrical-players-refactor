package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.*;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class StatementPrinter {

  public String print(Statement statement) {
    var printTemplate = """
            Statement for %s
            %s
            Amount owed is %s
            You earned %s credits""";

    var currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    return printTemplate.formatted(
            statement.customer(),

            statement.statementLines().stream()
                    .map(sl -> String.format("  %s: %s (%s seats)", sl.play().name, currencyFormatter.format(sl.amount() / 100), sl.audience()))
                    .collect(Collectors.joining("\n")),
            currencyFormatter.format(statement.totalAmount() / 100),
            statement.credits()
    );
  }


}

package com.globant.javacodecamp.players.printers;

import com.globant.javacodecamp.players.model.*;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleTextStatementPrinter {

    public String print(Statement statement) {
        var result = String.format("Statement for %s\n", statement.customer());

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        result += statement.statementLines().stream()
                .map(sl -> String.format("  %s: %s (%s seats)\n", sl.play().name(), frmt.format(sl.amount() / 100), sl.audience()))
                .collect(Collectors.joining());

        result += String.format("Amount owed is %s\n", frmt.format(statement.totalAmount() / 100));
        result += String.format("You earned %s credits", statement.credits());
        return result;
    }
}

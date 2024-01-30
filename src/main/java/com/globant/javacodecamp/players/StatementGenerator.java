package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.*;

import java.util.ArrayList;
import java.util.Map;

public class StatementGenerator {

  private final Map<String, PlayTypeAmountCalculator> amountCalculators = Map.of(
          "comedy", new ComedyAmountCalculator(),
          "tragedy", new TragedyAmountCalculator()
  );
  public Statement generateStatement(Invoice invoice, Map<String, Play> plays) {
    var totalAmount = 0;
    var volumeCredits = 0;
    var statementLines = new ArrayList<StatementLine>();
    for (var perf : invoice.performances) {
      var play = plays.get(perf.playID);
      volumeCredits += calculateCredits(perf, play);
      var statementLine = new StatementLine(play, calculateAmount(perf, play), perf.audience);
      statementLines.add(statementLine);

      // print line for this order
      totalAmount += statementLine.amount();
    }
    var statement = new Statement(invoice.customer, totalAmount, volumeCredits, statementLines);
    return statement;
  }

  private static int calculateCredits(Performance perf, Play play) {
    int thisCredits = 0;
    // add volume credits
    thisCredits += Math.max(perf.audience - 30, 0);
    // add extra credit for every ten comedy attendees
    if ("comedy".equals(play.type())) thisCredits += Math.floor(perf.audience / 5);
    return thisCredits;
  }

  private int calculateAmount(Performance perf, Play play) {
    var amountCalculator = amountCalculators.get(play.type());
    if(amountCalculator == null) throw new Error("unknown type: ${play.type}");

    return amountCalculator.calculateAmount(perf, play);
  }
}

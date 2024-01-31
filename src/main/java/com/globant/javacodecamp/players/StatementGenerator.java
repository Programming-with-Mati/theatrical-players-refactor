package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.*;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class StatementGenerator {

  public static Statement generateStatement(Map<String, Play> plays, Invoice invoice) {
    return generateStatementWithCondition(plays,
            invoice,
            play -> true);
  }

  public static Statement generateStatementForSpecificPlayType(Map<String, Play> plays, Invoice invoice, String playType) {
    return generateStatementWithCondition(plays,
            invoice,
            play -> play.type.equals(playType));
  }
  public static Statement generateStatementWithCondition(Map<String, Play> plays, Invoice invoice, Predicate<Play> condition) {
    return invoice.performances
            .stream()
            .filter(perf -> condition.test(plays.get(perf.playID)))
            .map(perf -> createStatementWithSingleLine(invoice, plays.get(perf.playID), perf))
            .reduce(Statement::add)
            .orElseGet(() -> Statement.empty(invoice.customer));
  }

  private static Statement createStatementWithSingleLine(Invoice invoice, Play play, Performance perf) {
    var statementLine = new StatementLine(play, AmountCalculator.calculateAmount(perf, play), perf.audience, calculateCredits(perf, play));
    return new Statement(invoice.customer, statementLine.amount(), statementLine.credits(), List.of(statementLine));
  }

  private static int calculateCredits(Performance perf, Play play) {
    var volumeCredits = Math.max(perf.audience - 30, 0);
    // add extra credit for every ten comedy attendees
    if ("comedy".equals(play.type)) volumeCredits += (double) (perf.audience / 5);
    return volumeCredits;
  }
}

package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.Performance;
import com.globant.javacodecamp.players.model.Play;

public class AmountCalculator {

  public static final int TRAGEDY_BASE_AMOUNT = 40_000;
  public static final int COMEDY_BASE_AMOUNT = 30_000;

  public static int calculateAmount(Performance perf, Play play) {
    return switch (play.type) {
      case "tragedy" -> TRAGEDY_BASE_AMOUNT + (perf.audience > 30 ? 1_000 * (perf.audience - 30) : 0);
      case "comedy" -> COMEDY_BASE_AMOUNT + 300 * perf.audience + (perf.audience > 20 ? 10_000 + 500 * (perf.audience - 20) : 0);
      default -> throw new IllegalStateException("Unexpected value: " + play.type);
    };
  }
}

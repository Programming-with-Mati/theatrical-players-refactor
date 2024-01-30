package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.Performance;
import com.globant.javacodecamp.players.model.Play;

public class ComedyAmountCalculator implements PlayTypeAmountCalculator {
  @Override
  public int calculateAmount(Performance perf, Play play) {
    var amount = 30000;
    if (perf.audience > 20) {
      amount += 10000 + 500 * (perf.audience - 20);
    }
    amount += 300 * perf.audience;
    return amount;
  }
}

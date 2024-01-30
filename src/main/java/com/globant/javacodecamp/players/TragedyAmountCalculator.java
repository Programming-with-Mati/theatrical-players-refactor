package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.Performance;
import com.globant.javacodecamp.players.model.Play;

public class TragedyAmountCalculator implements PlayTypeAmountCalculator {
  @Override
  public int calculateAmount(Performance perf, Play play) {
    var amount = 40000;
    if (perf.audience > 30) {
      amount += 1000 * (perf.audience - 30);
    }
    return amount;
  }
}

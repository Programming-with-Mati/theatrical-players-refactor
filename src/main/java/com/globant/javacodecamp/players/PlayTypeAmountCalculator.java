package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.Performance;
import com.globant.javacodecamp.players.model.Play;

public interface PlayTypeAmountCalculator {

  int calculateAmount(Performance perf, Play play);
}

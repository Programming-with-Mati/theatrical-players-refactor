package com.globant.javacodecamp.players.model;

public record StatementLine(
        Play play,
        int amount,
        int audience
) {
}

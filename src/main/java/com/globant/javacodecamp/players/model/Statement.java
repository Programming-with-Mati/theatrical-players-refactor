package com.globant.javacodecamp.players.model;

import java.util.List;

public record Statement(
        String customer,
        int totalAmount,
        int credits,
        List<StatementLine> statementLines
) {
}

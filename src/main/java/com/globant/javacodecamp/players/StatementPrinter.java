package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.Invoice;
import com.globant.javacodecamp.players.model.Play;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public List<String> print(List<Invoice> invoices, Map<String, Play> plays) {
        List<String> results = new ArrayList<>();
        for (Invoice invoice: invoices) {
            var totalAmount = 0;
            var volumeCredits = 0;
            var result = String.format("Statement for %s\n", invoice.customer);

            NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

            for (var perf : invoice.performances) {
                var play = plays.get(perf.playID);
                var thisAmount = 0;

                switch (play.type) {
                    case "tragedy":
                        thisAmount = 40000;
                        if (perf.audience > 30) {
                            thisAmount += 1000 * (perf.audience - 30);
                        }
                        break;
                    case "comedy":
                        thisAmount = 30000;
                        if (perf.audience > 20) {
                            thisAmount += 10000 + 500 * (perf.audience - 20);
                        }
                        thisAmount += 300 * perf.audience;
                        break;
                    default:
                        throw new Error("unknown type: ${play.type}");
                }

                // add volume credits
                volumeCredits += Math.max(perf.audience - 30, 0);
                // add extra credit for every ten comedy attendees
                if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

                // print line for this order
                result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
                totalAmount += thisAmount;
            }
            result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
            result += String.format("You earned %s credits", volumeCredits);
            results.add(result);
        }

        return results;
    }

}

package br.com.brunojdsz.api;

public record DailyShareQuote(
        String company,
        Float price,
        String datetime) {
}

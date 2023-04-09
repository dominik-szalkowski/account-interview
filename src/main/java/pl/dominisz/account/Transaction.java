package pl.dominisz.account;

import java.math.BigDecimal;

public record Transaction(String description, BigDecimal amount) {}

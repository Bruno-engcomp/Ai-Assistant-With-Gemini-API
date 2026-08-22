package org.example.intelligentapiwithspringai.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Transaction {
    private final TransactionId id;
    private final String description;
    private final BigDecimal amount;
    private final Category category;
    private final LocalDateTime date;

    // Fábrica estática para facilitar a criação de novas transações
    public static Transaction create(String description, BigDecimal amount, Category category) {
        return new Transaction(
                new TransactionId(),
                description,
                amount,
                category,
                LocalDateTime.now()
        );
    }
}
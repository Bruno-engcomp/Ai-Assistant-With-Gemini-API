package org.example.intelligentapiwithspringai.application.output;

import org.example.intelligentapiwithspringai.domain.Category;
import org.example.intelligentapiwithspringai.domain.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionOutput(
        String id,
        String description,
        BigDecimal amount,
        Category category,
        LocalDateTime date
) {
    // Método utilitário para converter a entidade de domínio no DTO de saída
    public static TransactionOutput fromDomain(Transaction transaction) {
        return new TransactionOutput(
                transaction.getId().value().toString(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getCategory(),
                transaction.getDate()
        );
    }
}
package org.example.intelligentapiwithspringai.infrastructure.http.response;

import org.example.intelligentapiwithspringai.application.output.TransactionOutput;
import org.example.intelligentapiwithspringai.domain.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        String id,
        String description,
        BigDecimal amount,
        Category category,
        LocalDateTime date
) {
    // Utilitário para converter do output do UseCase para a resposta HTTP
    public static TransactionResponse from(TransactionOutput output) {
        return new TransactionResponse(
                output.id(),
                output.description(),
                output.amount(),
                output.category(),
                output.date()
        );
    }
}
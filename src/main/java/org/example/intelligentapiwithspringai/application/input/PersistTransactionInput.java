package org.example.intelligentapiwithspringai.application.input;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.example.intelligentapiwithspringai.domain.Category;

import java.math.BigDecimal;

public record PersistTransactionInput(
        @JsonPropertyDescription("A descrição detalhada da transação")
        String description,

        @JsonPropertyDescription("O valor financeiro da transação")
        BigDecimal amount,

        @JsonPropertyDescription("A categoria em que a transação se enquadra")
        Category category
) {}
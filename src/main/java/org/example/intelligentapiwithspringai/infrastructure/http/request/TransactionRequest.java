package org.example.intelligentapiwithspringai.infrastructure.http.request;

import org.example.intelligentapiwithspringai.domain.Category;
import java.math.BigDecimal;

public record TransactionRequest(
        String description,
        BigDecimal amount,
        Category category
) {}
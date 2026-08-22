package org.example.intelligentapiwithspringai.domain;

import java.util.UUID;

public record TransactionId(UUID value) {
    public TransactionId() {
        this(UUID.randomUUID());
    }
}
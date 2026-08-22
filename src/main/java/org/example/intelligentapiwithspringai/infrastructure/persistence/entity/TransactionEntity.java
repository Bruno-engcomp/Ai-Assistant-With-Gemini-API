package org.example.intelligentapiwithspringai.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.intelligentapiwithspringai.domain.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    private UUID id;

    private String description;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDateTime date;
}
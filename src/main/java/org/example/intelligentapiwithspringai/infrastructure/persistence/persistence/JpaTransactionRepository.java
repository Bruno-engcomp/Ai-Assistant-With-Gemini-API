package org.example.intelligentapiwithspringai.infrastructure.persistence.persistence;

import lombok.RequiredArgsConstructor;
import org.example.intelligentapiwithspringai.domain.Category;
import org.example.intelligentapiwithspringai.domain.Transaction;
import org.example.intelligentapiwithspringai.domain.TransactionId;
import org.example.intelligentapiwithspringai.domain.TransactionRepository;
import org.example.intelligentapiwithspringai.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaTransactionRepository implements TransactionRepository {

    private final TransactionEntityRepository springDataRepository;

    @Override
    public Transaction save(Transaction transaction) {
        // 1. Converte do Domínio para o JPA
        TransactionEntity entity = toEntity(transaction);

        // 2. Salva no banco de dados usando o Spring Data
        TransactionEntity savedEntity = springDataRepository.save(entity);

        // 3. Converte de volta para o Domínio e retorna
        return toDomain(savedEntity);
    }

    @Override
    public List<Transaction> findByCategory(Category category) {
        return springDataRepository.findByCategory(category).stream()
                .map(this::toDomain) // Converte a lista do banco para lista de domínio
                .toList();
    }

    // --- Métodos Privados de Mapeamento ---

    private TransactionEntity toEntity(Transaction domain) {
        return TransactionEntity.builder()
                .id(domain.getId().value())
                .description(domain.getDescription())
                .amount(domain.getAmount())
                .category(domain.getCategory())
                .date(domain.getDate())
                .build();
    }

    private Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
                new TransactionId(entity.getId()),
                entity.getDescription(),
                entity.getAmount(),
                entity.getCategory(),
                entity.getDate()
        );
    }
}
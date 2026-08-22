package org.example.intelligentapiwithspringai.infrastructure.persistence.persistence;

import org.example.intelligentapiwithspringai.domain.Category;
import org.example.intelligentapiwithspringai.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, UUID> {

    // O Spring Data gera a query automaticamente com base no nome do método
    List<TransactionEntity> findByCategory(Category category);
}
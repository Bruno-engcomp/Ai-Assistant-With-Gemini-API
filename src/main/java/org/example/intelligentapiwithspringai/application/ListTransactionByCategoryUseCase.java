package org.example.intelligentapiwithspringai.application;

import lombok.RequiredArgsConstructor;
import org.example.intelligentapiwithspringai.application.output.TransactionOutput;
import org.example.intelligentapiwithspringai.domain.Category;
import org.example.intelligentapiwithspringai.domain.TransactionRepository;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service("listTransactionByCategoryUseCase")
@RequiredArgsConstructor
@Description("Ferramenta útil para buscar, listar ou consultar todas as transações financeiras filtradas por uma categoria específica.")
public class ListTransactionByCategoryUseCase implements Function<ListTransactionByCategoryUseCase.Input, List<TransactionOutput>> {

    private final TransactionRepository repository;

    @Override
    public List<TransactionOutput> apply(Input input) {
        return repository.findByCategory(input.category())
                .stream()
                .map(TransactionOutput::fromDomain)
                .toList();
    }

    // Usamos um record interno como DTO de entrada para facilitar o mapeamento
    // dos argumentos JSON que o Gemini vai gerar para o Spring AI.
    public record Input(Category category) {}
}
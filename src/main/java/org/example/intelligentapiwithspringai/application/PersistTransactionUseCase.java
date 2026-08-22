package org.example.intelligentapiwithspringai.application;

import lombok.RequiredArgsConstructor;
import org.example.intelligentapiwithspringai.application.input.PersistTransactionInput;
import org.example.intelligentapiwithspringai.application.output.TransactionOutput;
import org.example.intelligentapiwithspringai.domain.Transaction;
import org.example.intelligentapiwithspringai.domain.TransactionRepository;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service("persistTransactionUseCase")
@RequiredArgsConstructor
@Description("Ferramenta útil para salvar, registrar ou criar uma nova transação financeira (despesa ou receita) no banco de dados.")
public class PersistTransactionUseCase implements Function<PersistTransactionInput, TransactionOutput> {

    // O Lombok injeta o repositório automaticamente graças ao @RequiredArgsConstructor
    private final TransactionRepository repository;

    @Override
    public TransactionOutput apply(PersistTransactionInput input) {
        // 1. Cria a entidade de domínio usando a fábrica
        Transaction transaction = Transaction.create(
                input.description(),
                input.amount(),
                input.category()
        );

        // 2. Salva no banco de dados
        Transaction savedTransaction = repository.save(transaction);

        // 3. Retorna a resposta formatada
        return TransactionOutput.fromDomain(savedTransaction);
    }
}
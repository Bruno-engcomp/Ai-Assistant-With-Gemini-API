package org.example.intelligentapiwithspringai.infrastructure.http;

import lombok.RequiredArgsConstructor;
import org.example.intelligentapiwithspringai.application.ListTransactionByCategoryUseCase;
import org.example.intelligentapiwithspringai.application.PersistTransactionUseCase;
import org.example.intelligentapiwithspringai.application.input.PersistTransactionInput;
import org.example.intelligentapiwithspringai.domain.Category;
import org.example.intelligentapiwithspringai.infrastructure.http.request.TransactionRequest;
import org.example.intelligentapiwithspringai.infrastructure.http.response.TransactionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final PersistTransactionUseCase persistUseCase;
    private final ListTransactionByCategoryUseCase listUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@RequestBody TransactionRequest request) {
        var input = new PersistTransactionInput(
                request.description(),
                request.amount(),
                request.category()
        );

        var output = persistUseCase.apply(input);
        return TransactionResponse.from(output);
    }

    @GetMapping
    public List<TransactionResponse> listByCategory(@RequestParam Category category) {
        var input = new ListTransactionByCategoryUseCase.Input(category);

        return listUseCase.apply(input).stream()
                .map(TransactionResponse::from)
                .toList();
    }
}
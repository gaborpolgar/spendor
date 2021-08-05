package org.training360.spendor.transaction;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/spendor/transactions")
@Tag(name = "operations with transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionDto> listTransactions() {
        return transactionService.listTransactions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an transaction", description = "Create an transaction.")
    @ApiResponse(responseCode = "201", description = "transaction has been created")
    public TransactionDto createTransaction(@RequestBody CreateTransCommand command) {
        return transactionService.createTransaction(command);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get an transaction by id", description = "get an transaction by id.")
    public TransactionDto getTransactionById(@PathVariable("id") long id) {
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update an transaction", description = "Update an transaction.")
    @ApiResponse(responseCode = "404", description = "transaction not found")
    public TransactionDto updateTransaction(@PathVariable("id") long id, @RequestBody UpdateTransCommand command) {
        return transactionService.updateTransaction(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable("id") long id) {
        transactionService.deleteTransaction(id);
    }


}

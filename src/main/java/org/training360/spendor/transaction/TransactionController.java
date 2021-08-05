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
    public List<TransactionDto> getTransactions() {
        return transactionService.getTransactions();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create an transaction")
    @ApiResponse(responseCode = "201", description = "transaction has been created")
    public TransactionDto createTransaction(@RequestBody CreateTransCommand command) {
        return transactionService.createTransaction(command);
    }

    @PutMapping("/{id}")
    public TransactionDto updateTransaction(@PathVariable("id") long id, @RequestBody UpdateTransCommand command) {
        return transactionService.updateTransaction(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable("id") long id) {
        transactionService.deleteTransaction(id);
    }


}

package org.training360.spendor.transaction;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ApiResponse(responseCode = "200", description = "Transactions have been listed")
    @ApiResponse(responseCode = "400", description = "Bad request, transactions cannot be listed")
    public List<TransactionDto> listTransactions() {
        return transactionService.listTransactions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an transaction", description = "Create an transaction.")
    @ApiResponse(responseCode = "201", description = "transaction has been created")
    @ApiResponse(responseCode = "400", description = "Bad request, transaction cannot be created")
    public TransactionDto createTransaction(@RequestBody CreateTransCommand command) {
        return transactionService.createTransaction(command);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get an transaction by id", description = "get an transaction by id.")
    @ApiResponse(responseCode = "200", description = "Transaction has been found")
    @ApiResponse(responseCode = "400", description = "Bad request, transaction cannot be found")
    @ApiResponse(responseCode = "404", description = "Transaction has not been found")
    public TransactionDto getTransactionById(@PathVariable("id") long id) {
        return transactionService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update an transaction by id", description = "Update an transaction.")
    @ApiResponse(responseCode = "404", description = "transaction not found")
    public TransactionDto updateTransaction(@PathVariable("id") long id, @RequestBody UpdateTransCommand command) {
        return transactionService.updateTransaction(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "transaction deleted by id")
    @ApiResponse(responseCode = "204", description = "deleted transaction")
    @ApiResponse(responseCode = "400", description = "Bad request: Transaction can not be deleted")
    public void deleteTransaction(@PathVariable("id") long id) {
        transactionService.deleteTransaction(id);
    }

}

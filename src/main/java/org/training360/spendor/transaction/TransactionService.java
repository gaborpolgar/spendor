package org.training360.spendor.transaction;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService {

    private TransactionRepository repository;
    private ModelMapper modelMapper;

    public List<TransactionDto> listTransactions() {
        return repository.findAll().stream()
                .map(t -> modelMapper.map(t, TransactionDto.class))
                .collect(Collectors.toList());
    }

    public TransactionDto createTransaction(CreateTransCommand command) {
       Transaction transaction = new Transaction(command.getName(), command.getAmount(), command.getDate(), command.getLocation());
        repository.save(transaction);
        return modelMapper.map(transaction, TransactionDto.class);
    }

    @Transactional
    public TransactionDto updateTransaction(long id, UpdateTransCommand command) {
        Transaction transaction = repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        transaction.setName(command.getName());
        return modelMapper.map(transaction, TransactionDto.class);
    }

    public void deleteTransaction(long id) {
        repository.deleteById(id);
    }

    public TransactionDto getTransactionById(long id) {
        Transaction transaction = repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        return modelMapper.map(transaction, TransactionDto.class);
    }
}

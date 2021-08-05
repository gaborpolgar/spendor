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

    TransactionRepository repository;
    ModelMapper modelMapper;

    public List<TransactionDto> getTransactions() {
        return repository.findAll().stream()
                .map(t -> modelMapper.map(t, TransactionDto.class))
                .collect(Collectors.toList());
    }


}

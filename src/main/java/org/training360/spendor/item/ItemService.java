package org.training360.spendor.item;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.training360.spendor.transaction.Transaction;
import org.training360.spendor.transaction.TransactionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {

    private ModelMapper modelMapper;
    private ItemRepository itemRepository;
    private TransactionRepository transactionRepository;

    @Transactional
    ItemDto createItem(CreateItemCommand command) {
        Transaction transaction = transactionRepository.findById(command.getTransaction_id())
                .orElseThrow(() -> new IllegalArgumentException("Transaction with id: " + command.getTransaction_id() + " not found."));
        Item item = makeNewItem(command, transaction);
        itemRepository.save(item);
        return modelMapper.map(item, ItemDto.class);
    }

    private Item makeNewItem(CreateItemCommand command, Transaction transaction) {
        Item item = new Item(command.getName(), transaction);
        return item;
    }


    public List<ItemDto> listItems() {
        return itemRepository.findAll().stream()
                .map(t -> modelMapper.map(t, ItemDto.class))
                .collect(Collectors.toList());
    }

    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }
}
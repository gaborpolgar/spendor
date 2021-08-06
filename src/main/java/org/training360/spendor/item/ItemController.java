package org.training360.spendor.item;


import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/spendor/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping()
    ItemDto createItem(@Valid @RequestBody CreateItemCommand command) {
        return service.createItem(command);
    }

    @GetMapping()
    List<ItemDto> listItems() {
        return service.listItems();
    }

}

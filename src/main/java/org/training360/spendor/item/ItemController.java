package org.training360.spendor.item;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "item deleted by id")
    @ApiResponse(responseCode = "204", description = "deleted item")
    @ApiResponse(responseCode = "400", description = "Bad request: Item can not be deleted")
    public void deleteItem(@PathVariable("id") long id) {
       service.deleteItem(id);
    }

}

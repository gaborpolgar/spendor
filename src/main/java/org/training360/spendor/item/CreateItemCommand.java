package org.training360.spendor.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemCommand {

//    @NotBlank
    private String name;

//    @NotNull
    private Long transaction_id;
}

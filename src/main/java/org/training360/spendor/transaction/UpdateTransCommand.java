package org.training360.spendor.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransCommand {

    @NotNull
    @NotBlank
    @Schema(description = "name of the transaction", example = "szolgáltatás")
    private String name;
}

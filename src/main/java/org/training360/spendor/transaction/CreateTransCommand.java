package org.training360.spendor.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransCommand {

    @NotNull(message = "Transaction's name can not be nullable!")
    @NotBlank(message = "Transaction's name can not be blank!")
    @Schema(description = "name of the transaction", example = "nagybevásárlás")
    private String name;

    @NotNull
    @NotBlank
    @Schema(description = "amount of the transaction/purchase", example = "10000")
    private Long amount;

    @NotNull
    @NotBlank
    @Schema(description = "date of the transaction/purchase", example = "2021-03-01T22:00:00")
    private LocalDateTime date;

    @NotNull
    @NotBlank
    @Schema(description = "location of the transaction/purchase", example = "lidl")
    private String location;


}

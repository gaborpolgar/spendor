package org.training360.spendor.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransCommand {

    @Schema(description = "name of the transaction", example = "nagybevásárlás")
    private String name;

    @Schema(description = "amount of the transaction/purchase", example = "10000")
    private Long amount;

    @Schema(description = "date of the transaction/purchase", example = "2021-03-01T22:00:00")
    private LocalDateTime date;

    @Schema(description = "location of the transaction/purchase", example = "lidl")
    private String location;


}

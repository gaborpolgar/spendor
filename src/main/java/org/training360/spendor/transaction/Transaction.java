package org.training360.spendor.transaction;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank
//    @NotNull
    @Column(name = "transaction_name", nullable = false)
    private String name;

    private Long amount;

    private LocalDateTime date;

    private String location;

    public Transaction(String name, Long amount, LocalDateTime date, String location) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.location = location;
    }

//    @ManyToOne
//    private List<Item> items;


}

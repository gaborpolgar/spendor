package org.training360.spendor.transaction;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

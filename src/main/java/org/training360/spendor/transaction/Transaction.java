package org.training360.spendor.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.spendor.item.Item;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "transaction")
    private List<Item> items;

}

package com.devsu.llc.microaccountancy.model;

import com.devsu.llc.microaccountancy.enums.TypeMovement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "movement")
@AllArgsConstructor
@NoArgsConstructor
public class Movement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_movement")
    private TypeMovement typeMovement;
    private Double value;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "number_account", referencedColumnName = "number_account")
    private Account account;
}

package com.devsu.llc.microaccountancy.model;

import com.devsu.llc.microaccountancy.enums.State;
import com.devsu.llc.microaccountancy.enums.TypeAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number_account")
    private Integer numberAccount;
    private Long identification;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_account")
    private TypeAccount typeAccount;
    @Column(name = "amount_initial")
    private Double amountInitial;
    @Enumerated(EnumType.STRING)
    private State state;
}

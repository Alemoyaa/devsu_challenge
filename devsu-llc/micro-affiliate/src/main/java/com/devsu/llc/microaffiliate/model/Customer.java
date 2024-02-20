package com.devsu.llc.microaffiliate.model;

import com.devsu.llc.microaffiliate.enums.State;
import com.devsu.llc.microaffiliate.model.composite.CustomerId;
import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@IdClass(CustomerId.class)
@Table(name = "customer")
public class Customer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", unique = true)
    private Integer customerId;
    private String password;
    @Enumerated(EnumType.STRING)
    private State state;

}
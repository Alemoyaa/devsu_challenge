package com.devsu.llc.microaffiliate.model.composite;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerId implements Serializable {
    private Long identification;
    private Integer customerId;
}

package com.devsu.llc.microaffiliate.dto.response;

import com.devsu.llc.microaffiliate.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends PersonDto {
    private Integer customerId;
    private String password;
    private State state;
}

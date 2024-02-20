package com.devsu.llc.microaccountancy.dto.http;

import com.devsu.llc.microaccountancy.enums.State;
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
        private State state;
}

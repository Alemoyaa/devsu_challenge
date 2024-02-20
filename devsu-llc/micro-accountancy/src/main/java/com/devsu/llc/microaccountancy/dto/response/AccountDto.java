package com.devsu.llc.microaccountancy.dto.response;

import com.devsu.llc.microaccountancy.enums.State;
import com.devsu.llc.microaccountancy.enums.TypeAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Integer numberAccount;
    private Long identification;
    private TypeAccount typeAccount;
    private Double amountInitial;
    private State state;
}

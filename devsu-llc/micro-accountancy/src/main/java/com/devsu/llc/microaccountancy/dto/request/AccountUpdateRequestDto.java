package com.devsu.llc.microaccountancy.dto.request;

import com.devsu.llc.microaccountancy.enums.TypeAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateRequestDto {
    private TypeAccount typeAccount;
    private Double amountInitial;
}

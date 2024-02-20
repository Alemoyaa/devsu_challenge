package com.devsu.llc.microaccountancy.dto.request;

import com.devsu.llc.microaccountancy.enums.TypeAccount;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {
    @NotNull(message = "La identificación no puede estar vacía.")
    private Long identification;
    @NotNull(message = "El tipo de cuenta no puede estar vacío.")
    private TypeAccount typeAccount;
    @NotNull(message = "El monto inicial no puede estar vacío.")
    private Double amountInitial;
}

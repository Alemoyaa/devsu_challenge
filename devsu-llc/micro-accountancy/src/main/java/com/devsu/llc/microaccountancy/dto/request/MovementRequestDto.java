package com.devsu.llc.microaccountancy.dto.request;

import com.devsu.llc.microaccountancy.enums.TypeMovement;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementRequestDto {
    @NotNull(message = "El tipo de movimiento no puede estar vacío.")
    private TypeMovement typeMovement;
    @NotNull(message = "El valor no puede estar vacío.")
    private Double value;
    @NotNull(message = "El número de cuenta no puede estar vacío.")
    private Integer numberAccount;
}

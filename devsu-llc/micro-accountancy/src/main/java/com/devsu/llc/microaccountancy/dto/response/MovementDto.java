package com.devsu.llc.microaccountancy.dto.response;

import com.devsu.llc.microaccountancy.enums.TypeMovement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementDto {
    private Integer id;
    private Date date;
    private TypeMovement typeMovement;
    private Double value;
    private Double amount;
    private Integer numberAccount;
}

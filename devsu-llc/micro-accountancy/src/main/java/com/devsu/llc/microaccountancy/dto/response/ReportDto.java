package com.devsu.llc.microaccountancy.dto.response;

import com.devsu.llc.microaccountancy.dto.http.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private CustomerDto customer;
    private HashMap<AccountExtendDto, List<MovementDto>> accounts;
}

package com.devsu.llc.microaffiliate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequestDto {
    private String password;
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String phone;
}

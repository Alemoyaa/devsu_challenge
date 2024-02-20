package com.devsu.llc.microaccountancy.dto.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private Long identification;
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String phone;
}

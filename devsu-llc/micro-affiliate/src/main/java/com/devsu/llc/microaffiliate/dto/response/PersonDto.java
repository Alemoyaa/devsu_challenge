package com.devsu.llc.microaffiliate.dto.response;

import lombok.Data;

@Data
public class PersonDto {
    private Long identification;
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String phone;
}

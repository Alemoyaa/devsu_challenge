package com.devsu.llc.microaffiliate.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    @NotNull(message = "La identificación es requerida")
    private Long identification;
    @NotEmpty(message = "La contraseña es requerida")
    private String password;
    @NotEmpty(message = "El nombre es requerido")
    private String name;
    @NotEmpty(message = "El género es requerido")
    private String gender;
    private Integer age;
    @NotEmpty(message = "La dirección es requerida")
    private String address;
    private String phone;
}

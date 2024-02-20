package com.devsu.llc.microaffiliate.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class Person implements Serializable {

    @Id
    private Long identification;
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String phone;

}
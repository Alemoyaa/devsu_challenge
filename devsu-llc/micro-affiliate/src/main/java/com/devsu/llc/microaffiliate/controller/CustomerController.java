package com.devsu.llc.microaffiliate.controller;

import com.devsu.llc.microaffiliate.dto.request.CustomerRequestDto;
import com.devsu.llc.microaffiliate.dto.request.CustomerUpdateRequestDto;
import com.devsu.llc.microaffiliate.dto.response.CustomerDto;
import com.devsu.llc.microaffiliate.dto.response.MessageDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerController {
    ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerRequestDto customer);

    ResponseEntity<CustomerDto> update(@PathVariable Long identification, @RequestBody CustomerUpdateRequestDto customer);

    ResponseEntity<MessageDto> delete(@PathVariable Long identification);

    ResponseEntity<CustomerDto> findById(@PathVariable Long identification);

    ResponseEntity<List<CustomerDto>> findAll();
}

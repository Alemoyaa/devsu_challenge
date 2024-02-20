package com.devsu.llc.microaffiliate.controller.impl;

import com.devsu.llc.microaffiliate.controller.CustomerController;
import com.devsu.llc.microaffiliate.dto.request.CustomerUpdateRequestDto;
import com.devsu.llc.microaffiliate.dto.response.CustomerDto;
import com.devsu.llc.microaffiliate.dto.response.MessageDto;
import com.devsu.llc.microaffiliate.dto.request.CustomerRequestDto;
import com.devsu.llc.microaffiliate.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class CustomerControllerImpl implements CustomerController {

    @Autowired
    private CustomerService customerService;

    @Override
    @PostMapping
    public ResponseEntity<CustomerDto> create(CustomerRequestDto customer) {
        return new ResponseEntity<>(this.customerService.create(customer), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(Long id, CustomerUpdateRequestDto customer) {
        return new ResponseEntity<>(this.customerService.update(id, customer), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(Long id) {
        return new ResponseEntity<>(this.customerService.delete(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(Long id) {
        return new ResponseEntity<>(this.customerService.findById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAll() {
        return new ResponseEntity<>(this.customerService.findAll(), HttpStatus.OK);
    }
}

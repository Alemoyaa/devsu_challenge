package com.devsu.llc.microaffiliate.service;

import com.devsu.llc.microaffiliate.dto.request.CustomerUpdateRequestDto;
import com.devsu.llc.microaffiliate.dto.response.CustomerDto;
import com.devsu.llc.microaffiliate.dto.response.MessageDto;
import com.devsu.llc.microaffiliate.dto.request.CustomerRequestDto;

import java.util.List;

public interface CustomerService {

    CustomerDto create(CustomerRequestDto customer);
    CustomerDto update(Long identification, CustomerUpdateRequestDto customer);
    MessageDto delete(Long identification);
    List<CustomerDto> findAll();
    CustomerDto findById(Long identification);

}

package com.devsu.llc.microaffiliate.service.impl;

import com.devsu.llc.microaffiliate.dto.request.CustomerUpdateRequestDto;
import com.devsu.llc.microaffiliate.dto.response.CustomerDto;
import com.devsu.llc.microaffiliate.dto.response.MessageDto;
import com.devsu.llc.microaffiliate.dto.request.CustomerRequestDto;
import com.devsu.llc.microaffiliate.enums.State;
import com.devsu.llc.microaffiliate.exception.ResourceBadRequestException;
import com.devsu.llc.microaffiliate.exception.ResourceNotFoundException;
import com.devsu.llc.microaffiliate.model.Customer;
import com.devsu.llc.microaffiliate.repository.CustomerRepository;
import com.devsu.llc.microaffiliate.service.CustomerService;
import com.devsu.llc.microaffiliate.util.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String CUSTOMER_NOT_FOUND = "No existen clientes con esa identificación.";
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto create(CustomerRequestDto dto) {
        customerRepository.findByIdentification(dto.getIdentification()).ifPresent(customer -> {
            throw new ResourceBadRequestException("Ya existe un cliente con esa identificación.");
        });
        Customer customerModel = MapperService.map(dto, Customer.class);
        customerModel.setState(State.ACTIVE);
        return MapperService.map(customerRepository.save(customerModel), CustomerDto.class);
    }

    @Override
    public CustomerDto update(Long id, CustomerUpdateRequestDto dto) {
        Customer customerFind = customerRepository.findByIdentification(id).orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_NOT_FOUND));
        if (dto.getAge() != null)
            customerFind.setAge(dto.getAge());
        if (dto.getAddress() != null)
            customerFind.setAddress(dto.getAddress());
        if (dto.getGender() != null)
            customerFind.setGender(dto.getGender());
        if (dto.getName() != null)
            customerFind.setName(dto.getName());
        if (dto.getPhone() != null)
            customerFind.setPhone(dto.getPhone());
        if (dto.getPassword() != null)
            customerFind.setPassword(dto.getPassword());
        return MapperService.map(customerRepository.save(customerFind), CustomerDto.class);
    }

    @Override
    public MessageDto delete(Long id) {
        Customer customer = customerRepository.findByIdentification(id).orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_NOT_FOUND));
        if (customer.getState().equals(State.INACTIVE))
            throw new ResourceBadRequestException("El cliente ya ha sido eliminado.");
        customer.setState(State.INACTIVE);
        customerRepository.save(customer);
        return new MessageDto("Cliente eliminado con éxito.");
    }

    @Override
    public List<CustomerDto> findAll() {
        return MapperService.mapAll(customerRepository.findAll(), CustomerDto.class);
    }

    @Override
    public CustomerDto findById(Long id) {
        return MapperService.map(customerRepository.findByIdentification(id).orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_NOT_FOUND)), CustomerDto.class);
    }
}

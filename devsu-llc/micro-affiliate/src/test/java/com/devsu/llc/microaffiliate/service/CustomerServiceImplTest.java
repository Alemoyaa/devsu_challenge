package com.devsu.llc.microaffiliate.service;

import com.devsu.llc.microaffiliate.dto.request.CustomerRequestDto;
import com.devsu.llc.microaffiliate.dto.request.CustomerUpdateRequestDto;
import com.devsu.llc.microaffiliate.dto.response.CustomerDto;
import com.devsu.llc.microaffiliate.dto.response.MessageDto;
import com.devsu.llc.microaffiliate.enums.State;
import com.devsu.llc.microaffiliate.exception.ResourceNotFoundException;
import com.devsu.llc.microaffiliate.model.Customer;
import com.devsu.llc.microaffiliate.repository.CustomerRepository;
import com.devsu.llc.microaffiliate.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    Customer existingCustomer;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        existingCustomer = new Customer(1, "123456", State.ACTIVE);
    }

    @Test
    @DisplayName("Test to create a customer")
    void testCreate() {
        CustomerRequestDto requestDto = new CustomerRequestDto(42318968L, "123456", "John Doe", "Masculino", 25, "Calle 123", "123456789");
        when(customerRepository.save(any())).thenReturn(existingCustomer);
        when(customerRepository.findByIdentification(any())).thenReturn(Optional.empty());

        CustomerDto createdCustomer = customerService.create(requestDto);

        assertNotNull(createdCustomer);
        assertEquals("123456", createdCustomer.getPassword());
        assertEquals(State.ACTIVE, createdCustomer.getState());
    }

    @Test
    @DisplayName("Test to create a customer with an existing identification")
    void testUpdate() {
        Long customerId = 1L;
        CustomerUpdateRequestDto updateDto = new CustomerUpdateRequestDto("123456", "John Doe", "Masculino", 25, "Calle 123", "123456789");
        when(customerRepository.findByIdentification(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any())).thenReturn(existingCustomer);

        CustomerDto updatedCustomer = customerService.update(customerId, updateDto);

        assertNotNull(updatedCustomer);
        assertEquals("123456", updatedCustomer.getPassword());
        assertEquals(State.ACTIVE, updatedCustomer.getState());
        assertEquals("John Doe", updatedCustomer.getName());
    }

    @Test
    @DisplayName("Test to delete a customer")
    void testDelete() {
        Long customerId = 1L;
        when(customerRepository.findByIdentification(customerId)).thenReturn(Optional.of(existingCustomer));

        MessageDto result = customerService.delete(customerId);

        assertNotNull(result);
        assertEquals("Cliente eliminado con éxito.", result.getMessage());
    }

    @Test
    @DisplayName("Test to find all customers")
    void testFindAll() {
        List<Customer> listCustomer = new ArrayList<>();
        listCustomer.add(new Customer(1, "123456", State.ACTIVE));
        listCustomer.add(new Customer(2, "123457", State.ACTIVE));

        when(customerRepository.findAll()).thenReturn(listCustomer);
        List<CustomerDto> customers = customerService.findAll();

        assertNotNull(customers);
        assertEquals(2, customers.size());
    }

    @Test
    @DisplayName("Test to find a customer by id")
    void testFindById() {
        Long customerId = 1L;
        when(customerRepository.findByIdentification(customerId)).thenReturn(Optional.of(existingCustomer));

        CustomerDto foundCustomer = customerService.findById(customerId);

        assertNotNull(foundCustomer);
        assertEquals("123456", foundCustomer.getPassword());
        assertEquals(State.ACTIVE, foundCustomer.getState());
    }

    @Test
    @DisplayName("Test not find a customer by id")
    void notFindCustomerById() {
        assertThrows(ResourceNotFoundException.class, () -> customerService.findById(1L));
    }
}

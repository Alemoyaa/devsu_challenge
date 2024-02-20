package com.devsu.llc.microaffiliate.repository;

import com.devsu.llc.microaffiliate.model.Customer;
import com.devsu.llc.microaffiliate.model.composite.CustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, CustomerId> {
    Optional<Customer> findByIdentification(Long identification);

}

package com.devsu.llc.microaccountancy.client;

import com.devsu.llc.microaccountancy.configuration.CustomerFeignConfig;
import com.devsu.llc.microaccountancy.dto.http.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "server-affiliate", url = "http://localhost:8081/api/clientes", configuration = CustomerFeignConfig.class)
public interface CustomerClient {

    @GetMapping("/{id}")
    Optional<CustomerDto> getCustomerById(@PathVariable Long id);

}

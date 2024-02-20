package com.devsu.llc.microaccountancy.configuration;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class CustomerFeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

}

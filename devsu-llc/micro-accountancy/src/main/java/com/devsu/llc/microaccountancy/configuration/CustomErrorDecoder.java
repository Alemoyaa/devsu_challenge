package com.devsu.llc.microaccountancy.configuration;

import com.devsu.llc.microaccountancy.exception.ResourceNotFoundException;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, feign.Response response) {
        return switch (response.status()) {
            case 400, 404 -> {
                if (methodKey.contains("getCustomerById")) {
                    yield new ResourceNotFoundException("No existen clientes con esa identificación");
                }
                yield new ResourceNotFoundException("Recurso no encontrado");
            }
            default -> new Exception(response.reason());
        };
    }

}

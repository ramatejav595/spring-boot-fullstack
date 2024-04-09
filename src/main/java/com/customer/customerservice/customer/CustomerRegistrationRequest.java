package com.customer.customerservice.customer;

public record CustomerRegistrationRequest(
        String name,
        String email,
        Integer age
) {
}

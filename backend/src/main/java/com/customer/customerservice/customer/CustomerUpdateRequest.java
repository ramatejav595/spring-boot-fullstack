package com.customer.customerservice.customer;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {
}

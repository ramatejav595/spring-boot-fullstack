package com.customer.customerservice.customer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
    public List<Customer> getAllCustomers() {
        return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Integer id){
        return customerDAO.selectCustomerById(id).orElseThrow(
                ()-> new RuntimeException(
                                "Customer with id [%s] not found" .formatted(id)
        ));
    }
    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        //Check if email exists
    if(customerDAO.existsPersonWithEmail(customerRegistrationRequest.email())){
            throw new RuntimeException("Email exists");
    }
        Customer customer = new Customer( customerRegistrationRequest.name(),
                                          customerRegistrationRequest.email(),
                                          customerRegistrationRequest.age());

                                    customerDAO.insertCustomer(customer);
    }

    public void deleteCustomerById(Integer id){
        if(!customerDAO.existsPersonWithId(id)){
            throw new RuntimeException("Customer with Id not exist");
        }
        customerDAO.deleteCustomer(id);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest updateRequest) {

        Customer customer = getCustomer(customerId);
        boolean changes = false;
        if(updateRequest.name() != null && !updateRequest.name().equals(customer.getName())){
            customer.setName(updateRequest.name());
            changes=true;
        }
        if(updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())){
            customer.setAge(updateRequest.age());
            changes=true;
        }
        if(updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())){
            if(customerDAO.existsPersonWithEmail(updateRequest.email())){
                throw  new RuntimeException("email already taken");
            }
            customer.setEmail(updateRequest.email());
            changes=true;
        }
        if(!changes){
            throw new RuntimeException("No changes found");
        }

        customerDAO.updateCustomer(customer);
    }
}


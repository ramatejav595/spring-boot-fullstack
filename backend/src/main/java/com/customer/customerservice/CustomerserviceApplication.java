package com.customer.customerservice;

import com.customer.customerservice.customer.Customer;
import com.customer.customerservice.customer.CustomerRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class CustomerserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerserviceApplication.class, args);

    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){

        return  args -> {
            var faker = new Faker();
            Random random = new Random();
            Name name = faker.name();
            String firstname = name.firstName();
            String lastname = name.lastName();
            Customer customer = new Customer(firstname+ " " +lastname,
                    firstname.toLowerCase() + "." +lastname.toLowerCase() + "@gmail.com",
                    random.nextInt(16, 99));
            customerRepository.save(customer);
        };
    }
}

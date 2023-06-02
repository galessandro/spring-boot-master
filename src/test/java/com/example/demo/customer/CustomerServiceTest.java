package com.example.demo.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;
    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void getCustomers() {
        Customer german = new Customer(1L, "German", "Hello", "email@gmail.com");
        Customer mateo = new Customer(2L, "Mateo", "Hello", "email@gmail.com");
        customerRepository.saveAll(Arrays.asList(german, mateo));

        List<Customer> customers = underTest.getCustomers();

        assertEquals(2, customers.size());
    }

    @Test
    void getCustomer() {
        Customer german = new Customer(1L, "German", "Hello", "email@gmail.com");
        customerRepository.save(german);

        Customer customer = underTest.getCustomer(1L);

        assertEquals(1L, customer.getId());
        assertEquals("German", customer.getName());
        assertEquals("Hello", customer.getPassword());
        assertEquals("email@gmail.com", customer.getEmail());
    }
}
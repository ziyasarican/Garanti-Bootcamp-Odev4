package com.banking.odev4.service;

import com.banking.odev4.entity.Customer;
import com.banking.odev4.repository.CustomerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerEntityService {

    private CustomerRepository customerRepository;

    public CustomerEntityService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        customer = customerRepository.save(customer);
        return customer;
    }


    public Customer findById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElse(null);
    }


    public Customer findByIdentityNo(String identityNo) {
        Optional<Customer> customerOptional = customerRepository.findByIdentityNo(identityNo);
        return customerOptional.orElse(null);
    }

    public Customer findByEmail(String email){
        Customer customer = customerRepository.findByEmail(email);
        return customer;
    }
}

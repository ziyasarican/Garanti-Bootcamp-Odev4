package com.banking.odev4.controller;

import com.banking.odev4.entity.Customer;
import com.banking.odev4.repository.CustomerRepository;
import com.banking.odev4.service.CustomerEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController{

    private final CustomerRepository customerRepository;
    private CustomerEntityService customerEntityService;

    public CustomerController(CustomerEntityService customerEntityService, CustomerRepository customerRepository) {
        this.customerEntityService = customerEntityService;
        this.customerRepository = customerRepository;
    }


    @GetMapping("/all")
    public List<Customer> getAllCustomers(){
        Customer customer1 = new Customer();
        customer1.setName("ziya");
        customer1.setSurname("sarican");
        customer1.setIdentityNo("123");
        customer1.setPhoneNumber("543");
        customer1.setPassword("123");
        customer1.setEmail("zsrcn@gmail.com");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);

        return customerList;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        try {
            customerRepository.deleteById(id);
            return ResponseEntity.ok("Customer deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting customer");
        }
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer){
        customer = customerEntityService.save(customer);
        return customer;
    }

    @GetMapping("/id/{id}")
    public Customer findById(@PathVariable Long id){
        Customer customer = customerEntityService.findById(id);
        return customer;
    }

    @GetMapping("/identity-no/{identityNo}")
    public Customer findByIdentityNo(@PathVariable String identityNo){
        Customer customer = customerEntityService.findByIdentityNo(identityNo);
        return customer;
    }

    @GetMapping("/email/{email}")
    public Customer findByEmail(@PathVariable String email){
        Customer customer = customerEntityService.findByEmail(email);
        return customer;
    }

}

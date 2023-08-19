package com.banking.odev4.controller;

import com.banking.odev4.entity.Account;
import com.banking.odev4.entity.Customer;
import com.banking.odev4.repository.AccountRepository;
import com.banking.odev4.repository.CustomerRepository;
import com.banking.odev4.service.AccountEntityService;
import com.banking.odev4.service.CustomerEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountEntityService accountEntityService;
    private final CustomerEntityService customerEntityService;
    public AccountController(AccountRepository accountRepository, CustomerRepository customerRepository, AccountEntityService accountEntityService, CustomerEntityService customerEntityService) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountEntityService = accountEntityService;
        this.customerEntityService = customerEntityService;
    }

    @PostMapping
    public ResponseEntity<String> saveAccount(@RequestBody Account account) {
        try {
            Long customerId = account.getCustomerId();

            // Girilen customerId ile customer tablosundaki id değerlerinden biri eşleşiyor mu? null veya dolu değer döndürür
            Optional<Customer> customerOptional = Optional.ofNullable((customerEntityService.findById(customerId)));

            // customerOptional.isPresent() -> null veya not null döndürür
            if (!customerOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Customer not found.");
            }

            accountEntityService.save(account);

            return ResponseEntity.ok("Account created.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occured.");
        }
    }




    //olmayan id için de customer deleted çıktısı veriyor
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        try{
            accountRepository.deleteById(id);
            return ResponseEntity.ok("Customer deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting customer");
        }
    }

    @GetMapping("/find-all-account-by-customer-id/{customerId}")
    public List<Account> findAllAccountsByCustomerId(@PathVariable Long customerId){
        Optional<Customer> customerOptional = Optional.ofNullable(customerEntityService.findById(customerId));

        if (customerOptional.isPresent()) {
            return accountRepository.findByCustomerId(customerId);
        } else {
            return null;
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestParam Long amount,@RequestParam Long id){
        Optional<Account> accountOptional = accountEntityService.findById(id);

        if(!accountOptional.isPresent()){
            return ResponseEntity.ok("Account does not found.");
        }

        Account account = accountOptional.get();
        Long currentBalance = account.getCurrentBalance();
        if(currentBalance >= amount){
            account.setCurrentBalance(currentBalance - amount);
            accountEntityService.save(account);
            return ResponseEntity.ok("Current Balance: " + currentBalance + "\n"
                    + "Withdraw Amount: " + amount + "\n"
                    + "New Balance: " + (currentBalance-amount));
        } else {
            return ResponseEntity.ok("Unsufficient balance");
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestParam Long amount,@RequestParam Long id){

        Optional<Account> accountOptional = accountEntityService.findById(id);
        if(!accountOptional.isPresent()){
            return ResponseEntity.ok("Account does not found.");
        }
        Account account = accountOptional.get();
        Long currentBalance = account.getCurrentBalance();

        account.setCurrentBalance(currentBalance + amount);
        accountEntityService.save(account);
        return ResponseEntity.ok("Current Balance: " + currentBalance + "\n"
                + "Deposit Amount: " + amount + "\n"
                + "New Balance: " + (currentBalance+amount));
    }


}

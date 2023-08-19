package com.banking.odev4.service;

import com.banking.odev4.entity.Account;
import com.banking.odev4.entity.Customer;
import com.banking.odev4.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountEntityService {

    private AccountRepository accountRepository;

    public AccountEntityService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account){
        account = accountRepository.save(account);
        return account;
    }


    public Optional<Account> findById(Long accountId) {
        Optional<Account> customerOptional = accountRepository.findById(accountId);
        return customerOptional;
    }
}

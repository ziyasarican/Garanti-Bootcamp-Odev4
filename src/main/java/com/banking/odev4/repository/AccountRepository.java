package com.banking.odev4.repository;

import com.banking.odev4.entity.Account;
import com.banking.odev4.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long>{


    Optional<List<Customer>> findAllByCustomerId(Long customerId);

    @Query("select a from Account a where a.ibanNo = : ibanNo")
    Optional<Account> findByIbanNo(String ibanNo);

    Account save (Account account);
    List<Account> findByCustomerId(Long customerId);

}

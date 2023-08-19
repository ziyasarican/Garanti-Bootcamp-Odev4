package com.banking.odev4.repository;

import com.banking.odev4.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByIdentityNo(String identityNo);

    // her e-mail bir kullanıcıya ait olacağı için List tipinde değil
    @Query("select c from Customer c where c.email = :email")
    Customer findByEmail(@Param("email") String email);

    //Account için post sorgusu atarken ilgili customerId değişkeni, customer tablosunda bir id ile örtüşüyor mu?
    Optional<Customer> findById(Long id);

}

package com.banking.odev4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Account")
    @SequenceGenerator(name = "Account", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
    private Long id;

    /* DB tarafında left join ile birleştirme işlemi
    @JoinColumn(name = "ID_CUSTOMER")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
     */

    @Column(name = "customer id", length = 100, nullable = false)
    private Long customerId;

    @Column(name = "iban no", unique = true, length = 20, nullable = false)
    private String ibanNo;

    @Column(name = "current balance", length = 1000)
    private Long currentBalance;


    @Enumerated(EnumType.STRING)
    @Column(name = "currency_type", length = 10, nullable = false)
    private CurrencyTypeEnum currencyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "account type", length = 10, nullable = false)
    private AccountTypeEnum accountTypeEnum;

    public enum AccountTypeEnum {
        DRAWING,
        DEPOSIT
    }
    public enum CurrencyTypeEnum {
        TL,
        EURO,
        DOLLAR
    }


}

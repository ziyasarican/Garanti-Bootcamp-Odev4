package com.banking.odev4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Customer")
    @SequenceGenerator(name = "Customer", sequenceName = "CUSTOMER_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "surname", length = 50, nullable = false)
    private String surname;

    @Column(name = "identity no", unique = true, length = 11, nullable = false)
    private String identityNo;

    @Column(name = "password", length = 20, nullable = false)
    private  String password;

    @Column(name = "email", unique = true,  length = 30, nullable = false)
    private String email;

    @Column(name = "phone number", length = 11, nullable = false)
    private String phoneNumber;
}

Bankacılık uygulaması:
Bir spring boot application oluşturunuz.
1. Customer ve Account adında 2 tane entity tanımlayın.
   Customer: - Id,
- name,
- surname,
- identityNo,
- password,
- email,
- phoneNumber
  Account:
- id,
- customerId,
- ibanNo,
- currentBalance,
- currencyTypeEnum( TL,EURO,DOLLAR,),
- accountTypeEnum(DRAWING,DEPOSIT)
2. CustomerRepository oluşturunuz.
- findByIdentityNo (query yazmadan)
- findByEmail (query yazarak)
3. AccountRepository oluşturunuz.
- findAllByCustomerId(query yazmadan)
- findByIbanNo(query yazarak)
4. Bu entity lerin entityservice lerini oluşturunuz.
5. CustomerController oluşturunuz.
- saveCustomer
- deleteCustomer - findById
- findByIdentityNo
6. AccountController oluşturunuz. - saveAccount
- deleteAccount
- findAllAccountsByCustomerId - WithdrawMoney
- DepositMoney
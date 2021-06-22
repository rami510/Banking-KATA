package com.kata.banking.service.api;

import com.kata.banking.model.Account;

import java.util.Optional;


public interface AccountService {

    void createAccount(Account account);

    Optional<Account> getAccountById(String id);
}

package com.kata.banking.service.impl;

import com.kata.banking.model.Account;
import com.kata.banking.service.api.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

public class AccountServiceImpl implements AccountService {

    private static AccountService instance;

    private AccountServiceImpl() {
    }

    public static AccountService getInstance() {
        if (isNull(instance))
            instance = new AccountServiceImpl();
        return instance;
    }

    // we should delegate data input output to a repository layer (no database is needed on our case we chose to make it simple)
    private List<Account> accountList = new ArrayList<>();

    @Override
    public void createAccount(Account account) {
        Optional<Account> existingAccount = this.getAccountById(account.getId());
        if (existingAccount.isPresent())
            throw new RuntimeException("Account exist");

        this.accountList.add(account);
    }

    @Override
    public Optional<Account> getAccountById(String accountId) {
        return this.accountList.stream().filter((a) -> a.getId().equals(accountId)).findFirst();
    }
}

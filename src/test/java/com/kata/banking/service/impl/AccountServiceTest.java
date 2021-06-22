package com.kata.banking.service.impl;

import com.kata.banking.model.Account;
import com.kata.banking.service.api.AccountService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class AccountServiceTest {

    private AccountService accountService;

    @Before
    public void createServices() {
        this.accountService = AccountServiceImpl.getInstance();
    }

    @Test
    public void should_create_account() {
        //GIVEN
        String accountId = UUID.randomUUID().toString();
        Account actual = buildAccount(accountId);
        //WHEN
        accountService.createAccount(actual);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_account_exist() {
        //GIVEN
        String accountId = UUID.randomUUID().toString();
        Account actual = buildAccount(accountId);

        //THEN
        accountService.createAccount(actual);
        accountService.createAccount(actual);
    }

    @Test
    public void should_return_account() {
        //GIVEN
        String accountId = UUID.randomUUID().toString();
        Account account = buildAccount(accountId);
        accountService.createAccount(account);

        //WHEN
        Optional<Account> actual = accountService.getAccountById(accountId);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(actual.get().getId(), account.getId());
    }

    @Test
    public void should_return_empty_when_account_not_exist() {
        //WHEN
        Optional<Account> actual = accountService.getAccountById("account 1");

        //THEN
        assertFalse(actual.isPresent());
    }

    private Account buildAccount(String accountId) {
        return Account.builder().id(accountId).build();
    }
}

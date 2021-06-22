package com.kata.banking.service.api;

import com.kata.banking.model.Account;
import com.kata.banking.model.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface OperationService {
    void withdraw(Account account, BigDecimal amount);

    void deposit(Account account, BigDecimal amount);

    List<Operation> getAccountOperationHistory(String accountId);

    BigDecimal getBalanceByAccountId(String accountId);
}

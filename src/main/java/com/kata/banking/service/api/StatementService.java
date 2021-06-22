package com.kata.banking.service.api;

import com.kata.banking.model.Account;

import java.io.PrintStream;

public interface StatementService {
    void printStatement(Account account, PrintStream stream);
}

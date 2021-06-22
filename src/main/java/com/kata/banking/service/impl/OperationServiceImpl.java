package com.kata.banking.service.impl;

import com.kata.banking.model.Account;
import com.kata.banking.model.Operation;
import com.kata.banking.service.api.OperationService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class OperationServiceImpl implements OperationService {

    private final List<Operation> operationList;
    private static OperationService instance;

    private OperationServiceImpl() {
        this.operationList = new ArrayList<>();
    }

    public static OperationService getInstance() {
        if (isNull(instance))
            instance = new OperationServiceImpl();
        return instance;
    }


    //US 1
    @Override
    public void withdraw(Account account, BigDecimal amount) {
        transaction(account, amount, Operation.OperationType.WITHDRAW);
    }

    //US 1
    @Override
    public void deposit(Account account, BigDecimal amount) {
        transaction(account, amount, Operation.OperationType.DEPOSIT);
    }

    //US 2
    @Override
    public List<Operation> getAccountOperationHistory(String accountId) {
        return operationList.stream().filter((operation) -> operation.getAccountId().equals(accountId)).sorted((a, b) -> a.getDate().compareTo(b.getDate())).collect(Collectors.toList());
    }

    private void transaction(Account account, BigDecimal amount, Operation.OperationType operationType) {
        BigDecimal balance = (operationType == Operation.OperationType.DEPOSIT) ? getBalanceByAccountId(account.getId()).add(amount) : getBalanceByAccountId(account.getId()).subtract(amount);
        Operation operation = Operation.builder().amount(amount)
                .accountId(account.getId())
                .balance(balance)
                .date(LocalDateTime.now())
                .type(operationType)
                .build();

        operationList.add(operation);
    }


    public BigDecimal getBalanceByAccountId(String accountId) {

        List<Operation> operationForAccount = operationList.stream().filter(operation -> operation.getAccountId() == accountId).collect(Collectors.toList());
        return operationForAccount.isEmpty() ? new BigDecimal(0) : operationForAccount.get(operationForAccount.size() - 1).getBalance();
    }


}

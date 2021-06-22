package com.kata.banking.service.impl;

import com.kata.banking.model.Account;
import com.kata.banking.model.Operation;
import com.kata.banking.service.api.OperationService;
import com.kata.banking.service.api.StatementService;

import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class StatementServiceImpl implements StatementService {

    public static final String STATEMENT_HEADER = String.format("%10s | %20s | %10s | %10s\n", "operation", "date", "amount", "balance");

    private static StatementService instance;

    OperationService operationService = OperationServiceImpl.getInstance();

    public static StatementService getInstance() {
        if (isNull(instance))
            instance = new StatementServiceImpl();
        return instance;
    }

    private StatementServiceImpl() {
    }


    @Override
    public void printStatement(Account account, PrintStream stream) {
        List<Operation> operationList = operationService.getAccountOperationHistory(account.getId());

        StringBuilder builder = new StringBuilder(STATEMENT_HEADER);
        builder.append(operationList.stream().map(operation ->
                buildLine(operation)).collect(Collectors.joining()));

        stream.println(builder.toString());
        System.out.println(builder.toString());
    }

    private String buildLine(Operation operation) {
        return String.format("%10s | %20s | %10.2f | %10.2f\n",
                operation.getType().getTypeName(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(operation.getDate()),
                operation.getAmount(),
                operation.getBalance());
    }
}
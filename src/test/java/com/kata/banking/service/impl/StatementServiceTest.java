package com.kata.banking.service.impl;

import com.kata.banking.model.Account;
import com.kata.banking.model.Operation;
import com.kata.banking.service.api.OperationService;
import com.kata.banking.service.api.StatementService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class StatementServiceTest {

    StatementService statementService;
    OperationService operationService;

    @Before
    public void createServices() {
        this.statementService = StatementServiceImpl.getInstance();
        this.operationService = OperationServiceImpl.getInstance();
    }

    @SneakyThrows
    @Test
    public void should_print_account_operation_history() {
        //GIVEN
        Account account = setupAccount();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);

        //WHEN
        operationService.deposit(account, BigDecimal.TEN);
        operationService.withdraw(account, BigDecimal.TEN);
        List<Operation> operationList = operationService.getAccountOperationHistory(account.getId());
        this.statementService.printStatement(account, ps);
        String result = baos.toString("UTF-8");
        String expected = " operation |                 date |     amount |    balance\n" +
                "   Deposit |  " + format(operationList.get(0).getDate()) + " |      10,00 |      10,00\n" +
                "  Withdraw |  " + format(operationList.get(1).getDate()) + " |      10,00 |       0,00\n" +
                "\n";
        ps.close();

        //THEN
        assertEquals(expected.trim(), result.trim());

    }

    private String format(TemporalAccessor temporal) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(temporal);
    }


    private Account setupAccount() {
        return Account.builder().id(UUID.randomUUID().toString()).build();

    }
}

package com.kata.banking.service.impl;

import com.kata.banking.model.Account;
import com.kata.banking.model.Operation;
import com.kata.banking.service.api.OperationService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class OperationServiceTest {

    private static final int[] AMOUNT_VALUES = {500, 1000, 100, 300, 700};
    private static final int[] BALANCE_VALUES = {500, 1500, 1600, 1300, 2000};

    OperationService operationService;

    @Before
    public void createServices() {
        this.operationService = OperationServiceImpl.getInstance();
    }


    @Test
    public void should_calculate_balance_after_one_withdrawal() {
        //GIVEN
        Account account = setupAccount();
        BigDecimal withdrawAmount = new BigDecimal(200);

        //WHEN
        operationService.withdraw(account, withdrawAmount);

        //THEN
        assertEquals(operationService.getBalanceByAccountId(account.getId()), new BigDecimal(-200));
    }


    @Test
    public void should_calculate_balance_after_one_deposit() {
        //GIVEN
        Account account = setupAccount();
        BigDecimal depositAmount = new BigDecimal(200);

        //WHEN
        operationService.deposit(account, depositAmount);

        //THEN
        assertEquals(operationService.getBalanceByAccountId(account.getId()), new BigDecimal(200));
    }

    @Test
    public void should_return_account_operation_history() {
        //GIVEN
        Account account = setupAccount();

        //WHEN
        operationService.deposit(account, new BigDecimal(500));
        operationService.deposit(account, new BigDecimal(1000));
        operationService.deposit(account, new BigDecimal(100));
        operationService.withdraw(account, new BigDecimal(300));
        operationService.deposit(account, new BigDecimal(700));
        List<Operation> operationList = operationService.getAccountOperationHistory(account.getId());

        //THEN
        assertEquals(operationList.size(), 5);

        for (int i = 0; i < 5; i++) {
            assertEquals(operationList.get(i).getAmount(), new BigDecimal(AMOUNT_VALUES[i]));
            assertEquals(operationList.get(i).getBalance(), new BigDecimal(BALANCE_VALUES[i]));
        }
    }

    private Account setupAccount() {
        return Account.builder().id(UUID.randomUUID().toString()).build();
    }
}

package com.kata.banking.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class Operation {
    public enum OperationType {
        WITHDRAW("Withdraw"),
        DEPOSIT("Deposit");
        private String typeName;

        public String getTypeName() {
            return typeName;
        }

        OperationType(String type) {
            this.typeName = type;
        }
    }

    private String id;
    private LocalDateTime date;
    private OperationType type;
    private BigDecimal amount;
    private BigDecimal balance;
    private String accountId;


    @Override
    public String toString() {

        return type +
                " | " + date +
                " | " + amount +
                " | " + balance +
                " | '" + accountId + '\'' + '\n';
    }
}

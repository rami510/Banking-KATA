package com.kata.banking.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {
    private String id;
    private String name;
    private String clientId;
}

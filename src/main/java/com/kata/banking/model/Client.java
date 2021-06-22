package com.kata.banking.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Client {
    private String id;
    private String firstname;
    private String lastname;
}

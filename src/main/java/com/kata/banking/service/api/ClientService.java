package com.kata.banking.service.api;

import com.kata.banking.model.Client;

import java.util.Optional;

public interface ClientService {

    void createClient(Client client);

    Optional<Client> findClient(String clientId);
}

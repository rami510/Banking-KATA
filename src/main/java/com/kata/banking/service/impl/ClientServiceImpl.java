package com.kata.banking.service.impl;

import com.kata.banking.model.Client;
import com.kata.banking.service.api.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

public class ClientServiceImpl implements ClientService {

    // we should delegate data input output to a repository layer (no database is needed on our case we chose to make it simple)
    private List<Client> clientList;
    private static ClientService instance;

    private ClientServiceImpl() {
        this.clientList = new ArrayList<>();
    }

    public static ClientService getInstance() {
        if (isNull(instance))
            instance = new ClientServiceImpl();
        return instance;
    }

    @Override
    public void createClient(Client client) {
        Optional<Client> existingClient = this.findClient(client.getId());
        if (existingClient.isPresent())
            throw new RuntimeException("Client exist");

        this.clientList.add(client);
    }

    @Override
    public Optional<Client> findClient(String clientId) {
        return this.clientList.stream().filter((a) -> a.getId().equals(clientId)).findFirst();
    }
}

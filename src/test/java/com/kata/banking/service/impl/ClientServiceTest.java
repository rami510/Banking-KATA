package com.kata.banking.service.impl;

import com.kata.banking.model.Client;
import com.kata.banking.service.api.ClientService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class ClientServiceTest {

    ClientService clientService;

    @Before

    public void createServices() {
        this.clientService = ClientServiceImpl.getInstance();
    }


    @Test
    public void should_create_client() {
        //GIVEN
        String clientId = UUID.randomUUID().toString();
        Client actual = buildClient(clientId);

        //WHEN
        clientService.createClient(actual);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_client_exist() {
        //GIVEN
        String clientId = UUID.randomUUID().toString();
        Client actual = buildClient(clientId);

        //WHEN
        clientService.createClient(actual);
        clientService.createClient(actual);

    }

    @Test
    public void should_return_client() {
        //GIVEN
        String clientId = UUID.randomUUID().toString();
        Client client = buildClient(clientId);
        clientService.createClient(client);

        //WHEN
        Optional<Client> actual = clientService.findClient(clientId);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(actual.get().getId(), client.getId());
    }

    @Test
    public void should_return_empty_when_client_not_exist() {
        //WHEN
        Optional<Client> actual = clientService.findClient("client 1");

        //THEN
        assertFalse(actual.isPresent());
    }

    private Client buildClient(String clientId) {
        Client actual = Client.builder().id(clientId).build();
        return actual;
    }
}

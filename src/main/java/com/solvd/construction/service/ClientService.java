package com.solvd.construction.service;

import com.solvd.construction.model.Client;

import java.util.Optional;

public interface ClientService {
    Client create(Client client);

    Optional<Client> retrieveById(Long id);
}

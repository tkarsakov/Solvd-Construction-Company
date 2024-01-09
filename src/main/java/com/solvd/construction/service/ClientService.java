package com.solvd.construction.service;

import com.solvd.construction.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService extends IService<Client> {
    Client create(Client client);

    Optional<Client> retrieveById(Long id);

    List<Client> retrieveAll();

    void update(Client client);

    void delete(Long id);
}

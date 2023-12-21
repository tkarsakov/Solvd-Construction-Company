package com.solvd.construction.persistence;

import com.solvd.construction.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    void create(Client client);

    Optional<Client> findById(Long id);

    List<Client> findAll();

    void update(Client client);

    void deleteById(Long id);
}

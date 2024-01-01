package com.solvd.construction.service.impl;

import com.solvd.construction.model.Client;
import com.solvd.construction.persistence.ClientRepository;
import com.solvd.construction.persistence.impl.ClientRepositoryImplDAO;
import com.solvd.construction.service.ClientService;
import com.solvd.construction.service.CountryService;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final CountryService countryService;

    public ClientServiceImpl() {
        this.clientRepository = new ClientRepositoryImplDAO();
        this.countryService = new CountryServiceImpl();
    }

    @Override
    public Client create(Client client) {
        client.setId(null);
        if (countryService.retrieveByCountryName(client.getCountry().getCountryName()).isEmpty()) {
            client.setCountryId(countryService.create(client.getCountry()).getId());
        }
        clientRepository.create(client);
        return client;
    }

    @Override
    public Optional<Client> retrieveById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        optionalClient.ifPresent(setFields());
        return optionalClient;
    }

    @Override
    public List<Client> retrieveAll() {
        List<Client> clients = clientRepository.findAll();
        clients.forEach(setFields());
        return clients;
    }

    @Override
    public void update(Client client) {
        clientRepository.update(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    private Consumer<Client> setFields() {
        return client -> client.setCountry(countryService.retrieveById(client.getCountryId()).orElse(null));
    }
}

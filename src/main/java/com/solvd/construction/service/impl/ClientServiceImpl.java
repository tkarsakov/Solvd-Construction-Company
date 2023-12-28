package com.solvd.construction.service.impl;

import com.solvd.construction.model.Client;
import com.solvd.construction.persistence.ClientRepository;
import com.solvd.construction.persistence.impl.ClientRepositoryImplDAO;
import com.solvd.construction.service.ClientService;
import com.solvd.construction.service.CountryService;

import java.util.Optional;

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
        return clientRepository.findById(id);
    }
}

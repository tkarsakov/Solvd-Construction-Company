package com.solvd.construction.service.impl;

import com.solvd.construction.model.Client;
import com.solvd.construction.service.ClientService;
import com.solvd.construction.persistence.mappers.ClientMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class MBClientServiceImpl implements ClientService {
    private final ClientMapper clientMapper;
    private final MBCountryServiceImpl countryService;

    public MBClientServiceImpl(SqlSession session) {
        this.clientMapper = session.getMapper(ClientMapper.class);
        this.countryService = new MBCountryServiceImpl(session);
    }


    @Override
    public Client create(Client client) {
        client.setId(null);
        if (client.getCountryId() == null || client.getCountry().getId() == null) {
            countryService.create(client.getCountry());
        }
        return clientMapper.create(client);
    }

    @Override
    public Optional<Client> retrieveById(Long id) {
        Optional<Client> optionalClient = Optional.of(clientMapper.retrieveById(id));
        optionalClient.ifPresent(client -> client
                .setCountry(countryService.retrieveById(client.getCountryId()).orElse(null)));
        return Optional.of(clientMapper.retrieveById(id));
    }
}

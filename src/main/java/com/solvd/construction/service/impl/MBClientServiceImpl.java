package com.solvd.construction.service.impl;

import com.solvd.construction.model.Client;
import com.solvd.construction.persistence.mappers.ClientMapper;
import com.solvd.construction.service.ClientService;
import com.solvd.construction.service.CountryService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;

public class MBClientServiceImpl implements ClientService {
    private final CountryService countryService;
    private final SqlSessionFactory sessionFactory;

    public MBClientServiceImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.countryService = new MBCountryServiceImpl(sessionFactory);
    }


    @Override
    public Client create(Client client) {
        try (SqlSession session = sessionFactory.openSession()) {
            ClientMapper clientMapper = session.getMapper(ClientMapper.class);
            client.setId(null);
            client.setCountry(countryService.retrieveById(client.getCountryId()).orElse(null));
            clientMapper.create(client);
            session.commit();
            return client;
        }
    }

    @Override
    public Optional<Client> retrieveById(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            ClientMapper clientMapper = session.getMapper(ClientMapper.class);
            Optional<Client> optionalClient = Optional.of(clientMapper.retrieveById(id));
            optionalClient.ifPresent(client -> client
                    .setCountry(countryService.retrieveById(client.getCountryId()).orElse(null)));
            return Optional.of(clientMapper.retrieveById(id));
        }
    }

    @Override
    public List<Client> retrieveAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            ClientMapper clientMapper = session.getMapper(ClientMapper.class);
            List<Client> clients = clientMapper.retrieveAll();
            clients.forEach(client -> client
                    .setCountry(countryService.retrieveById(client.getCountryId()).orElse(null)));
            return clients;
        }
    }

    @Override
    public void update(Client client) {
        try (SqlSession session = sessionFactory.openSession()) {
            ClientMapper clientMapper = session.getMapper(ClientMapper.class);
            clientMapper.update(client);
            session.commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            ClientMapper clientMapper = session.getMapper(ClientMapper.class);
            clientMapper.delete(id);
            session.commit();
        }
    }
}

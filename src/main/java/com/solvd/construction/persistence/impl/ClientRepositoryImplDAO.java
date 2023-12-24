package com.solvd.construction.persistence.impl;

import com.solvd.construction.model.Client;
import com.solvd.construction.persistence.ClientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImplDAO extends ModelRepositoryImpl<Client> implements ClientRepository {
    private final String TABLE_NAME = "clients";
    private final String[] TABLE_COLUMNS = {"client_name", "client_email", "country_id"};
    private final int[] FIELD_TYPES = {Types.VARCHAR, Types.VARCHAR, Types.BIGINT};

    @Override
    public void create(Client client) {
        super.create(client, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return super.findById(id, TABLE_NAME);
    }

    @Override
    public List<Client> findAll() {
        return super.findAll(TABLE_NAME);
    }

    @Override
    public void update(Client client) {
        super.update(client, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id, TABLE_NAME);
    }

    @Override
    public Object[] getModelParams(Client client) {
        return new Object[]{client.getClientName(), client.getClientEmail(), client.getCountryId()};
    }

    @Override
    public Optional<Client> getOptionalOfModel(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new Client(
                        resultSet.getLong(1),
                        resultSet.getString(TABLE_COLUMNS[0]),
                        resultSet.getString(TABLE_COLUMNS[1]),
                        resultSet.getLong(TABLE_COLUMNS[2])
                )
        );
    }

    @Override
    public List<Client> getListOfModel(ResultSet resultSet) throws SQLException {
        List<Client> clients = new ArrayList<>();
        while (resultSet.next()) {
            clients.add(
                    new Client(
                            resultSet.getLong(1),
                            resultSet.getString(TABLE_COLUMNS[0]),
                            resultSet.getString(TABLE_COLUMNS[1]),
                            resultSet.getLong(TABLE_COLUMNS[2])
                    )
            );
        }
        return clients;
    }
}

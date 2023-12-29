package com.solvd.construction;


import com.solvd.construction.model.Client;
import com.solvd.construction.persistence.mappers.ClientMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClientMapper clientMapper = session.getMapper(ClientMapper.class);
            Client client = clientMapper.retrieveById(1L);
            System.out.println(client.getClientName());
        }
    }
}

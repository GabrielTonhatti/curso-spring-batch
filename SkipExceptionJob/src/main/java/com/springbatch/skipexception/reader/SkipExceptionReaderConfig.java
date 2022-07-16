package com.springbatch.skipexception.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.skipexception.dominio.Cliente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class SkipExceptionReaderConfig {
    @Bean
    public ItemReader<Cliente> skipExceptionReader(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("skipExceptionReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM cliente")
                .rowMapper(rowMapper())
//				.beanRowMapper(Cliente.class)
                .build();
    }

    private RowMapper<Cliente> rowMapper() {
        return (resultSet, rowNum) -> {
            if (resultSet.getRow() == 11)
                throw new SQLException(String.format("Encerrando a execução - Cliente inválido %s", resultSet.getString("email")));
            else return clienteRowMapper(resultSet);

        };
    }

    private Cliente clienteRowMapper(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome(resultSet.getString("nome"));
        cliente.setSobrenome(resultSet.getString("sobrenome"));
        cliente.setIdade(resultSet.getString("idade"));
        cliente.setEmail(resultSet.getString("email"));

        return cliente;
    }

}

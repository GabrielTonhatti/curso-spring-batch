package com.springbatch.jdbccursorreader.writer;

import com.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcCursorReaderWriterConfig {

    @Bean
    public ItemWriter<Cliente> jdbcCursorReaderWriter() {
        return clientes -> clientes.forEach(System.out::println);
    }

}

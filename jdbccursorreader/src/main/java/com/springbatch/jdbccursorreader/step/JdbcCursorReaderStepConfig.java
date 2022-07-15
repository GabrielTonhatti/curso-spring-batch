package com.springbatch.jdbccursorreader.step;

import com.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcCursorReaderStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Step jdbcCursorReaderStep(ItemReader<Cliente> jdbcCursorReader, ItemWriter<Cliente> jdbcCursorWriter) {
        return stepBuilderFactory
                .get("jdbcCursorReaderStep")
                .<Cliente, Cliente>chunk(1)
                .reader(jdbcCursorReader)
                .writer(jdbcCursorWriter)
                .build();
    }

}

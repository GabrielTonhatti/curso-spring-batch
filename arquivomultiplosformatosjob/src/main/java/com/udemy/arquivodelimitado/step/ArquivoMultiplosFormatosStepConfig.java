package com.udemy.arquivodelimitado.step;

import com.udemy.arquivodelimitado.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoMultiplosFormatosStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Step arquivoMultiplosFormatosStep(
            MultiResourceItemReader<Cliente> multiplosArquivosClienteTransacaoReader,
            ItemWriter leituraArquivoMultiplosFormatosItemWriter
    ) {
        return stepBuilderFactory
                .get("arquivoMultiplosFormatosStep")
                .chunk(1)
                .reader(multiplosArquivosClienteTransacaoReader)
                .writer(leituraArquivoMultiplosFormatosItemWriter)
                .build();
    }

    }

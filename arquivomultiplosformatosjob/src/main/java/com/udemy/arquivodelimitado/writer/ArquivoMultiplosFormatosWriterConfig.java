package com.udemy.arquivodelimitado.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoMultiplosFormatosWriterConfig {

    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public ItemWriter arquivoMultiplosFormatosWriter() {
        return items -> items.forEach(System.out::println);
    }

}

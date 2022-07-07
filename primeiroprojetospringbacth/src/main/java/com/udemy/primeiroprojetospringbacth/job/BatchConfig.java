package com.udemy.primeiroprojetospringbacth.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job imprimeOlaJob(Step imprimeOlaStep) {
        return jobBuilderFactory
                .get("imprimeOlaJob")
                .start(imprimeOlaStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Job imprimeParImparJob() {
        return jobBuilderFactory
                .get("imprimeParImparJob")
                .start(imprimeParImparStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    public Step imprimeParImparStep() {
        return stepBuilderFactory
                .get("imprimeParImparStep")
                .<Integer, String>chunk(10)
                .reader(contaAteDezReader())
                .processor(parOuImparProcessor())
                .writer(imprimeWriter())
                .build();
    }

    private IteratorItemReader<Integer> contaAteDezReader() {
        List<Integer> numerosDeUmAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        return new IteratorItemReader<>(numerosDeUmAteDez.iterator());
    }

    private FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<>(
                (item -> item % 2 == 0 ? String.format("Item %s é Par", item)
                        : String.format("Item %s é Impar", item))
        );
    }

    private ItemWriter<? super String> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }

}

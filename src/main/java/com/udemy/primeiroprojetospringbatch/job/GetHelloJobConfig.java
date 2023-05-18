package com.udemy.primeiroprojetospringbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class GetHelloJobConfig {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job getHelloJob(Step getHelloStep){

        return jobBuilderFactory
                .get("IMPRIMINDO O METODO 'OLÁ JOB'")
                .start(getHelloStep)
                //.incrementer(new RunIdIncrementer())
                //TOMAR CUIDADO AO USAR O INCREMENTER. SE O JOB PARAR, ELE N RESTARTAR DE ONDE PAROU
                //ELE VAI CRIAR OUTRA INSTANCIA DO ZERO
                .build();
    }
}

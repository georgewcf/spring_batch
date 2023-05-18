package com.udemy.primeiroprojetospringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private StepBuilderFactory steBuilderFactory;

    @Bean
    public Job getHelloJob(){

        return jobBuilderFactory
                .get("IMPRIMINDO O METODO 'OLÁ JOB'")
                .start(getHelloStep())
                .incrementer(new RunIdIncrementer())
                //TOMAR CUIDADO AO USAR O INCREMENTER. SE O JOB PARAR, ELE N RESTARTAR DE ONDE PAROU
                //ELE VAI CRIAR OUTRA INSTANCIA DO ZERO
                .build();
    }

    private Step getHelloStep() {
        return steBuilderFactory
                .get("IMPRIMINDO O METODO 'OLÁ STEP'")
                .tasklet(getTasklet(null))
                .build();
    }

    @Bean
    @StepScope
    public Tasklet getTasklet(@Value("#{jobParameters['nome']}") String nome) {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Olá Mundo!");
                return RepeatStatus.FINISHED; //tarefa terminada
            }
        };
    }
}

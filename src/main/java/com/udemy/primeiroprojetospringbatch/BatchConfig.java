package com.udemy.primeiroprojetospringbatch;

import lombok.Data;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory steBuilderFactory;
    @Bean
    public Job imprimeOlaJob(){

        return jobBuilderFactory
                .get("IMPRIMINDO O METODO 'OLÁ JOB'")
                .start(imprimeOlaStep())
                .build();
    }

    private Step imprimeOlaStep() {
        return steBuilderFactory
                .get("IMPRIMINDO O METODO 'OLÁ STEP'")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Olá Mundo!");
                        return RepeatStatus.FINISHED; //tarefa terminada
                    }
                })
                .build();
    }
}

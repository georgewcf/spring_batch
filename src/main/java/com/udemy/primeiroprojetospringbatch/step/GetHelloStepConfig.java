package com.udemy.primeiroprojetospringbatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetHelloStepConfig {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private StepBuilderFactory steBuilderFactory;
    @Bean
    public Step getHelloStep(Tasklet getHelloTasklet) {
        return steBuilderFactory
                .get("IMPRIMINDO O METODO 'OLÁ STEP'")
                .tasklet(getHelloTasklet)
                .build();
    }
}

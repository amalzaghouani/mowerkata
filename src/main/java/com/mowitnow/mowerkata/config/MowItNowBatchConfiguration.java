package com.mowitnow.mowerkata.config;

import com.mowitnow.mowerkata.model.Mower;
import com.mowitnow.mowerkata.model.MowerData;
import com.mowitnow.mowerkata.batchsteps.MowerFileProcessor;
import com.mowitnow.mowerkata.batchsteps.MowerFileReader;
import com.mowitnow.mowerkata.batchsteps.MowerFileWriter;
import com.mowitnow.mowerkata.service.MowerInstructionServiceImpl;
import com.mowitnow.mowerkata.utils.MowerDataValidationImpl;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class MowItNowBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public JobParametersIncrementer jobParametersIncrementer() {
        return new RunIdIncrementer();
    }


    @Bean
    @StepScope
    public ItemReader<MowerData> itemReader(@Value("#{jobParameters['inputFile']}") String inputFile) {
        return new MowerFileReader(new MowerDataValidationImpl(), inputFile);
    }

    @Bean
    public ItemProcessor<MowerData, Mower> itemProcessor() {
        return new MowerFileProcessor(new MowerInstructionServiceImpl());
    }

    @Bean
    @StepScope
    public ItemWriter<Mower> itemWriter(@Value("#{jobParameters['outputFile']}") String outputFile) {
        return new MowerFileWriter(outputFile);
    }

    @Bean
    public Job mowitnowJob() throws Exception {
        return jobBuilderFactory.get("mowitnowJob")
                .start(step(itemReader(null), itemProcessor(), itemWriter(null)))
                .build();
    }

    @Bean
    public Step step(ItemReader<MowerData> reader, ItemProcessor<MowerData, Mower> processor, ItemWriter<Mower> writer) {
        return stepBuilderFactory
                .get("step").<MowerData, Mower>chunk(1)
                .reader(reader).processor(processor)
                .writer(writer).build();
    }


}

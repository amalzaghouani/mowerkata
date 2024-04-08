package com.mowitnow.mowerkata.config;

import com.mowitnow.mowerkata.model.Mower;
import com.mowitnow.mowerkata.model.MowerData;
import com.mowitnow.mowerkata.batchsteps.MowerFileProcessor;
import com.mowitnow.mowerkata.batchsteps.MowerFileReader;
import com.mowitnow.mowerkata.batchsteps.MowerFileWriter;
import com.mowitnow.mowerkata.service.MowerInstructionService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ItemReader<MowerData> itemReader() {
        return new MowerFileReader("src\\main\\resources\\testFile.txt");
    }

    @Bean
    public ItemProcessor<MowerData, Mower> itemProcessor() {
        return new MowerFileProcessor();
    }

    @Bean
    public ItemWriter<Mower> itemWriter() {
        return new MowerFileWriter("src\\main\\resources\\outputFile.txt");
    }

    @Bean
    public Job mowitnowJob() {
        return jobBuilderFactory.get("mowitnowJob").start(step(itemReader(), itemProcessor(), itemWriter())).build();
    }

    @Bean
    public Step step(ItemReader<MowerData> reader, ItemProcessor<MowerData, Mower> processor, ItemWriter<Mower> writer) {
        return stepBuilderFactory.get("step").<MowerData, Mower>chunk(1).reader(reader).processor(processor).writer(writer).build();
    }
}

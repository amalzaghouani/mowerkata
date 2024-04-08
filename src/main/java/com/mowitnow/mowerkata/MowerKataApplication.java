package com.mowitnow.mowerkata;

import com.mowitnow.mowerkata.config.MowItNowBatchConfiguration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class MowerKataApplication {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MowItNowBatchConfiguration.class);
        context.refresh();

        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        Job job = context.getBean(Job.class);
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("inputPath", "src\\main\\resources\\testFile.txt")
                .addString("outputPath", "src\\main\\resources\\outputFile.txt")
                .toJobParameters();
        System.out.println("job parameters"+jobParameters);
        try {
            JobExecution execution = jobLauncher.run(job,jobParameters);
            System.out.println("Job Exit status " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }

}

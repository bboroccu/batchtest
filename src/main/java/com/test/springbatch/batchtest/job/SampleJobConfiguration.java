package com.test.springbatch.batchtest.job;

import com.test.springbatch.batchtest.linstener.SampleListener;
import com.test.springbatch.batchtest.model.SampleModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SampleJobConfiguration {
    public static final String JOB_NAME = "sampleJop";
    public static final String STEP_NAME = "sampleStep1";
    private static final int CHUNK_SIZE = 3;

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private JpaPagingItemReader<SampleModel> itemReader;

    @Autowired
    public SampleJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, JpaPagingItemReader<SampleModel> itemReader) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.itemReader = itemReader;
    }

    @Bean(JOB_NAME)
    public Job sampleJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(sampleStep1())
                .build();
    }

    @Bean(STEP_NAME)
    public Step sampleStep1() {
        return stepBuilderFactory.get(STEP_NAME)
                .<SampleModel, SampleModel>chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .skip(SampleException.class)
                .skipLimit(CHUNK_SIZE)
                .processorNonTransactional()
                .listener(new SampleListener())
                .build();
    }

    @Bean
    public ItemProcessor<SampleModel, SampleModel> processor() {
        return item -> {
            log.info("processing id : {}", item.getId());
            if (item.getId() == 3 || item.getId() == 7) {
                throw new SampleException();
            } else {
                return item;
            }
        };
    }

    @Bean
    public ItemWriter<SampleModel> writer() {
        return items -> {
            items.forEach(item->{
                log.info("complete id : {}", item.getId());
            });
        };
    }

}

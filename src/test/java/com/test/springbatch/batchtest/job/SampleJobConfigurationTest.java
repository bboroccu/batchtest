package com.test.springbatch.batchtest.job;

import com.test.springbatch.batchtest.BatchTestApplication;
import com.test.springbatch.batchtest.model.SampleModel;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BatchTestApplication.class, TestUtilContext.class})
class SampleJobConfigurationTest {
    @Autowired
    private Job sampleJob;

    @MockBean(name = "itemReader")
    JpaPagingItemReader<SampleModel> mockItemReader;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Before
    public void setUp() {
        jobLauncherTestUtils.setJob(sampleJob);
    }
    @Test
    void sampleJob() throws Exception {
        given(mockItemReader.read()).willReturn(
                new SampleModel(1, "1"),
                new SampleModel(2, "2"),
                new SampleModel(3, "3"),
                new SampleModel(4, "4"),
                new SampleModel(5, "5"),
                new SampleModel(6, "6"),
                new SampleModel(7, "7"),
                new SampleModel(8, "8"),
                new SampleModel(9, "9"),
                new SampleModel(10, "10"),
                null
        );

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("sampleStep1");
    }
}
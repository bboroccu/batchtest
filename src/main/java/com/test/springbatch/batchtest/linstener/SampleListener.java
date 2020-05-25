package com.test.springbatch.batchtest.linstener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

@Slf4j
public class SampleListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("[STEP] beforeStep({})", stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        Integer totalCount = stepExecution.getReadCount();
        Integer successCount = stepExecution.getWriteCount();
        Integer failCount = stepExecution.getProcessSkipCount();
        log.info("total : {}, success : {}, fail : {}", totalCount, successCount, failCount);
        return null;
    }
}

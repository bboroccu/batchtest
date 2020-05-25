package com.test.springbatch.batchtest.repository;

import com.test.springbatch.batchtest.model.SampleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleModelRepo extends JpaRepository<SampleModel, Integer> {
}

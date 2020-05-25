package com.test.springbatch.batchtest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@Getter
@Entity
@NoArgsConstructor
public class SampleModel {
    public SampleModel(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String title;
}

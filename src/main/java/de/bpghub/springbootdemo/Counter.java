package de.bpghub.springbootdemo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Counter {

    @Id
    private String name;

    private int count;

}

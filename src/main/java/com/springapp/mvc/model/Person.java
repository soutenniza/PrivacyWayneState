package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by Van on 3/17/2015.
 */
@NodeEntity
public class Person {
    @GraphId Long nodeID;
    public String name;

    public Person() {}
    public Person(String N) { this.name = name;}

    public String getName(){
        return this.name;
    }
}

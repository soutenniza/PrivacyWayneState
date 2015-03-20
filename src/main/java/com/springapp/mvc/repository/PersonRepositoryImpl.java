package com.springapp.mvc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;

/**
 * Created by Van on 3/20/2015.
 */
public class PersonRepositoryImpl {
    @Autowired
    private Neo4jOperations neo4jOperations;
}

package com.springapp.mvc.service;

import com.springapp.mvc.model.Person;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by Van on 3/17/2015.
 */
public interface PersonRepository extends GraphRepository<Person> {
}

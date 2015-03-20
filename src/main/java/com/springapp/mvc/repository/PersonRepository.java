package com.springapp.mvc.repository;

import com.springapp.mvc.model.Person;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Van on 3/17/2015.
 */
@Repository
public interface PersonRepository extends GraphRepository<Person> {
}

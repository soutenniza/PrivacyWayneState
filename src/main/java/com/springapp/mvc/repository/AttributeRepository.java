package com.springapp.mvc.repository;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Group;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Zack on 3/28/15.
 */
@Repository
public interface AttributeRepository extends GraphRepository<Attribute> {
}


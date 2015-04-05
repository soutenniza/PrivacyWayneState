package com.springapp.mvc.repository;

import com.springapp.mvc.model.HasRelationship;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Zachary on 4/4/2015.
 */
@Repository
public interface HasRepository extends GraphRepository<HasRelationship> {
}
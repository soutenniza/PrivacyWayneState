package com.springapp.mvc.repository;

import com.springapp.mvc.model.FriendRelationship;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Van on 4/18/2015.
 */
@Repository
public interface FriendRepository extends GraphRepository<FriendRelationship> {
}

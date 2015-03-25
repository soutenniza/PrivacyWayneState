package com.springapp.mvc.repository;

import com.springapp.mvc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Van on 3/20/2015.
 */
public class PersonRepositoryImpl {
    @Autowired
    private Neo4jOperations neo4jOperations;


    //@Override
    @Transactional
    public void addFriend(Person friend, Person user){
        user.friends(friend);
        neo4jOperations.save(user);

    }

}

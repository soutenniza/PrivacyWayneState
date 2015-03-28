package com.springapp.mvc.service;

import com.springapp.mvc.model.Person;
import com.springapp.mvc.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by Van on 3/20/2015.
 */
@Service
@Transactional
public class PersonService {
    @Autowired
    private Neo4jTemplate template;
    @Autowired
    private PersonRepository personRepository;


    //@Override
    public void addFriend(Person friend, Person user){
        user.friends(friend);
        template.save(user);

    }


    public ArrayList<Person> getAllPersons(){
        ArrayList<Person> people = new ArrayList<Person>();
        people.addAll(personRepository.findAll().as(ArrayList.class));
        return people;
    }

}

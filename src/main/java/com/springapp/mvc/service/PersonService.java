package com.springapp.mvc.service;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Comment;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.repository.AttributeRepository;
import com.springapp.mvc.repository.GroupRepository;
import com.springapp.mvc.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private AttributeRepository attributeRepository;


    public void addFriend(Person friend, Person user){
        user.friends(friend);
        template.save(user);
    }

    public void addMember(Group group, Person user){
        group.member(user);
        template.save(group);
    }

    public void addAttribute(Attribute attribute, Person user){
        attribute.has(user);
        template.save(attribute);
    }

    public void addOwner(Comment comment, Person person){
        comment.owns(person);
        template.save(comment);
    }

    public ArrayList<Person> getAllPersons(){
        ArrayList<Person> people = new ArrayList<Person>();
        people.addAll(personRepository.findAll().as(ArrayList.class));
        return people;
    }

    public boolean groupExists(String name) {
        ArrayList<Group> groups = getAllGroups();
        boolean found = false;
        for (Group g : groups) {
            if (g.getName().equals(name)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean areFriends(Person p1, Person p2){
        ArrayList<Person> persons = p1.getFriends();
        boolean found = false;
        if(persons.isEmpty())
            return false;
        for (Person p : persons) {
            if (p.getName().equals(p2.getName())) {
                found = true;
                break;
            }
        }
        return found;
    }

    public Person getPerson(Long id){
        return  personRepository.findOne(id);
    }

    public Group getGroup(Long id) { return groupRepository.findOne(id);}

    public ArrayList<Group> getAllGroups(){
        ArrayList<Group> groups = new ArrayList<Group>();
        groups.addAll(groupRepository.findAll().as(ArrayList.class));
        return groups;
    }

    public ArrayList<Attribute> getAllAttributes(){
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        attributes.addAll(attributeRepository.findAll().as(ArrayList.class));
        return attributes;
    }


}

package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.Collection;

/**
 * Created by Van on 3/17/2015.
 */
@NodeEntity
public class Person {
    @GraphId Long nodeID;
    public String name;
    public int age;

    @RelatedToVia
    Collection<FriendRelationship> friendRelationships;


    public Person() {}

    public Person(String N) {
        this.name = name;
        this.age = age;
    }

    public String getName(){
        //System.out.println("CLASS_PERSON: Returning the name "+ this.name);
        return this.name;
    }
    public void setName(String sname){
        this.name = sname;
        //System.out.println("CLASS_PERSON: Name set to "+ sname);
    }

    public Long getNodeID(){
        return nodeID;
    }

    // Takes a string (given from the web page) and stores an integer
    public void setAge(String sage){
        int intAge = Integer.parseInt(sage);
        this.age = intAge;
        //System.out.println("CLASS_PERSON: Age set to "+ sage);
    }

    public int getAge(){
        return this.age;
    }

    public FriendRelationship friends(Person friend){
        final FriendRelationship friendRelationship = new FriendRelationship(this, friend);
        friendRelationships.add(friendRelationship);
        return friendRelationship;
    }

}

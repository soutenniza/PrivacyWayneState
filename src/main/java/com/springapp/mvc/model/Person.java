package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Van on 3/17/2015.
 */
@NodeEntity
public class Person {

    @GraphId
    Long nodeID;
    public String name;

    @RelatedToVia
    Collection<FriendRelationship> friendRelationships;

    @RelatedToVia
    Collection<HasRelationship> hasRelationships;

    Collection<Person> friends;

    Collection<Attribute> has;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String sname){
        this.name = sname;
    }

    public Long getNodeID(){
        return nodeID;
    }

    public FriendRelationship friends(Person friend){
        final FriendRelationship friendRelationship = new FriendRelationship(this, friend);
        friends.add(friend);
        friendRelationships.add(friendRelationship);
        return friendRelationship;
    }

    public HasRelationship has(Attribute attribute){
        final HasRelationship hasRelationship = new HasRelationship(this, attribute);
        has.add(attribute);
        hasRelationships.add(hasRelationship);
        return hasRelationship;
    }


    public Collection<Person> getFriends(){
        return friends;
    }

    public Collection<FriendRelationship> getFriendRelationships(){
        return friendRelationships;
    }

}

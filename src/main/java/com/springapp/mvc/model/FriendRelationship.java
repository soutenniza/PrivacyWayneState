package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by vannguyen on 3/25/15.
 */
@RelationshipEntity(type = "FRIENDS")
public class FriendRelationship {
    @GraphId Long id;
    @StartNode Person user;
    @EndNode Person friend;

    public FriendRelationship(){

    }

    public FriendRelationship(Person user, Person friend){
        this.user = user;
        this.friend = friend;
    }
}

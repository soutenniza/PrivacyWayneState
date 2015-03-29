package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by Zachary on 3/28/2015.
 */
@RelationshipEntity(type = "OWNS")
public class OwnsRelationship {
    @GraphId
    Long id;

    @StartNode
    Person user;

    @EndNode
    Comment comment;

    public OwnsRelationship(){
        //null
    }

    public OwnsRelationship(Comment comment, Person user){
        this.user = user;
        this.comment = comment;
    }
}

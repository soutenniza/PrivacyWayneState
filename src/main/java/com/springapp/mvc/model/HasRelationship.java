package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by Zack on 3/28/15.
 */
@RelationshipEntity(type = "HAS")
public class HasRelationship {

    @GraphId
    Long id;

    @StartNode
    Person user;

    @EndNode
    Attribute attribute;

    public HasRelationship(){
        //null
    }

    public HasRelationship(Attribute attribute, Person user){
        this.user = user;
        this.attribute = attribute;
    }
}

package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by Zack on 3/28/15.
 */
@RelationshipEntity(type = "MEMBER")
public class MemberRelationship {

    @GraphId
    Long id;

    @StartNode
    Person user;

    @EndNode
    Group group;

    public MemberRelationship(){
        //null
    }

    public MemberRelationship(Group group, Person user){
        this.user = user;
        this.group = group;
    }
}

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

    private int pv; // privacy value
    private int vv; // visibility value
    private int sv; // sensitivity value

    @StartNode
    Person user;

    @EndNode
    Attribute attribute;

    public HasRelationship(){
        //null
    }

    public HasRelationship(Person user, Attribute attribute, int pv, int vv, int sv){
        this.user = user;
        this.attribute = attribute;
        this.pv = pv;
        this.vv = vv;
        this.sv = sv;
    }
}

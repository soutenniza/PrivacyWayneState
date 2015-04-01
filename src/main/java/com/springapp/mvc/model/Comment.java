package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.Collection;

/**
 * Created by Zachary on 3/28/2015.
 */
@NodeEntity
public class Comment {
    @GraphId
    private Long nodeID;
    private String text;
    private Long ownerID;

    @RelatedToVia
    Collection<OwnsRelationship> ownsRelationships;

    public Comment() {}

    public Comment(String text, Long ownerID) {
        this.text = text;
        this.ownerID = ownerID;
    }

    public void setText(String val){
        this.text = val;
    }

    public void setOwnerId(Long val) { this.ownerID = val; }

    public String getText(){
        return this.text;
    }

    public Long getOwnerID() { return this.ownerID; }

    public Long getNodeID(){
        return nodeID;
    }

    public OwnsRelationship owns(Person person){
        final OwnsRelationship ownsRelationship = new OwnsRelationship(this, person);
        ownsRelationships.add(ownsRelationship);
        return ownsRelationship;
    }

}

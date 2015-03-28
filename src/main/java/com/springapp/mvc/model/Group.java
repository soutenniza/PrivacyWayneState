package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.Collection;

/**
 * Created by Zack on 3/28/15.
 */
@NodeEntity
public class Group {

    @GraphId
    private Long nodeID;
    private String name;

    @RelatedToVia
    Collection<MemberRelationship> memberRelationships;

    public Group() {
        // null
    }

    public Group(String name) {
        this.name = name;
    }

    public Long getNodeID(){
        return this.nodeID;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String sname){
        this.name = sname;
    }

    public MemberRelationship member(Person user){
        final MemberRelationship memberRelationship = new MemberRelationship(this, user);
        memberRelationships.add(memberRelationship);
        return memberRelationship;
    }

}

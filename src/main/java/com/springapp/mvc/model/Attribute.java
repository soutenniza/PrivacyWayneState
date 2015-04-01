package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.Collection;

/**
 * Created by Zack on 3/28/15.
 */
@NodeEntity
public class Attribute {

    @GraphId
    private Long nodeID;
    private String label;
    private String value;

    @RelatedToVia
    Collection<HasRelationship> hasRelationships;

    public Attribute() {

    }

    public Attribute(String label, String value) {
        this.label = label;
        this.value = normalizeString(value);
    }

    public String getLabel(){
        return this.label;
    }

    public void setLabel(String slabel){
        this.label = slabel;
    }

    public String getValue(){

        return this.value;
    }

    public void setValue(String svalue){
        this.value = normalizeString(svalue);
    }

    public Long getNodeID(){
        return nodeID;
    }

    public HasRelationship has(Person person, int p, int v, int s){
        final HasRelationship hasRelationship = new HasRelationship(person, this, p, v, s);
        hasRelationships.add(hasRelationship);
        return hasRelationship;
    }

    public String normalizeString(String val){
        if(val.length() == 0){
            return "";
        }
        if(val.length() == 1){
            return val.toUpperCase();
        }
        return val.substring(0,1).toUpperCase() + val.substring(1).toLowerCase();
    }
}

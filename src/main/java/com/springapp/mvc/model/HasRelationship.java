package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import java.util.ArrayList;

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
    private ArrayList<Double> attVisibilityRecord = new ArrayList<>();

    @StartNode
    Person user;

    @EndNode
    Attribute attribute;

    public HasRelationship(){
    }

    public HasRelationship(Person user, Attribute attribute, int pv, int vv, int sv){
        this.user = user;
        this.attribute = attribute;
        this.pv = pv;
        this.vv = vv;
        this.sv = sv;
        this.attVisibilityRecord.add(0.0);
    }

    public ArrayList<Double> getAttVisibilityRecord(){
        return this.attVisibilityRecord; }

    public void addToAttVisRecord(double val){
        //System.out.println("adding to attvisrec: "+ val);
        this.attVisibilityRecord.add(val); }

    public int getPv(){
        return pv;
    }

    public int getVv(){
        return vv;
    }

    public int getSv(){
        return sv;
    }

    public void setPv(int val) { pv = val; }

    public void setVv(int val) { vv = val; }

    public void setSv(int val) { sv = val; }

    public Attribute getEnd() { return attribute; }

    public Long getId() { return  id; }

}

package com.springapp.mvc.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import java.util.ArrayList;

/**
 * Created by vannguyen on 3/25/15.
 */
@RelationshipEntity(type = "FRIENDS")
public class FriendRelationship {

    @GraphId
    Long id;

    @StartNode
    Person user;

    @EndNode
    Person friend;

    private ArrayList<Integer> mutualFriendRecord = new ArrayList<>();
    private ArrayList<Integer> commonGroupRecord = new ArrayList<>();
    private ArrayList<Double> interactionsRecord = new ArrayList<>();
    private ArrayList<Double> socialDistanceRecord = new ArrayList<>();
    private ArrayList<Double> relationshipStrengthRecord = new ArrayList<>();
    private ArrayList<Double> outgoingCommunicationsRecord = new ArrayList<>();

    public FriendRelationship(){
        // null
    }

    public FriendRelationship(Person user, Person friend){
        this.user = user;
        this.friend = friend;
        this.mutualFriendRecord.add(0);
        this.commonGroupRecord.add(0);
        this.interactionsRecord.add(0.0);
        this.socialDistanceRecord.add(0.0);
        this.relationshipStrengthRecord.add(0.0);
        this.outgoingCommunicationsRecord.add(0.0);
    }

    public ArrayList<Integer> getMutualFriendRecord(){
        return this.mutualFriendRecord;
    }

    public ArrayList<Integer> getCommonGroupRecord() {
        return this.commonGroupRecord;
    }

    public ArrayList<Double> getInteractionsRecord() {
        return this.interactionsRecord;
    }

    public ArrayList<Double> getSocialDistanceRecord() {
        return this.socialDistanceRecord;
    }

    public ArrayList<Double> getRelationshipStrengthRecord() {
        return this.relationshipStrengthRecord;
    }

    public ArrayList<Double> getOutgoingCommunicationsRecord() {
        return this.outgoingCommunicationsRecord;
    }

    public void addToMutualFriendsRecord(int val){
        this.mutualFriendRecord.add(val);
    }

    public void addToCommonGroupsRecord(int val){
        this.commonGroupRecord.add(val);
    }

    public void addToInteractionsRecord(double val){
        this.interactionsRecord.add(val);
    }

    public void addToSocialDistanceRecord(double val){
        this.socialDistanceRecord.add(val);
    }

    public void addToRelationshipStrengthRecord(double val){
        this.relationshipStrengthRecord.add(val);
    }

    public void addToOutgoingCommunicationsRecord(double val){
        this.outgoingCommunicationsRecord.add(val);
    }

    public Person getFriend(){
        return friend;
    }

    public long getNodeID(){
        return id;
    }
}

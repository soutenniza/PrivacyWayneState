package com.springapp.mvc.model;

import org.neo4j.cypher.internal.compiler.v1_9.commands.Has;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;
import scala.Int;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Van on 3/17/2015.
 */
@NodeEntity
public class Person {

    @GraphId
    Long nodeID;
    public String name;

    @RelatedToVia
    Collection<FriendRelationship> friendRelationships;

    @RelatedToVia
    Collection<HasRelationship> hasRelationships;

    @RelatedToVia
    Collection<MemberRelationship> memberRelationships;

    @RelatedToVia
    Collection<OwnsRelationship> ownsRelationships;

//    @RelatedToVia
//    Collection<LikesRelationship> likesRelationships;

    Collection<Comment> comments;

    Collection<Person> friends;

    Collection<Attribute> attributes;

    Collection<Group> groups;

    Collection<Comment> likes;

    Collection<Comment> tempCommentCollection;

    ArrayList<Integer> privacyScoreRecord;

    ArrayList<Double> networkVisibilityRecord;

    ArrayList<AttData> attDatas;

    public Person() {}

    public Person(String name) {
        this.name = name;
        privacyScoreRecord = new ArrayList<>();
        privacyScoreRecord.add(0);
        attDatas = new ArrayList<>();
        attDatas.add(new AttData(Long.valueOf(0),"null",0)); // init
    }

    public String getName(){
        return this.name;
    }

    public void setName(String sname){
        this.name = sname;
    }

    public Long getNodeID(){
        return nodeID;
    }

    public void removeFriend(Person p){
        friends.remove(p);
    }

    // --------------------- record keeping ---------------------- //
    public void addPrivacyScoreRecord(int val){
        privacyScoreRecord.add(val);
    }
    public void addNetworkVisibilityRecord(double val){
        networkVisibilityRecord.add(val);
    }
    public ArrayList<Integer> getPrivacyScoreRecord(){
        return privacyScoreRecord;
    }
    public ArrayList<Double> getNetworkVisibilityRecord(){
        return networkVisibilityRecord;
    }

    public ArrayList<Double> getAttributeDataWithName(Long attId, String name){
        ArrayList<Double> vals = new ArrayList<>();
        for(AttData ad : attDatas){
            if(ad.getDataName().equals(name)){
                if(ad.getAttID().equals(attId)){
                    vals.add(ad.getDatVal());
                }
            }
        }
        return vals;
    }
    // ---------------------------------------------------------- //

    public FriendRelationship friends(Person friend){
        final FriendRelationship friendRelationship = new FriendRelationship(this, friend);
        friends.add(friend);
        friendRelationships.add(friendRelationship);
        return friendRelationship;
    }

    public HasRelationship has(Attribute attribute, int p, int v, int s){
        final HasRelationship hasRelationship = new HasRelationship(this, attribute, p, v, s);
        attributes.add(attribute);
        hasRelationships.add(hasRelationship);
        return hasRelationship;
    }

    public MemberRelationship member(Group g){
        final MemberRelationship memberRelationship = new MemberRelationship(g, this);
        groups.add(g);
        memberRelationships.add(memberRelationship);
        return memberRelationship;
    }

    public OwnsRelationship owns(Comment c){
        final OwnsRelationship ownsRelationship = new OwnsRelationship(c, this);
        comments.add(c);
        ownsRelationships.add(ownsRelationship);
        return ownsRelationship;
    }

    public void removeLike(Comment c){
        this.tempCommentCollection.clear();
        for(Comment inc : this.getLikes()){
            if(inc.getNodeID().equals(c.getNodeID())){
                // don't add!
            }
            else{
                tempCommentCollection.add(inc);
            }
        }
        this.likes.clear();
        for(Comment inc : tempCommentCollection){
            this.likes.add(inc);
        }
    }

    public void addLike(Comment c){
        this.likes.add(c);
    }

//    public LikesRelationship likes(Comment c){
//        final LikesRelationship likesRelationship = new LikesRelationship(c, this);
//        likes.add(c);
//        likesRelationships.add(likesRelationship);
//        return likesRelationship;
//    }

    public Collection<Comment> getComments(){
        return comments;
    }

    public Collection<Person> getFriends(){
        return friends;
    }

    public Collection<Group> getGroups(){
        return groups;
    }

    public Collection<Comment> getLikes() { return likes; }

    public Collection<FriendRelationship> getFriendRelationships(){
        return friendRelationships;
    }

    public Collection<Attribute> getAttributes() {
        return attributes;
    }

    public Collection<HasRelationship> getAttributeRelationships() {
        return hasRelationships;
    }

    public Collection<OwnsRelationship> getCommentRelationships() {
        return ownsRelationships;
    }

//    public Collection<LikesRelationship> getLikesRelationships() {
//        return likesRelationships;
//    }


}

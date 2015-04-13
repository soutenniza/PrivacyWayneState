package com.springapp.mvc.service;

import com.springapp.mvc.model.*;
import com.springapp.mvc.repository.*;
import org.neo4j.cypher.internal.compiler.v1_9.commands.Has;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Van on 3/20/2015.
 */
@Service
@Transactional
public class PersonService {
    @Autowired
    private Neo4jTemplate template;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private HasRepository hasRepository;
    @Autowired
    ContentAnalysisService contentAnalysisService;

    public void addFriend(Person friend, Person user){
        user.friends(friend);
        template.save(user);
    }

    public boolean createComment(Long pid, String text){
        if(commentExists(text)){
            System.out.println("Comment exists!");
            return false;
        }
        else {
            Comment c = new Comment();
            c.setOwnerId(pid);
            c.setText(text);
            c.setSentiment(contentAnalysisService.runSentimentAnalysisForText(text));
            commentRepository.save(c);
            addComment(c, getPerson(pid));
            return true;
        }
    }

    public boolean createReply(Long personId, Long parentId, String text){

        Comment c = new Comment();
        c.setOwnerId(personId);
        c.setText(text);
        c.setSentiment(contentAnalysisService.runSentimentAnalysisForText(text));
        c.setParentId(parentId);
        c.setAsReply();
        commentRepository.save(c);
        addComment(c, getPerson(personId));
        addReply(getComment(parentId).getNodeID(), c);
        commentRepository.save(getComment(parentId));

//        System.out.println("comment created");
//        System.out.println("owner: " + getPerson(personId).getName());
//        System.out.println("parentid: " + c.getParentID());
//        System.out.println("is root: " + c.isRoot());
//        System.out.println("parent has children: " + getComment(c.getParentID()).hasChildren());
//        System.out.println("is parent root: " + getComment(c.getParentID()).isRoot());
//        System.out.println("has children: " + c.hasChildren());

        // always true
        return true;

    }

    public void addMember(Group group, Person user){
        user.member(group);
        template.save(user);
    }

    public void addAttribute(Attribute attribute, Person user, int p, int v, int s){
        Person thePerson = getAllPersons().get(0);
        boolean found = false;
        ArrayList<Person> pp = getAllPersons();
        for(Person tp : pp){
            if(tp.getName().equals(user.getName())){
                thePerson = tp;
                found = true;
            }
        }
        if(found) {
            HasRelationship has = thePerson.has(attribute, p, v, s);
            hasRepository.save(has);
            template.save(thePerson);
        }
    }

    public Person createPerson(String name){
        Person p = new Person(name);
        personRepository.save(p);
        return p;
    }

    public Attribute createAttribute(String label, String value){
        value = value.trim();
        if(attributeExists(label, value)){
            Attribute a = getAttribute(label, value);
            return a;
        }
        else{
            Attribute a = new Attribute(label, value);
            attributeRepository.save(a);
            return a;
        }
    }

    public Attribute getAttribute(String label, String value){
        Attribute att = getAllAttributes().get(0);
        ArrayList<Attribute> attributes = getAllAttributes();
        for (Attribute a : attributes) {
            if ((a.getLabel().equals(label))&&(a.getValue().equals(value))) {
                att = a;
            }
        }
        return att;
    }

    public boolean attributeExists(String label, String value){
        ArrayList<Attribute> attributes = getAllAttributes();
        boolean found = false;
        for (Attribute a : attributes) {
            if ((a.getLabel().equals(label))&&(a.getValue().equals(value))) {
                found = true;
                break;
            }
        }
        return found;
    }

    public void addComment(Comment comment, Person person){
        person.owns(comment);
        template.save(person);
    }

    public void addReply(Long cid, Comment c){
        getComment(cid).addReply(c);
    }


    // --------------------- record keeping ---------------------- //
    public void addToPrivacyScoreRecord(int val, Long pid){
        Person p = getPerson(pid);
        System.out.println(val);
        p.addPrivacyScoreRecord(val);
        template.save(p);
    }
    public ArrayList<Integer> getPrivacyScoreRecord(Long pid) {
        return getPerson(pid).getPrivacyScoreRecord();
    }
    public int getLatestPrivacyScore(Long pid) {
        if (getPerson(pid).getPrivacyScoreRecord().isEmpty()) {
            return -1;
        } else {
            return getPerson(pid).getPrivacyScoreRecord().get(getPerson(pid).getPrivacyScoreRecord().size()-1);
        }
    }
    public void addToAttData(Long pid, Long attid, String name, double val){
        Person p = getPerson(pid);
        p.addAttData(attid,name,val);
        template.save(p);
    }
    public ArrayList<Double> getAttDataWithName(Long pid, Long attid, String name){
        Person p = getPerson(pid);
        ArrayList<Double> vals = p.getAttributeDataWithName(attid, name);
        return  vals;
    }
    // ----------------------------------------------------------- //

//    public void addLike(Comment c, Person p){
//        p.likes(c);
//        template.save(p);
//    }

    public void toggleLike(Person p, Comment c){
        Collection<Comment> likes = getPerson(p.getNodeID()).getLikes();
        c = getComment(c.getNodeID());
        if(likesComment(c,p)){
            p.removeLike(c);
            System.out.println("unliking comment");
        }
        else{
            p.addLike(c);
            System.out.println("liking comment");
        }
    }

    public int getNumLikes(Long cid){
        int numLikes = 0;
        Collection<Person> allPersons = getAllPersons();
        for(Person p : allPersons){
            Collection<Comment> personsLikes = p.getLikes();
            for(Comment lc : personsLikes){
                if(cid.equals(lc.getNodeID())){
                    numLikes = numLikes + 1;
                }
            }
        }
        return numLikes;
    }

    public ArrayList<Person> getAllPersons(){
        ArrayList<Person> people = new ArrayList<Person>();
        people.addAll(personRepository.findAll().as(ArrayList.class));
        return people;
    }

    public boolean groupExists(String name) {
        ArrayList<Group> groups = getAllGroups();
        boolean found = false;
        for (Group g : groups) {
            if (g.getName().equals(name)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean personExists(String name) {
        ArrayList<Person> persons = getAllPersons();
        boolean found = false;
        for (Person p : persons) {
            if (p.getName().equals(name)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean areFriends(Person p1, Person p2){
        Collection<Person> persons = p1.getFriends();
        boolean found = false;
        if(persons.isEmpty())
            return false;
        for (Person p : persons) {
            if (p.getNodeID().equals(p2.getNodeID())) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean commentExists(String t){
        Collection<Comment> comments = getAllComments();
        boolean found = false;
        if(comments.isEmpty())
            return false;
        for (Comment cs : comments) {
            if (cs.getText().equals(t)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean isMember(Group group, Person person){
        Collection<Group> groups = person.getGroups();
        boolean found = false;
        if(groups.isEmpty())
            return false;
        for (Group g : groups) {
            if (g.getNodeID().equals(group.getNodeID())) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean likesComment(Comment c, Person p){
        Collection<Comment> likes = p.getLikes();
        boolean found = false;
        if(likes.isEmpty())
            return false;
        for (Comment lc : likes) {
            if (c.getNodeID().equals(lc.getNodeID())) {
                found = true;
                break;
            }
        }
        return found;
    }

    public Person getPerson(Long id){ return  personRepository.findOne(id); }

    public Group getGroup(Long id) { return groupRepository.findOne(id); }

    public HasRelationship getHasRelationship(Long id) { return hasRepository.findOne(id); }

    public Comment getComment(Long id) { return commentRepository.findOne(id); }

    public Attribute getAttributeWithId(Long id) { return attributeRepository.findOne(id); }

    public ArrayList<Group> getAllGroups(){
        ArrayList<Group> groups = new ArrayList<Group>();
        groups.addAll(groupRepository.findAll().as(ArrayList.class));
        return groups;
    }

    public ArrayList<Comment> getAllComments(){
        ArrayList<Comment> comments = new ArrayList<Comment>();
        comments.addAll(commentRepository.findAll().as(ArrayList.class));
        return comments;
    }

    public Person getCommentOwner(Comment c){
        return getPerson(c.getOwnerID());
    }

    public ArrayList<Attribute> getAllAttributes(){
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        attributes.addAll(attributeRepository.findAll().as(ArrayList.class));
        return attributes;
    }

    public void deleteGroup(Long id){
        Group g = groupRepository.findOne(id);
        groupRepository.delete(g);
    }

    public void removeUser(Long id){
        Person p = personRepository.findOne(id);
        personRepository.delete(p);
    }

    public void deleteComment(Long id){
        Comment c = commentRepository.findOne(id);
        commentRepository.delete(c);
    }

    public void deleteAttribute(Long id){
        Attribute a = attributeRepository.findOne(id);
        attributeRepository.delete(a);
    }

    public void deleteAll(){
        for(Group g : getAllGroups()){
            deleteGroup(g.getNodeID());
        }
        for(Person p : getAllPersons()){
            removeUser(p.getNodeID());
        }
        for(Comment c : getAllComments()){
            deleteComment(c.getNodeID());
        }
        for(Attribute a : getAllAttributes()){
            deleteAttribute(a.getNodeID());
        }
    }

    public void removeFriendship(Person p1, Person p2){
        p1.removeFriend(p2);
        p2.removeFriend(p1);
        template.deleteRelationshipBetween(p1, p2, "FriendRelationship");
        template.deleteRelationshipBetween(p2, p1, "FriendRelationship");
        template.save(p1);
        template.save(p2);
    }

    public Long getPersonByName(String name) {
        ArrayList<Person> persons = getAllPersons();
        for (Person p : persons) {
            if (p.getName().equals(name)) {
                return p.getNodeID();
            }
        }
        return null;
    }

    public Long getGroupByName(String name) {
        ArrayList<Group> groups = getAllGroups();
        for (Group g : groups) {
            if (g.getName().equals(name)) {
                return g.getNodeID();
            }
        }
        return null;
    }

}

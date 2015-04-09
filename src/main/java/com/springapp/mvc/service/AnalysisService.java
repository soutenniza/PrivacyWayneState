package com.springapp.mvc.service;

import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.HasRelationship;
import com.springapp.mvc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Van on 3/29/2015.
 */
@Service
@Transactional
public class AnalysisService {

    @Autowired
    PersonService personService;

    @Autowired
    ContentAnalysisService contentAnalysis;

    @Autowired
    RelationshipAnalysisService relationshipAnalysis;

    @Autowired
    GroupAnalysisService groupAnalysis;

    @Autowired
    PrivacyProfileAnalysisService privacyProfileAnalysisService;


    private Person root;

    public void setRoot(Person root){
        this.root = root;
    }

    public ArrayList<String> fullAnalysis(){
        ArrayList<String> allMessages;
        allMessages = relationshipAnalysis.calculateRelationshipStrength(root);
        allMessages.addAll(contentAnalysis.calculateAll(root));
        //TODO: Dishank: add a call to group analysis service methods here

        return allMessages;
    }

    public ArrayList<String> calculateSingleFriend(Person friend){
        ArrayList<String> allMessages;
        allMessages = relationshipAnalysis.calculateSingleFriend(root, friend);
        return allMessages;
    }

    public double getPrivacyScore(Person p){
        return  privacyProfileAnalysisService.getPrivacyScore(personService.getPerson(p.getNodeID()));
    }

    public double getThresholdRS(Person p){
        return relationshipAnalysis.getThresholdRS(personService.getPerson(p.getNodeID()));
    }

    public double getRelationshipStrength(Person r, Person friend){
        return relationshipAnalysis.calculateRelationshipStrength(personService.getPerson(r.getNodeID()), personService.getPerson(friend.getNodeID()));
    }

}

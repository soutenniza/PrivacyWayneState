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

    private Person root;

    public void setRoot(Person root){
        this.root = root;
    }

    public ArrayList<String> fullAnalysis(){
        ArrayList<String> allMessages;
        allMessages = relationshipAnalysis.calculateRelationshipStrength(root);
        allMessages.addAll(contentAnalysis.calculateAll(root));


        return allMessages;
    }

    public ArrayList<String> calculateSingleFriend(Person friend){
        ArrayList<String> allMessages;
        allMessages = relationshipAnalysis.calculateSingleFriend(root, friend);


        return allMessages;
    }

}

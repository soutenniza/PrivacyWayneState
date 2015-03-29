package com.springapp.mvc.analysis;

import com.springapp.mvc.model.FriendRelationship;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Van on 3/29/2015.
 */
@Component
@Transactional
public class RelationshipAnalysisHandler {
    @Autowired
    PersonService service;

    private Person root;

    public RelationshipAnalysisHandler(){}

    public RelationshipAnalysisHandler(Person root){
        this.root = root;
    }

    public void calculateRelationshipStrength(){
        Collection<FriendRelationship> friendRelationships = root.getFriendRelationships();
        ArrayList<Person> persons = new ArrayList<>();
        for(FriendRelationship f : friendRelationships){
            persons.add(f.getFriend());
        }

    }
}

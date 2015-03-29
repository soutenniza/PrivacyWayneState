package com.springapp.mvc.service;

import com.springapp.mvc.analysis.MutualFriendsAnalysisHandler;
import com.springapp.mvc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Van on 3/29/2015.
 */
@Service
@Transactional
public class AnalysisService {
    @Autowired
    PersonService personService;

    private Person root;

    public void setRoot(Person root){
        this.root = root;
    }

    public void fullAnalysis(){
        MutualFriendsAnalysisHandler mutualFriendsAnalysisHandler = new MutualFriendsAnalysisHandler(root, personService.getAllPersons());
    }



}

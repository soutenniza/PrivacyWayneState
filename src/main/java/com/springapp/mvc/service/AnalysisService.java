package com.springapp.mvc.service;

import com.springapp.mvc.analysis.RelationshipAnalysisHandler;
import com.springapp.mvc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
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
        RelationshipAnalysisHandler relationshipAnalysisHandler = new RelationshipAnalysisHandler(root);
        //relationshipAnalysisHandler.calculateRelationshipStrength();
    }



}

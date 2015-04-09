package com.springapp.mvc.service;

import com.springapp.mvc.model.HasRelationship;
import com.springapp.mvc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by narimanammar on 4/7/15.
 */

@Service
@Transactional
public class PrivacyProfileAnalysisService {

    @Autowired
    PersonService service;

    /**
     *
     * Planned score based on static sensitivity and visibility in privacy settings
     * @param p
     * @return
     */
    public int getPrivacyScore(Person p){
        Person person = service.getPerson(p.getNodeID());
        Collection<HasRelationship> relationships = person.getAttributeRelationships();
        int score = 0;
        for(HasRelationship r : relationships){
            score += r.getVv()*r.getSv();
        }

        // append to record
        service.addToPrivacyScoreRecord(score, p.getNodeID());

        return score;
    }

    /**
     * Actual score Based on dynamic sensitivity and visibility values
     * @param p
     * @return
     */
    public int getDynamicPrivacyScore(Person p){
        //TODO: implement me.
        return 0;
    }



}


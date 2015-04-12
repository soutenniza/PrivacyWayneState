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

    public double getAttributeExposure(Person p, HasRelationship r, int total){
        int visibility = service.getHasRelationship(r.getId()).getVv();
        int networksize = total;
        int networkvisibility = getNumberOfFriends(service.getPerson(p.getNodeID()), visibility);
        if(networkvisibility == 0){
            return 0.0;
        }else{
            return (double) networkvisibility / (double) networksize;
        }

    }

    public int getNumberOfFriends(Person p, int visibility){
        if(visibility == 0) {
            return 0;
        }

        ArrayList<Person> friends = new ArrayList<>();
        friends.addAll(p.getFriends());
        ArrayList<Long> nodeFriends = new ArrayList<>();
        for(Person pp : friends){
            nodeFriends.add(pp.getNodeID());
        }

        if(visibility == 1) {
            return nodeFriends.size();
        }

        for(int i =0; i < nodeFriends.size(); i++){
            ArrayList<Person> fofFriends = new ArrayList<>();
            fofFriends.addAll(service.getPerson(nodeFriends.get(i)).getFriends());
            for(Person pp : friends) {
                if(!nodeFriends.contains(pp.getNodeID())){
                    nodeFriends.add(pp.getNodeID());
                }
            }
        }
        if(visibility == 2) {
            return nodeFriends.size();
        }

        for(int i =0; i < nodeFriends.size(); i++){
            ArrayList<Person> fofFriends = new ArrayList<>();
            fofFriends.addAll(service.getPerson(nodeFriends.get(i)).getFriends());
            for(Person pp : friends) {
                if(!nodeFriends.contains(pp.getNodeID())){
                    nodeFriends.add(pp.getNodeID());
                }
            }
        }
        if(visibility == 3) {
            return nodeFriends.size();
        }

        return service.getAllPersons().size();
    }



}


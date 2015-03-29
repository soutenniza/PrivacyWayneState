package com.springapp.mvc.service;

import com.springapp.mvc.analysis.RelationshipAnalysisHandler;
import com.springapp.mvc.model.FriendRelationship;
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

    private Person root;

    public void setRoot(Person root){
        this.root = root;
    }

    public ArrayList<String> fullAnalysis(){
        ArrayList<String> allMessages;
        allMessages = calculateAllMutualFriends();

        return allMessages;
    }

    private ArrayList<String> calculateAllMutualFriends() {
        Collection<Person> friends = root.getFriends();
        ArrayList<Integer> mutualFriends = new ArrayList<>();
        ArrayList<Long> mutualFriendID  = new ArrayList<>();
        ArrayList<String> messaages = new ArrayList<>();
        for(Person p : friends){
            Long pID = p.getNodeID();
            int mt = mutualfriends(root, personService.getPerson(pID));
            mutualFriends.add(mt);
            mutualFriendID.add(pID);
        }
        double threshold = 0;



        for(int i = 0; i < mutualFriends.size(); i++){
            threshold += mutualFriends.get(i);
        }

        threshold /= mutualFriends.size();
        double average = threshold;
        threshold = threshold - threshold/mutualFriends.size();

        for(int i = 0; i < mutualFriends.size(); i++){
            if(mutualFriends.get(i) < threshold){
                String msg = String.format("%s has a low number of mutual friends. SCORE: %d  AVERAGE: %.2f THRESHOLD: %.2f", personService.getPerson(mutualFriendID.get(i)).getName(), mutualFriends.get(i), average, threshold);
                messaages.add(msg);
                //System.out.println(msg);
            }
        }
        return messaages;
    }

    public int mutualfriends(Person r, Person p){
        Collection<Person> rootFriends = root.getFriends();
        ArrayList<Long> rootLong = new ArrayList<>();
        for(Person person : rootFriends){
            if(person.getNodeID() != r.getNodeID()){
                rootLong.add(person.getNodeID());
            }
        }
        Collection<Person> pFriends = p.getFriends();
        ArrayList<Long> pLong = new ArrayList<>();
        for(Person person : pFriends){
            if(person.getNodeID() != p.getNodeID()){
                pLong.add(person.getNodeID());
            }

        }
        rootLong.retainAll(pLong);

        return rootLong.size();
    }



}

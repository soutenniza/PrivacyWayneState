package com.springapp.mvc.service;

import com.springapp.mvc.model.FriendRelationship;
import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.HasRelationship;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Van on 3/29/2015.
 */
@Service
@Transactional
public class RelationshipAnalysisService {
    @Autowired
    PersonService service;

    private Person root;

    public RelationshipAnalysisService(){}

    public RelationshipAnalysisService(Person root){
        this.root = root;
    }

    public ArrayList<String> calculateRelationshipStrength(Person root){
        this.root = root;
        ArrayList<String> allMessages;
        allMessages = calculateAll();


        return allMessages;

    }
    private ArrayList<String> calculateAll(){
        Collection<Person> friends = root.getFriends();
        ArrayList<Integer> privacyScores = new ArrayList<>();
        ArrayList<Integer> mutualfriends = new ArrayList<>();
        ArrayList<Integer> commonGroups = new ArrayList<>();
        ArrayList<Long> privacyScoresID  = new ArrayList<>();
        ArrayList<String> messaages = new ArrayList<>();
        for(Person p : friends){
            Long pID = p.getNodeID();
            int ps = getPrivacyScore(service.getPerson(pID));
            int mt = mutualfriends(root, service.getPerson(pID));
            int mg = mutualgroups(root, service.getPerson(pID));
            mutualfriends.add(mt);
            commonGroups.add(mg);
            privacyScores.add(ps);
            privacyScoresID.add(pID);
        }

        double thresholdPS = 0;
        double thresholdMT = 0;
        double thresholdMG = 0;

        for(int i = 0; i < privacyScores.size(); i++){
            thresholdPS += privacyScores.get(i);
            thresholdMT += mutualfriends.get(i);
            thresholdMG += commonGroups.get(i);
        }

        double avgPS = thresholdPS / privacyScores.size();
        double avgMT = thresholdMT / privacyScores.size();
        double avgMG = thresholdMG / privacyScores.size();
        thresholdPS = avgPS + avgPS/privacyScores.size();
        thresholdMT = avgMT - avgMT/privacyScores.size();
        thresholdMG = avgMG - avgMG/privacyScores.size();

        for(int i = 0; i < privacyScores.size(); i++){
            if(privacyScores.get(i) > thresholdPS){
                String msg = String.format("%s has a high Privacy Score. SCORE: %d  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(privacyScoresID.get(i)).getName(), privacyScores.get(i), avgPS, thresholdPS);
                messaages.add(msg);
            }
        }


        for(int i = 0; i < mutualfriends.size(); i++){
            if(mutualfriends.get(i) < thresholdMT){
                String msg = String.format("%s has a low number of mutual friends. SCORE: %d  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(privacyScoresID.get(i)).getName(), mutualfriends.get(i), avgMT, thresholdMT);
                messaages.add(msg);
            }
        }

        for(int i = 0; i < commonGroups.size(); i++){
            if(commonGroups.get(i) < thresholdMG){
                String msg = String.format("%s has a low number of mutual groups. SCORE: %d  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(privacyScoresID.get(i)).getName(), commonGroups.get(i), avgMG, thresholdMG);
                messaages.add(msg);
            }
        }

        return messaages;
    }

    private ArrayList<String> calculateAllPrivacyScore(){
        Collection<Person> friends = root.getFriends();
        ArrayList<Integer> privacyScores = new ArrayList<>();
        ArrayList<Long> privacyScoresID  = new ArrayList<>();
        ArrayList<String> messaages = new ArrayList<>();
        for(Person p : friends){
            Long pID = p.getNodeID();
            int ps = getPrivacyScore(service.getPerson(pID));
            privacyScores.add(ps);
            privacyScoresID.add(pID);
        }

        double threshold = 0;

        for(int i = 0; i < privacyScores.size(); i++){
            threshold += privacyScores.get(i);
        }

        threshold /= privacyScores.size();
        double avg = threshold;
        threshold = threshold + threshold/privacyScores.size();

        for(int i = 0; i < privacyScores.size(); i++){
            if(privacyScores.get(i) > threshold){
                String msg = String.format("%s has a high Privacy Score. SCORE: %d  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(privacyScoresID.get(i)).getName(), privacyScores.get(i), avg, threshold);
                messaages.add(msg);
            }
        }

        return messaages;
    }

    private int getPrivacyScore(Person p){
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

    private ArrayList<String> calculateAllMutualFriends() {
        Collection<Person> friends = root.getFriends();
        ArrayList<Integer> mutualFriends = new ArrayList<>();
        ArrayList<Long> mutualFriendID  = new ArrayList<>();
        ArrayList<String> messaages = new ArrayList<>();
        for(Person p : friends){
            Long pID = p.getNodeID();
            int mt = mutualfriends(root, service.getPerson(pID));
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
                String msg = String.format("%s has a low number of mutual friends. SCORE: %d  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(mutualFriendID.get(i)).getName(), mutualFriends.get(i), average, threshold);
                messaages.add(msg);
                //System.out.println(msg);
            }
        }
        return messaages;
    }

    private ArrayList<String> calculateAllMutualGroups() {
        Collection<Person> friends = root.getFriends();
        ArrayList<Integer> mutualGroups = new ArrayList<>();
        ArrayList<Long> mutualGroupID  = new ArrayList<>();
        ArrayList<String> messaages = new ArrayList<>();
        for(Person p : friends){
            Long pID = p.getNodeID();
            int mt = mutualgroups(root, service.getPerson(pID));
            mutualGroups.add(mt);
            mutualGroupID.add(pID);
        }
        double threshold = 0;



        for(int i = 0; i < mutualGroups.size(); i++){
            threshold += mutualGroups.get(i);
        }

        threshold /= mutualGroups.size();
        double average = threshold;
        threshold = threshold - threshold/mutualGroups.size();

        for(int i = 0; i < mutualGroups.size(); i++){
            if(mutualGroups.get(i) < threshold){
                String msg = String.format("%s has a low number of mutual groups. SCORE: %d  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(mutualGroupID.get(i)).getName(), mutualGroups.get(i), average, threshold);
                messaages.add(msg);
            }
        }
        return messaages;
    }

    public ArrayList<String> calculateSingleFriend(Person root, Person friend){
        this.root = root;
        Collection<Person> friends = root.getFriends();
        ArrayList<Integer> mutualFriends = new ArrayList<>();
        ArrayList<Long> mutualFriendID  = new ArrayList<>();
        ArrayList<String> messaages = new ArrayList<>();
        for(Person p : friends){
            Long pID = p.getNodeID();
            int mt = mutualfriends(root, service.getPerson(pID));
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

        int mt = mutualfriends(root, service.getPerson(friend.getNodeID()));
        if(mt < threshold) {
            String msg = String.format("%s has a low number of mutual friends. SCORE: %d  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(friend.getNodeID()).getName(), mt, average, threshold);
            messaages.add(msg);
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

    public int mutualgroups(Person r, Person g){
        Collection<Group> rootGroups = root.getGroups();
        ArrayList<Long> rootLong = new ArrayList<>();
        for(Group group : rootGroups){
            if(group.getNodeID() != r.getNodeID()){
                rootLong.add(group.getNodeID());
            }
        }
        Collection<Group> gGroups = g.getGroups();
        ArrayList<Long> gLong = new ArrayList<>();
        for(Group group : gGroups){
            if(group.getNodeID() != g.getNodeID()){
                gLong.add(group.getNodeID());
            }

        }
        rootLong.retainAll(gLong);

        return rootLong.size();
    }

}

package com.springapp.mvc.service;

import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Dishank on 4/6/2015.
 */
@Service
@Transactional
public class GroupAnalysisService {

    @Autowired
    PersonService service;

    private Person root;

    public void setRoot(Person root) {
        this.root = root;
    }

    public ArrayList<String> calculateFriendInGroup(Group group){ /*took same approach as in AnalysisService*/
        ArrayList<String> allMessages;
        allMessages = calculateSingleGroup(root, group);

        return allMessages;
    }

    public ArrayList<String> calculateSingleGroup(Person root, Group group){
        this.root = root;
        Collection<Person> friends = root.getFriends();
        ArrayList<Float> friendsInGroup = new ArrayList<>();
        ArrayList<Long> friendsInGroupID  = new ArrayList<>();
        ArrayList<String> messages = new ArrayList<>();
        float count = 0;
        for(Person p : friends){
            Long pID = p.getNodeID();
            if(service.isMember(service.getGroup(group.getNodeID()), service.getPerson(p.getNodeID()))) {
                count = count+1;
            }
            friendsInGroup.add(count);
            friendsInGroupID.add(pID);
        }

        /*int GroupSize = service.getGroup(group.getNodeID()).getMembers().size();*/
        float averageMPG = count/3;

        double threshold = .25;

        if(averageMPG < .26){
            String msg = String.format("%s has a low number of friends that are members. SCORE: %.1f  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(group.getNodeID()).getName(), count, averageMPG, threshold);
            messages.add(msg);
        }

        else{
            String msg = String.format("%s has a low number of friends that are members. SCORE: %.1f  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(group.getNodeID()).getName(), count, averageMPG, threshold);
            messages.add(msg);
        }

        return messages;
    }

    public int mutualgroups(Person r, Person g){
        Collection<Group> rootGroups = r.getGroups();
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

    public int commonGroups(Person r, Person p){

        Collection<Group> rootGroups = null; //TODO: get groups
        ArrayList<Long> rootLong = new ArrayList<>();

        //TODO: implement me. Pay attention this is the normalized result
        return rootLong.size();
    }
}

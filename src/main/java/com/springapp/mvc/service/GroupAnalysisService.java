package com.springapp.mvc.service;

import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by narimanammar on 4/7/15.
 */
@Service
@Transactional
public class GroupAnalysisService {

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

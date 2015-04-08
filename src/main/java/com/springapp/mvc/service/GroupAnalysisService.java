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

    /**
     * * gets common groups between a friend and each of the mutual friends between him
     * and each friend in his friends list
     * @param r
     * @param g
     * @return
     */
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

    /**
     * gets common groups between a friend and each friend in his friends list
     * @param r
     * @param p
     * @return
     */
    public int commonGroups(Person r, Person p){
        Collection<Group> rootGroups = null; //TODO: get groups
        ArrayList<Long> rootLong = new ArrayList<>();

        //TODO: implement me. Pay attention this is the normalized result
        return rootLong.size();
    }


    /**
     * Group information that we cannot directly pull but can infer:
     -overlaps and containment within groups based on common attributes between a person and
     persons in his network (friends and friends of friends).
     a. family members
     b. high school friends
     c. college friends
     d. friends under same advisor
     e.g. all people in a user's network that are at WSU,
     user's that are engineering,
     CS users, ACM group,
     * @param p
     * @return
     */
    public int detectCommunitities(Person p){
        //TODO: Dishank: implement me
    return 0;
    }

    /**
     * Based on the above find commonalities in privacy settings then notify based on
     * outliers.
     * For all attributes in p's profile, if on average group members are conservative
     * regarding attribute dk then whoever falls below this average is an outlier
     * @return
     */
    public int detectPrivacyOutliers(){

        return 0;
    }
}

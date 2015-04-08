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
     *  These methods detect Overlaps and containment within network users based on common attributes
     *  between a person and persons in his network (friends and friends of friends).
     *  This information is what we cannot directly pull but can infer
     *  A good example on this is (WSU contains the CoE college which contains the CS department)
     */

    /**
     * Users who went to the same high school to which you went
     * @param p
     * @return
     */
    public int detectHighSchoolFriends(Person p){

        //TODO: Dishank: implement me
    return 0;
    }


    /**
     * Users who went to the same department in the same college in the same school you are/were at
     * @param p
     * @return
     */
    public int detectDepartmentFriends(Person p){

        //TODO: Dishank: implement me
        return 0;
    }

    /**
     * Users who went to the same college at the same school where you study/studied
     * @param p
     * @return
     */
    public int detectCollegeFriends(Person p){

        //TODO: Dishank: implement me
        return 0;
    }



    /**
     * Users who went to the same school to which you went
     * @param p
     * @return
     */
    public int detectSchoolFriends(Person p){

        //TODO: Dishank: implement me
        return 0;
    }



    /**
     * The following methods find commonalities in privacy settings (for each of the above detected communities) then notify based on
     * outliers.
     * For all attributes in p's profile, if on average group members are conservative
     * regarding attribute dk then whoever falls below this average is an outlier
     * this should incorporate all privacy settings from p's profile including:
     * privacy level, sensitivity, and visibility
     * @return
     */
    public int detectHighSchoolFriendsPrivacyOutliers(Person p){

        return 0;
    }

    public int detectDepartmentFriendsPrivacyOutliers(Person p){

        return 0;
    }

    public int detectCollegeFriendsPrivacyOutliers(Person p){

        return 0;
    }

    public int detectSchoolFriendsPrivacyOutliers(Person p){

        return 0;
    }

}

package com.springapp.mvc.service;

import com.springapp.mvc.model.Attribute;
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
        String location = "";
        ArrayList<String> allMessages;
        allMessages = FriendsInSingleGroup(root, group);

        Collection<Attribute> atts = service.getPerson(root.getNodeID()).getAttributes();
        for (Attribute att : atts) {
            Long aID = att.getNodeID();
            if (service.getAttributeWithId(aID).getLabel().contains("location")) {
                location = (service.getAttributeWithId(aID).getValue());
            }


            if (service.getAttributeWithId(aID).getValue().contains("GED")){
                detectHighSchoolFriends(root, location);
            }
            if ((service.getAttributeWithId(aID).getValue().contains("4-year degree")) || (service.getAttributeWithId(aID).getValue().contains("PHD")) || (service.getAttributeWithId(aID).getValue().contains("Masters"))){
                detectCollegeFriends(root, location);
            }

        }

        return allMessages;
    }

    public ArrayList<String> FriendsInSingleGroup(Person root, Group group){
        this.root = root;
        Collection<Person> friends = root.getFriends(); /*Gets the list of friends of the selected user*/
        ArrayList<Float> friendsInGroup = new ArrayList<>();
        ArrayList<Long> friendsInGroupID  = new ArrayList<>();
        ArrayList<String> messages = new ArrayList<>();
        float count = 0;
        for(Person p : friends){ /*iterating through the list of friends*/
            Long pID = p.getNodeID();
            if(service.isMember(service.getGroup(group.getNodeID()), service.getPerson(p.getNodeID()))) { /*see is the user's friends are in the selected group*/
                count = count+1;
            }
            friendsInGroup.add(count);
            friendsInGroupID.add(pID);
        }

        /*int GroupSize = service.getGroup(group.getNodeID()).getMembers().size();*/
        float averageMPG = count/3; /*sub 3 with group size*/

        double threshold = .25;

        if(averageMPG < .26){
            String msg = String.format("%s has a low number of friends that are members. Friends in Group: %.1f  Members of Group that are friends: %.2f THRESHOLD: %.2f", service.getPerson(group.getNodeID()).getName(), count, averageMPG, threshold);
            messages.add(msg);
        }

        /*else{
            String msg = String.format("%s has a low number of friends that are members.  Friends in Group: %.1f  Members of Group that are friends: %.2f THRESHOLD: %.2f", service.getPerson(group.getNodeID()).getName(), count, averageMPG, threshold);
            messages.add(msg);
        }*/

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
        //TODO: if the "friendsInGroup metric satisfies this, please delete. Otherwise provide an example of what is does and how is it different from Mutual Groups

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
    public ArrayList<String> detectHighSchoolFriends(Person p, String location){
        Collection<Person> friends = p.getFriends(); /*Gets the list of friends of the selected user*/
        ArrayList<String> messages = new ArrayList<>();
        String findCity = null;

        for(Person person : friends) {
            Collection<Attribute> atts = service.getPerson(person.getNodeID()).getAttributes();
            for (Attribute att : atts) {
                Long aID = att.getNodeID();
                if (service.getAttributeWithId(aID).getLabel().contains("education")) {
                    String education = (service.getAttributeWithId(aID).getValue());
                    if (service.getAttributeWithId(aID).getValue().contains("GED")){
                        findCity = "True";
                    } else {
                        findCity = "False";
                        System.out.println(service.getPerson(person.getNodeID()).getName() + " did not attend the same HighSchool as " + service.getPerson(p.getNodeID()).getName());
                    }
                }
            }

            if (findCity == "True") {
                Collection<Attribute> attrs = service.getPerson(person.getNodeID()).getAttributes();
                for (Attribute att : attrs) {
                    Long aID = att.getNodeID();
                    if (service.getAttributeWithId(aID).getLabel().contains("location")) {
                        String city = (service.getAttributeWithId(aID).getValue());
                        if (location.equals(city)) {
                            System.out.println(service.getPerson(person.getNodeID()).getName() + " attended the same HighSchool as " + service.getPerson(p.getNodeID()).getName());
                        } else {
                            System.out.println(service.getPerson(person.getNodeID()).getName() + " did not attend the same HighSchool as " + service.getPerson(p.getNodeID()).getName());
                        }
                    }
                }
            }
        }
        return messages;
    }


    /**
     * Users who went to the same department in the same college in the same school you are/were at
     * @param p
     * @return
     */
    public int detectDepartmentFriends(Person p, String location){

        //TODO: Dishank: implement me
        //TODO: Nariman: We have no possible way to get the data to do this. please delete
        return 0;
    }

    /**
     * Users who went to the same college at the same school where you study/studied
     * @param p
     * @return
     */
    public ArrayList<String> detectCollegeFriends(Person p, String location){
        Collection<Person> friends = p.getFriends(); /*Gets the list of friends of the selected user*/
        ArrayList<String> messages = new ArrayList<>();
        String findCity = null;

        for(Person person : friends) {
            Collection<Attribute> atts = service.getPerson(person.getNodeID()).getAttributes();
            for (Attribute att : atts) {
                Long aID = att.getNodeID();
                if (service.getAttributeWithId(aID).getLabel().contains("education")) {
                    /*String education = (service.getAttributeWithId(aID).getValue());*/
                    if ((service.getAttributeWithId(aID).getValue().contains("4-year degree")) || (service.getAttributeWithId(aID).getValue().contains("PHD")) || (service.getAttributeWithId(aID).getValue().contains("Masters"))){
                        findCity = "True";
                    } else {
                        findCity = "False";
                        System.out.println(service.getPerson(person.getNodeID()).getName() + " did not attend the same College as " + service.getPerson(p.getNodeID()).getName());
                    }
                }
            }

            if (findCity == "True") {
                Collection<Attribute> attrs = service.getPerson(person.getNodeID()).getAttributes();
                for (Attribute att : attrs) {
                    Long aID = att.getNodeID();
                    if (service.getAttributeWithId(aID).getLabel().contains("location")) {
                        String city = (service.getAttributeWithId(aID).getValue());
                        if (location.equals(city)) {
                            System.out.println(service.getPerson(person.getNodeID()).getName() + " attended the same College as " + service.getPerson(p.getNodeID()).getName());
                        } else {
                            System.out.println(service.getPerson(person.getNodeID()).getName() + " did not attend the same College as " + service.getPerson(p.getNodeID()).getName());
                        }
                    }
                }
            }
        }
            /*Long pID = person.getNodeID();
            Collection<Person> friendsOfFriends = service.getPerson(pID).getFriends();

            for(Person person1: friendsOfFriends){
                System.out.println("PERSON BLOCK");
                System.out.println(service.getPerson(person1.getNodeID()).getName());

                Collection<Attribute> atts = service.getPerson(person1.getNodeID()).getAttributes();
                for(Attribute att : atts) {
                    Long aID = att.getNodeID();
                    System.out.println("Attribute Block");
                    System.out.println(service.getAttribute("education", "4-year degree"));
                    System.out.println(service.getAttributeWithId(aID).getLabel());
                    System.out.println(service.getAttributeWithId(aID).getValue());
                }
            }*/
        return messages;
    }



    /**
     * Users who went to the same school to which you went
     * @param p
     * @return
     */
    public int detectSchoolFriends(Person p){

        //TODO: Dishank: implement me
        //TODO: Nariman: this is the same thing as detect HighSchool friends (We only have Highschool or masters/4yr/phd users) so please delete
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
        /*In progress*/
        return 0;
    }

    public int detectDepartmentFriendsPrivacyOutliers(Person p){
        /*TODO: No data. please delete*/
        return 0;
    }

    public int detectCollegeFriendsPrivacyOutliers(Person p){
        /*In progress*/
        return 0;
    }

    public int detectSchoolFriendsPrivacyOutliers(Person p) {
        /*TODO: Same as College or HighSchool. please delete*/
        return 0;
    }
}

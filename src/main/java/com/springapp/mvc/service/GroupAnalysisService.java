package com.springapp.mvc.service;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.HasRelationship;
import com.springapp.mvc.model.Person;
//import netkit.classifiers.relational.ClassDistribRelNeighbor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
        ArrayList<Person> highschoolFriends = new ArrayList<>();
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
                            highschoolFriends.add(person);
                        } else {
                            System.out.println(service.getPerson(person.getNodeID()).getName() + " did not attend the same HighSchool as " + service.getPerson(p.getNodeID()).getName());
                        }
                    }
                }
            }
        }

        for (Person highschoolFriend : highschoolFriends){
            messages.add(detectHighSchoolFriendsPrivacyOutliers(root, highschoolFriend));
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
        ArrayList<Person> collegeFriends = new ArrayList<>();
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
                            collegeFriends.add(person);
                        } else {
                            System.out.println(service.getPerson(person.getNodeID()).getName() + " did not attend the same College as " + service.getPerson(p.getNodeID()).getName());
                        }
                    }
                }
            }
        }

        for (Person collegeFriend : collegeFriends){
            messages.add(detectCollegeFriendsPrivacyOutliers(root, collegeFriend));
        }


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
    public String detectHighSchoolFriendsPrivacyOutliers(Person p, Person s){
        String msg = "";
        int threshold = 6;
        int counter = 0;
        int counter2 = 0;

        Long ppid = p.getNodeID();
        Long spid = s.getNodeID();
        Collection<HasRelationship> pAtts = service.getPerson(ppid).getAttributeRelationships();
        Collection<HasRelationship> sAtts = service.getPerson(spid).getAttributeRelationships();

        for (HasRelationship a : pAtts) {
            int total = 0;
            a = service.getHasRelationship(a.getId());
            /*System.out.println(service.getAttributeWithId(a.getEnd().getNodeID()).getLabel() + " " +
                    service.getAttributeWithId(a.getEnd().getNodeID()).getValue());
            System.out.println("pv" + a.getPv() + "sv" +a.getSv() + "vv" + a.getVv());*/
            total = ((a.getPv())  + (a.getSv()) + (a.getVv()));
            if (total >= 6){
                counter = counter + 1;
            }
        }

        for (HasRelationship b : sAtts) {
            int total2 = 0;
            b = service.getHasRelationship(b.getId());

            /*System.out.println(service.getAttributeWithId(b.getEnd().getNodeID()).getLabel() + " " +
                    service.getAttributeWithId(b.getEnd().getNodeID()).getValue());
            System.out.println("pv" + b.getPv() + "sv" + b.getSv() + "vv" + b.getVv());*/
            total2 = ((b.getPv()) + (b.getSv()) + (b.getVv()));
            if (total2 >=6){
                counter2 = counter2 + 1;
            }
        }

        System.out.println("detectHighSchoolFriendsPrivacyOutliers");
        if (counter < counter2){
            msg = service.getPerson(spid).getName() + " is an outlier.";
            System.out.println(service.getPerson(spid).getName() + " is an outlier.");
        }

        return msg;
    }

    public int detectDepartmentFriendsPrivacyOutliers(Person p){
        /*TODO: No data. please delete*/
        return 0;
    }

    public String detectCollegeFriendsPrivacyOutliers(Person p, Person s){
        String msg = "";
        int threshold = 6;
        int counter = 0;
        int counter2 = 0;

        Long ppid = p.getNodeID();
        Long spid = s.getNodeID();
        Collection<HasRelationship> pAtts = service.getPerson(ppid).getAttributeRelationships();
        Collection<HasRelationship> sAtts = service.getPerson(spid).getAttributeRelationships();

        for (HasRelationship a : pAtts) {
            int total = 0;
            a = service.getHasRelationship(a.getId());
            /*System.out.println(service.getAttributeWithId(a.getEnd().getNodeID()).getLabel() + " " +
                    service.getAttributeWithId(a.getEnd().getNodeID()).getValue());
            System.out.println("pv" + a.getPv() + "sv" +a.getSv() + "vv" + a.getVv());*/
            total = ((a.getPv())  + (a.getSv()) + (a.getVv()));
            if (total >= 6){
                counter = counter + 1;
            }
        }

        for (HasRelationship b : sAtts) {
            int total2 = 0;
            b = service.getHasRelationship(b.getId());

            /*System.out.println(service.getAttributeWithId(b.getEnd().getNodeID()).getLabel() + " " +
                    service.getAttributeWithId(b.getEnd().getNodeID()).getValue());
            System.out.println("pv" + b.getPv() + "sv" + b.getSv() + "vv" + b.getVv());*/
            total2 = ((b.getPv()) + (b.getSv()) + (b.getVv()));
            if (total2 >=6){
                counter2 = counter2 + 1;
            }
        }

        if (counter < counter2){
            msg = service.getPerson(spid).getName() + " is an outlier.";
        }

        return msg;
    }

    public int detectSchoolFriendsPrivacyOutliers(Person p) {
        /*TODO: Same as College or HighSchool. please delete*/
        return 0;
    }

    /**
     * group based attribute prediction
     * for an attribute dk in p's profile, predict the value of that attribute based on the distribution
     * of that attribute in a group g to which he belongs
     * 1. if p belongs to one group only
     *    use RelationalClassifiers in NetKit. Pick your favourate classifier!
     *    @see http://netkit-srl.sourceforge.net/current/docs/
     *    netkit.classifiers.relational.ClassDistribRelNeighbor
     *    Contact Sofus A. Macskassy (sofmac@gmail.com) for clarifications
     * 2. if p belongs to more than one group (difffernet distibutions!!)
     *    select  groups relevant to the classification task by taking the following group properties into account:
     *    density, size, homogenity, percentage of public profiles in it,
     *    smaller groups are more predictive than larger groups
     *    homogenious groups are more predictive
     *    dense groups are more predictive
     */
    HashMap<Attribute,Double> predictAttributeFromGroups(Person p,Group g, Attribute dk){
        //consider dk as the class label
        //You may need to map a neo4j Attribute to the corresponding netkit.graph.Attribute
        //mew Attribute(java.lang.String name, Type type)


        //More than one group case
        //1.get the set of relevent groups
        ArrayList<Group> relevantgroups=null;

        //determine group homogenity (0 highest homogenity, 1 lowest homogenity)
        //More homogenious groups are more predictive of an attribute
        // e.g. to predict dk=location, a group with 90% members from the same country
        //is your best predictor of the location class label
        double homogenity = 0;
        Double dkk[]= new Double[g.getMembers().size()];

        //calculate entropy of the group g based on attribute dk
        // homogenity = JavaMI.Entropy.calculateEntropy(dkk);
        //if(homogenious)
        //add to relevant groups

        //do the same for size and desnity etc.


        //2. traverse the relevent groups and extract their values for dk
        Double dkkrelevant[] = new Double[relevantgroups.size()];
        //train a classifier on dkkrelevant
        // get the class label for p's dk based on the trained classifier
        return null;
    }

    /**
     * For each of the above classification models assess their accuracy with respedt to the original
     * value of dk
     * @return
     */
    double calculateModelAccuracy(Person p, Attribute dk){
        //TODO: Dishank, Van based on the result of this method, if the accuracy is high, display a warning in both
        //group an relatioship what if analyses
        //WARNING: your membership in this groups predicts your attribute dk with high accuracy
        //WARNING: your relationship network predicts your attribute dk with high accuracy base on a cutoff or threshold
        return 0;
    }

    /**
     * relationship based attribute prediction
     * for an attribute dk in p's profile, predict the value of that attribute based on the distribution
     * of that attribute in the friends with which he has relations
     * Based on RelationalClassifiers in NetKit. Pick your favourate classifier!
     * @see http://netkit-srl.sourceforge.net/current/docs/
     *  netkit.classifiers.relational.ClassDistribRelNeighbor
     *  Contact Sofus A. Macskassy (sofmac@gmail.com) for clarifications
     */
    HashMap<Attribute,Double> predictAttributeFromRelations(Person p, Attribute dk){
        //consider dk as the class label
        //You may need to map a neo4j Attribute to the corresponding netkit.graph.Attribute
        //mew Attribute(java.lang.String name, Type type)


        //ClassDistribRelNeighbor classifier = new ClassDistribRelNeighbor();
        //Instances = new Instances ("")
        //dk=age -->class label
        //p=new node for which i want to predict a class label
        //based on set of nodes (your direct friends or relations

        return null;
    }

}

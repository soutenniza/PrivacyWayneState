package com.springapp.mvc.service;

import com.springapp.mvc.model.*;
import netkit.classifiers.relational.ClassDistribRelNeighbor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Van on 3/29/2015.
 */
@Service
@Transactional
public class RelationshipAnalysisService {
    @Autowired
    PersonService service;

    private Person root;

    @Autowired
    private GroupAnalysisService groupservice;

    @Autowired
    private PrivacyProfileAnalysisService profileservice;


    public RelationshipAnalysisService(){}

    public RelationshipAnalysisService(Person root){
        this.root = root;
    }

    /**
     * This entire class is based on the assumption that
     * RelationshipStrength = Sum (Norm(metric1), Norm(metric2), ..., Norm(metricn))
     * where metrics={#mutualFriends,#commongroups,socialDistance,duration,#interactions,geographicLocation}
     * and Norm is a value in [0,1]. We can get Norm is Avg(metrici) with respect to the number of elements in it's range.
     * TODO: How do we deal with boolean values? E.g. geographicalLocation,associationWithSameOrganization
     * TODO: make sure socioeconomicstatus, education level, political affiliation, race, gender , age, occupation, education,
     * political, religious views of individuals are all attributes in the database.
     */

    public double calculateRelationshipStrength(Person r, Person p){
        double RS = 0.0;
        Collection<FriendRelationship> friendRelationships = r.getFriendRelationships();
        Collection<Group> groupCollection = r.getGroups();
        ArrayList<Person> persons = new ArrayList<>();
        double maxIT = 0;
        for(FriendRelationship f : friendRelationships){
            Person pp = service.getPerson(f.getFriend().getNodeID());
            persons.add(pp);
            if(interactions(r,pp) > maxIT){
                maxIT = interactions(r,pp);
            }
        }

        ArrayList<Group> groups =  new ArrayList<>();
        groups.addAll(groupCollection);

        int mutualFriends = mutualfriends(r,p);
        int totalFriends = persons.size();
        double mutualFriendsRS =  (double) mutualFriends / (double) totalFriends;
        int commonGroups = groupservice.commonGroups(r,p);
        int totalGroups = groups.size();
        double commonGroupsRS = (double) commonGroups / (double) totalGroups;
        double IT = interactions(service.getPerson(r.getNodeID()), service.getPerson(p.getNodeID()));
        maxIT /= persons.size();
        double interactionsRS = IT/maxIT;

        double socialDistance = socialDistance(service.getPerson(r.getNodeID()), service.getPerson(p.getNodeID()));
        //Social distance is opposite of others, so it needs to be reversed
        RS = mutualFriendsRS + commonGroupsRS + interactionsRS + (5.0 - socialDistance)/5.0;

        return RS;
    }



    public double interactions(Person r, Person p){
        Collection<Comment> commentCollection = r.getComments();
        Collection<Comment> friendCollection = p.getComments();
        ArrayList<Long> rootComments = new ArrayList<>();
        ArrayList<Long> personLikes = new ArrayList<>();
        int totalComments = 0;

        for(Comment c : commentCollection){
            if(service.getComment(c.getNodeID()).isRoot()){
                rootComments.addAll(getReplies(c.getNodeID()));
            }
        }
        for(Comment c : friendCollection){
            personLikes.add(c.getNodeID());
        }
        totalComments = rootComments.size();
        rootComments.retainAll(personLikes);

        double commentRatio = (double) rootComments.size() / (double) totalComments;
        double ratio = commentRatio + getLikes(p,r);
        if(ratio > 9999)
            return 0.0;
        if(Double.isNaN(ratio))
            return 0.0;
        return ratio;
    }

    public ArrayList<Long> getReplies(Long nodeID){
        ArrayList<Long> replies = new ArrayList<>();
        replies.add(nodeID);

        while(service.getComment(nodeID).hasChildren()){
            Collection<Comment> childReplies = service.getComment(nodeID).getReplies();
            for(Comment c : childReplies){
                replies.addAll(getReplies(c.getNodeID()));
            }
            break;
        }

        return replies;
    }

    public double getLikes(Person r, Person p){
        Collection<Comment> commentCollection = r.getComments();
        Collection<Comment> likeCollections = p.getLikes();
        ArrayList<Long> rootComments = new ArrayList<>();
        ArrayList<Long> personLikes = new ArrayList<>();

        for(Comment c : commentCollection){
            rootComments.add(c.getNodeID());
        }
        for(Comment c : likeCollections){
            personLikes.add(c.getNodeID());
        }

        personLikes.retainAll(rootComments);
        double ratio = (double) personLikes.size() / (double) rootComments.size();
        if(ratio > 9999)
            return 0.0;
        //TODO: Zack: implement me
        return ratio;
    }

    public double geographicLocation(){

        double gd = 0.0;
        //Based on some preset values for locations this method measures the distance between locations.
        //Account for cities in the same state being still close, citities in different states being less close, cities outstide the US based on the contentent
        //being more distant.
        //1. Lookup table
        //2. GPS location
        //TODO: Nariman: find a dataset
        return gd;
    }

    public ArrayList<String> calculateRelationshipStrength(Person root){
        this.root = root;
        ArrayList<String> allMessages;
        allMessages = calculateAll();


        return allMessages;

    }
    private ArrayList<String> calculateAll(){
        Collection<FriendRelationship> friends = root.getFriendRelationships();
        ArrayList<Integer> privacyScores = new ArrayList<>();
        ArrayList<Integer> mutualfriends = new ArrayList<>();
        ArrayList<Integer> commonGroups = new ArrayList<>();
        ArrayList<Double> relationshipStrength = new ArrayList<>();
        ArrayList<Double> interactions = new ArrayList<>();
        ArrayList<Double> socialDistances = new ArrayList<>();
        ArrayList<Long> privacyScoresID  = new ArrayList<>();
        ArrayList<String> messaages = new ArrayList<>();
        for(FriendRelationship f : friends){
            Long pID = f.getFriend().getNodeID();
            int ps = profileservice.getPrivacyScore(service.getPerson(pID));
            int mt = mutualfriends(root, service.getPerson(pID));
            int mg = groupservice.mutualgroups(root, service.getPerson(pID));
            double rs = calculateRelationshipStrength(root, service.getPerson(pID));
            double it = interactions(root, service.getPerson(pID));
            double sd = socialDistance(root, service.getPerson(pID));
            mutualfriends.add(mt);
            commonGroups.add(mg);
            privacyScores.add(ps);
            relationshipStrength.add(rs);
            interactions.add(it);
            privacyScoresID.add(pID);
            socialDistances.add(sd);
            //Record Keeping
            service.addToMutualFriendsRecord(service.getFriendRelationship(f.getNodeID()), mt);
            service.addToCommonGroupRecord(service.getFriendRelationship(f.getNodeID()), mg);
            service.addToInteractionsRecord(service.getFriendRelationship(f.getNodeID()), it);
            service.addToSocialDistanceRecord(service.getFriendRelationship(f.getNodeID()), sd);
            service.addToRelationshipStrengthRecord(service.getFriendRelationship(f.getNodeID()), rs);
            //
        }

        double thresholdPS = 0;
        double thresholdMT = 0;
        double thresholdMG = 0;
        double thresholdRS = 0;
        double thresholdIT = 0;
        double thresholdSD = 0;

        for(int i = 0; i < privacyScores.size(); i++){
            thresholdPS += privacyScores.get(i);
            thresholdMT += mutualfriends.get(i);
            thresholdMG += commonGroups.get(i);
            thresholdRS += relationshipStrength.get(i);
            thresholdIT += interactions.get(i);
            thresholdSD += socialDistances.get(i);
        }

        double avgPS = thresholdPS / privacyScores.size();
        double avgMT = thresholdMT / privacyScores.size();
        double avgMG = thresholdMG / privacyScores.size();
        double avgRS = thresholdRS / privacyScores.size();
        double avgIT = thresholdIT / privacyScores.size();
        double avgSD = thresholdSD / privacyScores.size();
        thresholdPS = avgPS + avgPS/privacyScores.size();
        thresholdMT = avgMT - avgMT/privacyScores.size();
        thresholdMG = avgMG - avgMG/privacyScores.size();
        thresholdRS = avgRS - avgRS/privacyScores.size();
        thresholdIT = avgIT - avgIT/privacyScores.size();
        thresholdSD = avgSD + avgSD/privacyScores.size();

        for(int i = 0; i < socialDistances.size(); i++){
            if(socialDistances.get(i) > thresholdSD){
                String msg = String.format("%s has a high social distance. SCORE: %.2f  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(privacyScoresID.get(i)).getName(), socialDistances.get(i), avgSD, thresholdSD);
                messaages.add(msg);
            }
        }

        for(int i = 0; i < relationshipStrength.size(); i++){
            if(relationshipStrength.get(i) < thresholdRS){
                String msg = String.format("%s has a low relationship strength. SCORE: %.2f  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(privacyScoresID.get(i)).getName(), relationshipStrength.get(i), avgRS, thresholdRS);
                messaages.add(msg);
            }
        }

        for(int i = 0; i < interactions.size(); i++){
            if(interactions.get(i) < thresholdIT){
                String msg = String.format("%s has a low number of interactions. SCORE: %.2f  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(privacyScoresID.get(i)).getName(), interactions.get(i), avgIT, thresholdIT);
                messaages.add(msg);
            }
        }

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

        ArrayList<HasRelationship> attributes = new ArrayList<>();
        attributes.addAll(root.getAttributeRelationships());
        int total = service.getAllPersons().size();
        for(HasRelationship a : attributes){
            profileservice.getAttributeExposure(root, service.getHasRelationship(a.getId()), total);
        }

        return messaages;
    }

    public double getThresholdRS(Person r){
        Collection<Person> friends = r.getFriends();
        ArrayList<Double> relationshipstrengths = new ArrayList<>();
        for(Person p : friends){
            Long pID = p.getNodeID();
            double rs = calculateRelationshipStrength(r, service.getPerson(pID));
            relationshipstrengths.add(rs);
        }

        double threshold = 0;

        for(int i = 0; i < relationshipstrengths.size(); i++){
            threshold += relationshipstrengths.get(i);
        }

        threshold /= relationshipstrengths.size();
        threshold = threshold - threshold/relationshipstrengths.size();
        return threshold;
    }

    public ArrayList<String> calculateSingleFriend(Person root, Person friend){
        this.root = root;
        Collection<Person> friends = root.getFriends();
        ArrayList<Integer> mutualFriends = new ArrayList<>();
        ArrayList<Long> mutualFriendID  = new ArrayList<>();
        ArrayList<Double> relationshipStrength = new ArrayList<>();
        ArrayList<String> messaages = new ArrayList<>();
        for(Person p : friends){
            Long pID = p.getNodeID();
            int mt = mutualfriends(root, service.getPerson(pID));
            double rs = calculateRelationshipStrength(root, service.getPerson(pID));
            mutualFriends.add(mt);
            mutualFriendID.add(pID);
            relationshipStrength.add(rs);
        }
        double thresholdMT = 0;
        double thresholdRS = 0;



        for(int i = 0; i < mutualFriends.size(); i++){
            thresholdMT += mutualFriends.get(i);
        }

        for(int i = 0; i < relationshipStrength.size(); i++){
            thresholdRS += relationshipStrength.get(i);
        }

        thresholdMT /= mutualFriends.size();
        double avgMT = thresholdMT;
        thresholdMT = thresholdMT - thresholdMT/mutualFriends.size();

        thresholdRS /= relationshipStrength.size();
        double avgRS = thresholdRS;
        thresholdRS = thresholdRS - thresholdRS/relationshipStrength.size();

        int mt = mutualfriends(root, service.getPerson(friend.getNodeID()));
        double rs = calculateRelationshipStrength(root, service.getPerson(friend.getNodeID()));


        if(rs < thresholdRS){
            String msg = String.format("%s has a low number of relationship strength. SCORE: %.2f  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(friend.getNodeID()).getName(), rs, avgRS, thresholdRS);
            messaages.add(msg);
        }

        if(mt < thresholdMT) {
            String msg = String.format("%s has a low number of mutual friends. SCORE: %d  AVERAGE: %.2f THRESHOLD: %.2f", service.getPerson(friend.getNodeID()).getName(), mt, avgMT, thresholdMT);
            messaages.add(msg);
        }
        return messaages;

    }

    public int mutualfriends(Person r, Person p){
        Collection<Person> rootFriends = r.getFriends();
        ArrayList<Long> rootLong = new ArrayList<>();
        //Get root's friends
        for(Person person : rootFriends){
            if(person.getNodeID() != r.getNodeID()){
                rootLong.add(person.getNodeID());
            }
        }
        Collection<Person> pFriends = p.getFriends();
        ArrayList<Long> pLong = new ArrayList<>();
        //Get Person's Friends
        for(Person person : pFriends){
            if(person.getNodeID() != p.getNodeID()){
                pLong.add(person.getNodeID());
            }

        }

        //Use intersection to find same people
        rootLong.retainAll(pLong);

        return rootLong.size();
    }



    /**
     * Social distance indicated by socioeconomic status, education level,
     * political affiliation, race, gender or parity in age, occupation
     * education political religious views of individuals
     * @param r
     * @param p
     * @return
     */
    public double socialDistance(Person r, Person p){
        //Getting all the values and variables
        Collection<HasRelationship> rootAttributes = service.getPerson(r.getNodeID()).getAttributeRelationships();
        Collection<HasRelationship> personAttributes = service.getPerson(p.getNodeID()).getAttributeRelationships();
        int rAge = 0;
        int pAge = 0;
        String rGender = "";
        String pGender = "";
        String rEducation = "";
        String pEducation = "";
        String rPolitical = "";
        String pPolitical = "";
        String rWork = "";
        String pWork = "";

        //Get values from attributes to put into variables
        for(HasRelationship h : rootAttributes){
            Attribute a = service.getAttributeWithId(h.getEnd().getNodeID());
            if(a.getLabel().equals("age")){
                rAge = Integer.valueOf(a.getValue());
            }
            if(a.getLabel().equals("gender")){
                rGender = a.getValue();
            }
            if(a.getLabel().equals("education")){
                rEducation = a.getValue();
            }
            if(a.getLabel().equals("political view")){
                rPolitical = a.getValue();
            }
            if(a.getLabel().equals("work")){
                rWork = a.getValue();
            }
        }

        for(HasRelationship h : personAttributes){
            Attribute a = service.getAttributeWithId(h.getEnd().getNodeID());
            if(a.getLabel().equals("age")) {
                pAge = Integer.valueOf(a.getValue());
            }
            if(a.getLabel().equals("gender")){
                pGender = a.getValue();
            }
            if(a.getLabel().equals("education")){
                pEducation = a.getValue();
            }
            if(a.getLabel().equals("political view")){
                pPolitical = a.getValue();
            }
            if(a.getLabel().equals("work")){
                pWork = a.getValue();
            }
        }


        //Call calculation functions
        double socialdistance = 0.0;
        double politicalAffiliationDistance = calculatePoliticalDistance(rPolitical,pPolitical);
        double ethnicDistance;
        double gender = calculateGenderRatio(rGender, pGender);
        double educationLevelDistance = calculateEducationDistance(rEducation, pEducation);
        double occupationDistance = calculateOccuptionDistance(rWork, pWork);
        double ageParity = calculateAgeParity(rAge, pAge);
        socialdistance = ageParity + gender + educationLevelDistance + politicalAffiliationDistance + occupationDistance;

        return socialdistance;

    }

    public double calculateOccuptionDistance(String r, String p){
        double rWork = workToDouble(r);
        double pWork = workToDouble(p);
        double ratio = 0.0;

        if(rWork > pWork){
            ratio = rWork - pWork;
        }else{
            ratio = pWork - rWork;
        }

        ratio = 3.0 - ratio;
        ratio = ratio / 3.0;

        return 1.0 - ratio;
    }

    public double workToDouble(String e){
        if(e.equalsIgnoreCase("Unemployed")
                || e.equalsIgnoreCase("Charity and Voluntary")
                || e.equalsIgnoreCase("Leisure, Sport and Tourism")
                || e.equalsIgnoreCase("Retail")
                || e.equalsIgnoreCase("Sales")
                || e.equalsIgnoreCase("Transport and Logistics")){
            return 1.0;
        }

        if(e.equalsIgnoreCase("Environment and Agriculture")
                || e.equalsIgnoreCase("Hospitality")
                || e.equalsIgnoreCase("Media and Internet")
                || e.equalsIgnoreCase("Law Enforcement and Security")
                || e.equalsIgnoreCase("Creative Arts And Design")
                || e.equalsIgnoreCase("Information Technology")
                || e.equalsIgnoreCase("Property and Security")
                || e.equalsIgnoreCase("Recruitment and HR")
                || e.equalsIgnoreCase("Social Care")
                || e.equalsIgnoreCase("Public Services and Admin")){
            return 2.0;
        }


        if(e.equalsIgnoreCase("Law")
                || e.equalsIgnoreCase("Energy and Utilities")
                || e.equalsIgnoreCase("Accountancy, Banking and Finance")
                || e.equalsIgnoreCase("Marketing, Advertising and PR")
                || e.equalsIgnoreCase("Business, Consulting and Management")){
            return 3.0;
        }

        if(e.equalsIgnoreCase("Science and Pharmaceuticals")
                || e.equalsIgnoreCase("Teaching and Education")
                || e.equalsIgnoreCase("Engineering and Manufacturing")
                || e.equalsIgnoreCase("Healthcare")
                || e.equalsIgnoreCase("Business, Consulting and Management")){
            return 4.0;
        }

        return 1.0;
    }

    public double calculatePoliticalDistance(String r, String p){
        double rValue = politicalToDouble(r);
        double pValue = politicalToDouble(p);

        double ratio = 0.0;

        //Gets ratio from smaller divided by larger value;
        if(rValue > pValue){
            ratio = rValue - pValue;
        }else{
            ratio = pValue - rValue;
        }

        ratio = 8.0 - ratio;
        ratio /= 8.0;
        return 1 - ratio;
    }

    public double politicalToDouble(String e){
        if(e.equalsIgnoreCase("Extreme right")){
            return 1.0;
        }
        if(e.equalsIgnoreCase("Far right")){
            return 2.0;
        }
        if(e.equalsIgnoreCase("Right")){
            return 3.0;
        }
        if(e.equalsIgnoreCase("Center right")){
            return 4.0;
        }
        if(e.equalsIgnoreCase("Center")){
            return 5.0;
        }
        if(e.equalsIgnoreCase("Center Left")){
            return 6.0;
        }
        if(e.equalsIgnoreCase("Left")){
            return 7.0;
        }if(e.equalsIgnoreCase("Far Left")){
            return 8.0;
        }if(e.equalsIgnoreCase("Extreme Left")){
            return 9.0;
        }
        return 1.0;
    }

    public double calculateEducationDistance(String r, String p){
        double rValue = educationToDouble(r);
        double pValue = educationToDouble(p);
        double ratio = 0.0;

        //Gets ratio from 6 - difference +
        if(rValue > pValue){
            ratio = rValue - pValue;
        }else{
            ratio = pValue - rValue;
        }

        ratio = 5.0 - ratio;
        ratio /= 5.0;
        return 1.0 - ratio;

    }

    public double educationToDouble(String e){
        //This changes string to double so calculations can be done.
        if(e.equals("None")){
            return 1.0;
        }
        if(e.equals("GED")){
            return 2.0;
        }
        if(e.equals("Associates")){
            return 3.0;
        }
        if(e.equals("4-year Degree")){
            return 4.0;
        }
        if(e.equals("Masters")){
            return 5.0;
        }
        if(e.equals("PHD")){
            return 6.0;
        }
        return 1.0;

    }

    public double calculateGenderRatio(String r, String p){
        //If gender is same then ratio is 1.0
        if(r.equals(p)){
            return 0.0;
        }else {
            return 1.0;
        }
    }

    public double calculateAgeParity(int r, int p){
        double ratio = 0.0;
        //Formula for age parity is highest age - difference / higher age
        if(r > p){
            ratio = (double) r - (double) p;
            ratio = (double) r - ratio;
            ratio = ratio / (double) r;
        }else{
            ratio = (double) p - (double) r;
            ratio = (double) p - ratio;
            ratio = ratio / (double) p;
        }

        return 1.0 - ratio;

    }

    public double Duration(Person r, Person p){
        double d =0.0;
        //TODO: Nariman: find a way to import timestamps
        return d;
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


        ClassDistribRelNeighbor classifier = new ClassDistribRelNeighbor();
        //Instances = new Instances ("")
        //dk=age -->class label
        //p=new node for which i want to predict a class label
        //based on set of nodes (your direct friends or relations

        return null;
    }

}

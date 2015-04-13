package com.springapp.mvc.service;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Group;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springapp.mvc.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import netkit.classifiers.relational.*;

/**
 * Created by narimanammar on 4/6/15.
 */
@Service
@Transactional
public class OtherAnalyses {

    private static final String JSON_PATH = "http://zackrzot.com/data.json";

    HashMap<String,Double> generateDirectRelationAttributeExposureMap(Person p){

        //TODO: get attributes from p
        //TODO: for each attribute calculate the exposure

        return null;
    }

    HashMap<String,Double> generateTransitiveRelationAttributeExposureMap(Person p){

        //TODO: get attributes from p
        //TODO: for each attribute calculate the exposure

        return null;
    }


    HashMap<String,Double> generateDynamicSensitivityeByConditionalPrivacyMap(Person p){

        //TODO: get attributes from p
        //TODO: for each attribute calculate ..

        return null;
    }

    HashMap<String,Double> generateDynamicSensitivityeByLevelMap(Person p){

        //TODO: get attributes from p
        //TODO: for each attribute calculate ..

        return null;
    }

    HashMap<String,Double> generateDynamicVisibilityByPropagationMap(Person p){

        //TODO: get attributes from p
        //TODO: for each attribute calculate ..

        return null;
    }

    /**
     * Use a Google Service with a well crafted query from the user's profile
     * @param p
     */
    HashMap<String,Double> detectFriendsUnderSameAdvisor(Person p){

        String query ="nariman ammar wayne state score lab";
        //googleservice.search(query);
        //takes me to this page
        //People-SCORElab: http://score.cs.wayne.edu/People.html


        return null;
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


        ClassDistribRelNeighbor classifier = new ClassDistribRelNeighbor();
        //Instances = new Instances ("")
        //dk=age -->class label
        //p=new node for which i want to predict a class label
        //based on set of nodes (your direct friends or relations

        return null;
    }




}

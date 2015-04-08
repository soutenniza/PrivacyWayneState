package com.springapp.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springapp.mvc.model.Person;

import java.util.HashMap;

/**
 * Created by narimanammar on 4/6/15.
 */
@Service
@Transactional
public class OtherAnalyses {
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




}

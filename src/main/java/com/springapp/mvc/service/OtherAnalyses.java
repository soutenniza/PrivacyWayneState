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

    
}

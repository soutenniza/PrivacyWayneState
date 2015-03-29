package com.springapp.mvc.analysis;

import com.springapp.mvc.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Van on 3/29/2015.
 */
@Component
public class RelationshipAnalysisHandler {
    private Person root;
    private ArrayList<Person> people;

    public RelationshipAnalysisHandler(){}

    public RelationshipAnalysisHandler(Person root){
        this.root = root;
        people = new ArrayList<Person>();
        people.addAll(root.getFriends());
    }

    public void calculateRelationshipStrength(){
        for(int i = 0; i < people.size(); i++){
            System.out.println(people.get(i).getName());
        }

    }
}

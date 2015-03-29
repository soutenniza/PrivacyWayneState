package com.springapp.mvc.analysis;

import com.springapp.mvc.model.Person;

import java.util.ArrayList;

/**
 * Created by Van on 3/29/2015.
 */
public class MutualFriendsAnalysisHandler {
    private Person root;
    private ArrayList<Person> people;

    public MutualFriendsAnalysisHandler(Person root, ArrayList<Person> people){
        this.root = root;
        this.people = people;
    }
}

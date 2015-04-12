package com.springapp.mvc.model;

/**
 * Created by Zack on 4/12/15.
 */
public class AttData {

    private Long attID;
    private String dataName;
    private double datVal;

    AttData(Long id, String name, double val){
        this.attID = id;
        this.dataName = name;
        this.datVal = val;
    }

    public Long getAttID(){ return this.attID; }
    public String getDataName(){ return this.dataName; }
    public double getDatVal(){ return this.datVal; }

}

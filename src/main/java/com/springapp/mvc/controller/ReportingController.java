package com.springapp.mvc.controller;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.HasRelationship;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.AnalysisService;
import com.springapp.mvc.service.ContentAnalysisService;
import com.springapp.mvc.service.PersonService;
import com.springapp.mvc.service.PrivacyProfileAnalysisService;
import org.neo4j.cypher.internal.compiler.v1_9.commands.Has;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vannguyen on 3/11/15.
 */
@Controller
public class ReportingController {

    @Autowired
    PersonService personService;

    @Autowired
    ContentAnalysisService contentAnalysis;

    @Autowired
    PrivacyProfileAnalysisService profileAnalysisService;

    @RequestMapping(value = "/reporting", method = RequestMethod.GET)
    public String displayAnalysisPage(Model model){
        Person person = new Person();
        model.addAttribute("inputPerson", person);
        initDropDown(model);
        return "reporting";
    }

    @RequestMapping(value = "/submitreport", method = RequestMethod.POST)
    @Transactional
    public String addFriend(@RequestParam(value = "inputPerson") Long p1,
                            Model models, final RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("ran", "loaded");
        redirectAttributes.addFlashAttribute("user", "<i>Privacy report for the user " + personService.getPerson(p1).getName() + ":</i>");


        System.out.println(personService.getLatestPrivacyScore(p1));

        int pscore = personService.getLatestPrivacyScore(p1);


        if(pscore == 0){
            redirectAttributes.addFlashAttribute("pscore", "Privacy score has not been calculated for this user as of yet.");
        }
        else{
            redirectAttributes.addFlashAttribute("pscore", "Privacy score: " + pscore);
            redirectAttributes.addFlashAttribute("psdata", genPSDataLine("Privacy Score", p1));
        }

        // construct attribute network visibility table
        String netvisStr = getNetVis(p1);
        redirectAttributes.addFlashAttribute("netvis", netvisStr);

        // add attribute visibility over time chart
        redirectAttributes.addFlashAttribute("asdata", genASDataLine(p1));

        // incoming vs outgoing communications
        redirectAttributes.addFlashAttribute("cfdata", genCFData(p1));


        return "redirect:/reporting";
    }

    protected void initDropDown(Model model){
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = personService.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
    }

    public String genPSDataLine(String name, Long pid){
        String msg = "['"+name+"', ";
        ArrayList<Integer> vals = personService.getPrivacyScoreRecord(pid);
        if(vals.size()==1){
            return "['No History']";
        }
        else{
            for(int x : vals){
                msg = msg + Integer.toString(x) + ", ";
            }
        }
        msg = msg + "],";
        return msg;
    }

    public String genASDataLine(Long pid){
        Person p = personService.getPerson(pid);
        Collection<HasRelationship> rels = p.getAttributeRelationships();
        String msg= "";
        for(HasRelationship h : rels){
            h = personService.getHasRelationship(h.getId());
            msg = msg + "['"+personService.getAttributeWithId(h.getEnd().getNodeID()).getLabel()+": "+  personService.getAttributeWithId(h.getEnd().getNodeID()).getValue()+ "', ";
            ArrayList<Double> vals = h.getAttVisibilityRecord();
            if(vals.size()==1){
                return "['No History']";
            }
            else{
                for(double x : vals){
                    msg = msg + Double.toString(x) + ", ";
                }
            }
            msg = msg + "],";
        }
        return msg;
    }

    public String getNetVis(Long pid){
        String html = "";
        Collection<HasRelationship> atts = personService.getPerson(pid).getAttributeRelationships();

        // add the values to the following loop when they become available

        int totalsize = personService.getAllPersons().size();

        for(HasRelationship a : atts){
            ArrayList<Double> attData = personService.getAttVisRecord(personService.getHasRelationship(a.getId()));
            html = html + "<tr>";
            // ATTRIBUTE NAME
            html = html + "<td>" + personService.getAttributeWithId(a.getEnd().getNodeID()).getLabel() +
                    ": "
                    + personService.getAttributeWithId(a.getEnd().getNodeID()).getValue() + "</td>";
            // PV
            html = html + "<td>" + personService.getHasRelationship(a.getId()).getPv() + "</td>";
            // VV
            html = html + "<td>" + personService.getHasRelationship(a.getId()).getVv() + "</td>";
            // SV
            html = html + "<td>" + personService.getHasRelationship(a.getId()).getSv() + "</td>";
            // NETWORK VISIBILITY
            double percent = attData.get(attData.size() - 1)* 100.0;
            String vis = String.format("%.2f%%", percent );

            html = html + "<td>" + vis + "</td>";
            // TARGET
            html = html + "<td>" + "<20%" + "</td>";
            // CHANGE
            html = html + "<td>" + genChangeStr(attData) + "</td>";

            html = html + "</tr>";
        }

        return html;
    }

    public String genCFData(Long pid){
        String msg = "";
        String nomsg = "<center>";
        int counter = 0;
        String id;
        Collection<Person> persons = personService.getAllPersons();
        for(Person person : persons){
            if(!(person.getNodeID().equals(pid))) {
                person = personService.getPerson(person.getNodeID());
                double outgoing = contentAnalysis.getRootOutgoingVal(pid, person.getNodeID());
                System.out.println("outval:" + outgoing);
                id = "cfid" + Integer.toString(counter);
                counter = counter + 1;
                String friendStr = "";
                if(personService.areFriends(person, personService.getPerson(pid))){
                    friendStr = "[FRIEND]";
                }
                else{
                    friendStr = "[NOT A FRIEND]";
                }
                if (Double.isNaN(outgoing)) {
                    nomsg = nomsg + "No interactions have been made with the person " + person.getName() + " as of yet! "+friendStr+"<br>";
                } else {
                    msg = msg + "<div id=\"chart" + id + "\"></div>" +
                            "<script language=\"JavaScript\">" +
                            "var chart" + id + " = c3.generate({\n" +
                            "bindto: '#chart" + id + "'," +
                            "    data: {\n" +
                            "        selection: {" +
                            "           enabled: true" +
                            "        }," +
                            "        columns: [\n" +
                            "            ['Outgoing', " + Double.toString(outgoing) + "],\n" +
                            "            ['Incoming', " + Double.toString(1.0 - outgoing) + "],\n" +
                            "        ],\n" +
                            "        type : 'donut',\n" +
                            "        onclick: function (d, i) { console.log(\"onclick\", d, i); },\n" +
                            "        onmouseover: function (d, i) { console.log(\"onmouseover\", d, i); },\n" +
                            "        onmouseout: function (d, i) { console.log(\"onmouseout\", d, i); }\n" +
                            "    },\n" +
                            "    donut: {\n" +
                            "        title: \"" + person.getName() + "\"\n" +
                            "    }\n" +
                            "});\n" +
                            "var label = d3.select('#chart" + id + "').select('text.c3-chart-arcs-title');\n" +
                            "label.html('');\n" +
                            "label.insert('tspan').text('" + person.getName() + "').attr('dy',0).attr('x', 0).attr('class','big-font');\n" +
                            "label.insert('tspan').text('" + friendStr + "').attr('dy',20).attr('x', 0).attr('class','small-font');\n" +
                            "</script>";
                }
            }
        }

        msg = nomsg + msg + "</center>";
        return msg;
    }

    public String genChangeStr(ArrayList<Double> vals){
        String msg = "";
        if(vals.size()!=1){
            Double val1 = vals.get(vals.size()-1);
            Double val2 = vals.get(vals.size()-2);
            if(val1.equals(val2)){
                msg = "0%";
            }
            else {
                // positive growth
                if (val1 > val2) {
                    Double growth = val2 / val1;
                    String valnum = Double.toString((growth*100));
                    if(valnum.length()>6) {
                        valnum = "+" + Double.toString((growth*100)).substring(0, 5) + "%";
                    }
                    msg = "+"+valnum+"%";
                } else {
                    Double growth = val1 / val2;
                    String valnum = Double.toString((growth*100));
                    if(valnum.length()>6){
                        valnum = Double.toString((growth*100)).substring(0,5);
                    }
                    msg = "-"+valnum+"%";
                }
            }
        }
        else{
            msg = msg + "0%";
        }

        return msg;
    }



}

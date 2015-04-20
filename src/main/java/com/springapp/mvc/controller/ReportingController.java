package com.springapp.mvc.controller;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.FriendRelationship;
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
        redirectAttributes.addFlashAttribute("user2", "<i>How Privacy Score is calculated for " + personService.getPerson(p1).getName() + ":</i>");


        System.out.println(personService.getLatestPrivacyScore(p1));

        int pscore = personService.getLatestPrivacyScore(p1);


        if(pscore == 0){
            redirectAttributes.addFlashAttribute("pscore", "Privacy score has not been calculated for this user as of yet.");
        }
        else{
            redirectAttributes.addFlashAttribute("pscore", "Privacy score: " + pscore);
            redirectAttributes.addFlashAttribute("psdata", genPSDataLine("Privacy Score", p1));
            redirectAttributes.addFlashAttribute("pspdata", genPSPieData(p1));
        }

        // construct attribute network visibility table
        String netvisStr = getNetVis(p1);
        redirectAttributes.addFlashAttribute("netvis", netvisStr);

        // add attribute visibility over time chart
        redirectAttributes.addFlashAttribute("asdata", genASDataLine(p1));

        // incoming vs outgoing communications
        redirectAttributes.addFlashAttribute("cfdata", genCFData(p1));

        redirectAttributes.addFlashAttribute("fddata", genFriendDataLine(p1));



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

    public String genPSPieData(Long pid){
        String msg = "";
        for(HasRelationship r : personService.getPerson(pid).getAttributeRelationships()){
            HasRelationship h = personService.getHasRelationship(r.getId());
            Attribute a = personService.getAttributeWithId(h.getEnd().getNodeID());
            int v = h.getVv();
            int s = h.getSv();
            String name = a.getLabel() + " : Visibility(" + Integer.toString(v) + ")*Sensivitity(" + Integer.toString(s) +")";
            msg = msg + "['" + name + "', " + Integer.toString(v*s) + "],";
        }
        return msg;
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
        int one = profileAnalysisService.getNumberOfPeople(personService.getPerson(pid), 1);
        int two = profileAnalysisService.getNumberOfPeople(personService.getPerson(pid), 2);
        int three = profileAnalysisService.getNumberOfPeople(personService.getPerson(pid), 3);

        for(HasRelationship a : atts){
            double target = 0.0;
            switch (a.getPv()){
                case 0:
                    target = 0.0;
                    break;
                case 1:
                    target = (double) one / (double) totalsize;
                    break;
                case 2:
                    target = (double) two / (double) totalsize;
                    break;
                case 3:
                    target = (double) three / (double) totalsize;
                    break;
                default:
                    target = 1.00;
            }
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
            ArrayList<Double> data = a.getAttVisibilityRecord();
            double percent = data.get(data.size() - 1) * 100.0;
            String vis = String.format("%.2f%%", percent );

            html = html + "<td>" + vis + "</td>";
            // TARGET
            html = html + "<td>" + String.format("%.2f", target * 100) + "%</td>";
            // CHANGE
            html = html + "<td>" + genChangeStr(personService.getHasRelationship(a.getId()).getAttVisibilityRecord()) + "</td>";

            html = html + "</tr>";
        }

        return html;
    }

    public String genCFData(Long pid){
        String msg = personService.getCommunicationCharts(personService.getPerson(pid).getNodeID());
        if(msg.equals("")){
            return "No data generated. Run analysis for this user and try again.";
        }
        return msg;
    }

    public String genFriendDataLine(Long pid){
        String fdData = "";
        String fdPieData = "";
        String msg = "<center>";
        Person p = personService.getPerson(pid);
        int count = 0;
        String id;
        Collection<FriendRelationship> friends = p.getFriendRelationships();
        for(FriendRelationship f : friends){
            Person person = personService.getPerson(f.getFriend().getNodeID());
            id = "fdid" + Integer.toString(count);
            count++;

            fdData = genFDData(f);
            fdPieData = genFDPieData(f);
            msg = msg + "<h3>" + person.getName() + "</h3>\n";
            msg = msg + "<div id=\"chart" + id + "\"></div>\n" +
                    "<script language=\"JavaScript\">\n" +
                    "var chart = c3.generate({\n" +
                    "bindto: '#chart" + id + "',\n" +
                    "data: {\n" +
                    "   columns: [" + fdData + "]\n" +
                    "}\n" +
                    "});" +
                    "</script><br>";
            msg = msg + "<h5>How your relationship strength is calculated with " + person.getName() + "</h5>";
            msg = msg + "<div id=\"charts" + id + "\"></div>\n" +
                    "<script language=\"JavaScript\">\n" +
                    "var chart = c3.generate({\n" +
                    "bindto: '#charts" + id + "',\n" +
                    "data: {\n" +
                    "   columns: [" + fdPieData + "],\n" +
                    "   type: 'pie',\n" +
                    "}\n" +
                    "});" +
                    "</script><br>";
        }


        return msg + "</center>";
    }

    public String genFDPieData(FriendRelationship f){
        String data = "";
        FriendRelationship r = personService.getFriendRelationship(f.getNodeID());
        ArrayList<Integer> mt = r.getMutualFriendRecord();
        ArrayList<Integer> cg = r.getCommonGroupRecord();
        ArrayList<Double> it = r.getInteractionsRecord();
        ArrayList<Double> rs = r.getRelationshipStrengthRecord();
        ArrayList<Double> sd = r.getSocialDistanceRecord();
        Person person = personService.getPersonFromFriendRelationship(personService.getFriendRelationship(f.getNodeID()));
        person = personService.getPerson(person.getNodeID());
        int countFriends = person.getFriends().size();
        double mtRatio = mt.get(mt.size() - 1) / (double) countFriends;

        int countGroups = person.getGroups().size();
        double cgRatio = cg.get(cg.size() - 1) / (double) countGroups;

        data = "['Mutual Friends', " + String.format("%.2f", mtRatio) + "], ";
        data = data + "['Common Groups', " + String.format("%.2f", cgRatio) + "], ";
        data = data + "['Interactions', " + String.format("%.2f", it.get(it.size() - 1)) + "], ";
        double sdRatio = (5.0 - sd.get(sd.size() - 1)) / 5.0;
        data = data + "['Social Distance', " + String.format("%.2f", sdRatio) + "], ";
        return data;
    }

    public String genFDData(FriendRelationship f){
        String data = "";
        FriendRelationship r = personService.getFriendRelationship(f.getNodeID());
        ArrayList<Integer> mt = r.getMutualFriendRecord();
        ArrayList<Integer> cg = r.getCommonGroupRecord();
        ArrayList<Double> it = r.getInteractionsRecord();
        ArrayList<Double> rs = r.getRelationshipStrengthRecord();
        ArrayList<Double> sd = r.getSocialDistanceRecord();
        data = "['Mutual Friends', ";
        for(int i = 0; i < mt.size(); i++){
            data = data + String.format("%d", mt.get(i)) + ", ";
        }
        data = data + "], ";
        data = data + "['Common Groups', ";
        for(int i = 0; i < cg.size(); i++){
            data = data + String.format("%d", cg.get(i)) + ", ";
        }
        data = data + "], ";
        data = data + "['Interactions', ";
        for(int i = 0; i < it.size(); i++){
            data = data + String.format("%.2f", it.get(i)) + ", ";
        }
        data = data + "], ";
        data = data + "['Social Distance', ";
        for(int i = 0; i < sd.size(); i++){
            data = data + String.format("%.2f", sd.get(i)) + ", ";
        }
        data = data + "], ";
        data = data + "['Relationship Strength', ";
        for(int i = 0; i < rs.size(); i++){
            data = data + String.format("%.2f", rs.get(i)) + ", ";
        }
        data = data + "] ";
        return data;
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
                if(val1 == 0){
                    return "100%";
                }
                // positive growth
                if (val1 > val2) {
                    Double growth = val1 - val2;
                    growth = growth / val2;
                    msg = "+"+ String.format("%.2f", growth * 100)+"%";
                } else {
                    Double growth = val2 - val1;
                    growth = growth / val2;
                    msg = "-"+String.format("%.2f", growth * 100)+"%";
                }
            }
        }
        else{
            msg = msg + "0%";
        }

        return msg;
    }



}

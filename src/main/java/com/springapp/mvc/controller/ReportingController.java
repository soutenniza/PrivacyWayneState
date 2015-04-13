package com.springapp.mvc.controller;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.HasRelationship;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.AnalysisService;
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

    public String getNetVis(Long pid){
        String html = "";
        Collection<HasRelationship> atts = personService.getPerson(pid).getAttributeRelationships();

        // add the values to the following loop when they become available

        int totalsize = personService.getAllPersons().size();

        for(HasRelationship a : atts){
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
            double percent = profileAnalysisService.getAttributeExposure(personService.getPerson(pid), personService.getHasRelationship(a.getId()), totalsize) * 100.0;
            String vis = String.format("%.2f%%", percent );

            html = html + "<td>" + vis + "</td>";
            // TARGET
            html = html + "<td>" + "<20%" + "</td>";
            // CHANGE
            html = html + "<td>" + "+2%" + "</td>";

            html = html + "</tr>";
        }

        return html;
    }

}

package com.springapp.mvc.controller;

import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.AnalysisService;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vannguyen on 3/11/15.
 */
@Controller
public class AnalysisController {
    @Autowired
    PersonService personService;

    @Autowired
    AnalysisService analysisService;

    @RequestMapping(value = "/analysis", method = RequestMethod.GET)
    public String displayAnalysisPage(Model model){
        Person person1 = new Person();
        model.addAttribute("inputPerson1", person1);
        initDropDown(model);
        return "analysis";
    }

    @RequestMapping(value = "/submitanalysis", method = RequestMethod.POST)
    @Transactional
    public String addFriend(@RequestParam(value = "inputPerson1") Long p1,
                            Model models, final RedirectAttributes redirectAttributes){

        analysisService.setRoot(personService.getPerson(p1));
        ArrayList<String> messages = analysisService.fullAnalysis();

        redirectAttributes.addFlashAttribute("ran", "loaded");

        String relationshipStrengthMsgs = "";
        String relationshipMsgs = "";
        String psMsgs = "";
        String mgpsMsgs = "";
        String ascMsgs = "";
        String sentMsgs = "";
        String contentMsgs = "";

        redirectAttributes.addFlashAttribute("user", "<i>Privacy analysis for the user "+personService.getPerson(p1).getName()+":</i>");

        // sort messages
        for(String m : messages){

            if(m.contains("relationship")){
                relationshipStrengthMsgs = relationshipStrengthMsgs + "<div id=\"message\" class=\"alert alert-warning\"> <b>[WARN]     " + m +"</b></div>";
            }
            if(m.contains("mutual friends")){
                relationshipMsgs = relationshipMsgs + "<div id=\"message\" class=\"alert alert-warning\"> <b>[WARN]     " + m +"</b></div>";
            }

            if(m.contains("Privacy Score")){
                psMsgs = psMsgs + "<div id=\"message\" class=\"alert alert-warning\"> <b>[WARN]     " + m +"</b></div>";
            }
            if(m.contains("mutual groups")){
                mgpsMsgs = mgpsMsgs + "<div id=\"message\" class=\"alert alert-warning\"> <b>[WARN]     " + m +"</b></div>";
            }
            if(m.contains("association")){
                ascMsgs = ascMsgs + "<div id=\"message\" class=\"alert alert-warning\"> <b>[WARN]     " + m +"</b></div>";
            }
            if(m.contains("negative comments")){
                sentMsgs = sentMsgs + "<div id=\"message\" class=\"alert alert-warning\"> <b>[WARN]     " + m +"</b></div>";
            }
            if(m.contains("gave the attribute")){
                contentMsgs = contentMsgs + "<div id=\"message\" class=\"alert alert-warning\"> <b>[WARN]     " + m +"</b></div>";
            }
        }

        printRelationshipStrengths(redirectAttributes, relationshipStrengthMsgs);
        printRelationships(redirectAttributes, relationshipMsgs);
        printPS(redirectAttributes, psMsgs);
        printMgps(redirectAttributes, mgpsMsgs);
        printAsc(redirectAttributes, ascMsgs);
        printSent(redirectAttributes, sentMsgs);
        printContent(redirectAttributes, contentMsgs);


        return "redirect:/analysis";
    }

    protected void initDropDown(Model model){
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = personService.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
    }

    public void printRelationshipStrengths(RedirectAttributes r, String relationshipStrengthsMsgs){
        if(relationshipStrengthsMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("relationshipstrengthok", msg);
        }
        else {
            r.addFlashAttribute("relationshipstrength", relationshipStrengthsMsgs);
        }
    }

    public void printRelationships(RedirectAttributes r, String relationshipMsgs){
        if(relationshipMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("relationshipsok", msg);
        }
        else {
            r.addFlashAttribute("relationships", relationshipMsgs);
        }
    }

    public void printPS(RedirectAttributes r, String psMsgs){
        if(psMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("fpsok", msg);
        }
        else {
            r.addFlashAttribute("fps", psMsgs);
        }
    }

    public void printMgps(RedirectAttributes r, String groupMsgs){
        if(groupMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("gpsok", msg);
        }
        else {
            r.addFlashAttribute("gps", groupMsgs);
        }
    }

    public void printAsc(RedirectAttributes r, String ascMsgs){
        if(ascMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("ascok", msg);
        }
        else {
            r.addFlashAttribute("asc", ascMsgs);
        }
    }

    public void printSent(RedirectAttributes r, String sentMsgs){
        if(sentMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("sentMsgsok", msg);
        }
        else {
            r.addFlashAttribute("sentMsgs", sentMsgs);
        }
    }

    public void printContent(RedirectAttributes r, String sentMsgs){
        if(sentMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("contentMsgsok", msg);
        }
        else {
            r.addFlashAttribute("contentMsgs", sentMsgs);
        }
    }

}

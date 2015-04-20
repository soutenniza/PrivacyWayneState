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
        String interactionMsgs = "";
        String psMsgs = "";
        String mgpsMsgs = "";
        String ascMsgs = "";
        String sentMsgs = "";
        String contentMsgs = "";
        String othercontentMsgs = "";
        String distanceMsgs = "";
        String predictMsgs = "";
        String hsfpoMsgs = "";
        String cfpoMsgs = "";

        redirectAttributes.addFlashAttribute("user", "<i>Privacy analysis for the user "+personService.getPerson(p1).getName()+":</i>");

        // sort messages
        for(String m : messages){

            if(m.contains("distance")){
                distanceMsgs = distanceMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }
            if(m.contains("interactions")){
                interactionMsgs = interactionMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }

            if(m.contains("relationship")){
                relationshipStrengthMsgs = relationshipStrengthMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }
            if(m.contains("mutual friends")){
                relationshipMsgs = relationshipMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }

            if(m.contains("Privacy Score")){
                psMsgs = psMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }
            if(m.contains("mutual groups")){
                mgpsMsgs = mgpsMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }
            if(m.contains("association")){
                ascMsgs = ascMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }
            if(m.contains("negative comments")){
                sentMsgs = sentMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }
            if(m.contains("score, but")){
                contentMsgs = contentMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }
            if(m.contains("set as invisible")){
                othercontentMsgs = othercontentMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }
            if(m.contains("Flagged words")){
                predictMsgs = predictMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }
            if(m.contains("is an outlier")){
                hsfpoMsgs = hsfpoMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }
            if(m.contains("is an outlier")){
                cfpoMsgs = cfpoMsgs + "<div id=\"message\" class=\"alert alert-warning\"> [WARN]     " + m +"</div>";
            }
        }

        printDistances(redirectAttributes, distanceMsgs);
        printInteractions(redirectAttributes, interactionMsgs);
        printRelationshipStrengths(redirectAttributes, relationshipStrengthMsgs);
        printRelationships(redirectAttributes, relationshipMsgs);
        printPS(redirectAttributes, psMsgs);
        printMgps(redirectAttributes, mgpsMsgs);
        printAsc(redirectAttributes, ascMsgs);
        printSent(redirectAttributes, sentMsgs);
        printContent(redirectAttributes, contentMsgs);
        printotherContent(redirectAttributes, othercontentMsgs);
        printpredictMsgs(redirectAttributes, predictMsgs);
        printhsfpoMsgs(redirectAttributes, hsfpoMsgs);
        printcfpoMsgs(redirectAttributes, cfpoMsgs);


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

    public void printDistances(RedirectAttributes r, String distanceMsgs){
        if(distanceMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("distancesok", msg);
        }
        else {
            r.addFlashAttribute("distances", distanceMsgs);
        }
    }

    public void printInteractions(RedirectAttributes r, String interactionsMsgs){
        if(interactionsMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("interactionsok", msg);
        }
        else {
            r.addFlashAttribute("interactions", interactionsMsgs);
        }
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

    public void printotherContent(RedirectAttributes r, String sentMsgs){
        if(sentMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("othercontentMsgsok", msg);
        }
        else {
            r.addFlashAttribute("othercontentMsgs", sentMsgs);
        }
    }

    public void printpredictMsgs(RedirectAttributes r, String sentMsgs){
        if(sentMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("predictMsgsok", msg);
        }
        else {
            r.addFlashAttribute("predictMsgs", sentMsgs);
        }
    }

    public void printhsfpoMsgs(RedirectAttributes r, String sentMsgs){
        if(sentMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("hsfpook", msg);
        }
        else {
            r.addFlashAttribute("hsfpo", sentMsgs);
        }
    }

    public void printcfpoMsgs(RedirectAttributes r, String sentMsgs){
        if(sentMsgs==""){
            String msg = "No issues here!";
            r.addFlashAttribute("cfpook", msg);
        }
        else {
            r.addFlashAttribute("cfpo", sentMsgs);
        }
    }

}

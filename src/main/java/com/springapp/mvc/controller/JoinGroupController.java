package com.springapp.mvc.controller;

import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.AnalysisService;
import com.springapp.mvc.service.GroupAnalysisService;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class JoinGroupController {

    @Autowired
    PersonService service;

    @Autowired
    GroupAnalysisService groupAnalysisService; /*this causes the error*/

    @Autowired
    @Qualifier("personValidator")
    private Validator personValidator;

    @Autowired
    @Qualifier("groupValidator")
    private Validator groupValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(groupValidator);
        binder.setValidator(personValidator);
    }

    @RequestMapping(value = "/joingroup")
    public String displayJoinGroup(Model model){
        Person person = new Person();
        model.addAttribute("inputPerson", person);
        Group group = new Group();
        model.addAttribute("inputGroup", group);
        initDropDown(model);
        return "joingroup";
    }

    @RequestMapping(value = "/submitjoingroup", method = RequestMethod.POST, params={"submit", "!whatif"})
    @Transactional
    public String joinGroup(@RequestParam(value = "inputPerson") Long p,
                            @RequestParam(value = "inputGroup") Long g, Model model, final RedirectAttributes redirectAttributes){

        if(service.isMember(service.getGroup(g), service.getPerson(p))){
            String msg = service.getPerson(p).getName() + " is already a member of the group " + service.getGroup(g).getName() + "!";
            redirectAttributes.addFlashAttribute("ismember", msg);
        }
        else
        {
            service.addMember(service.getGroup(g), service.getPerson(p));
            String msg = service.getPerson(p).getName() + " joined the group " + service.getGroup(g).getName() + "!";
            redirectAttributes.addFlashAttribute("message", msg);
        }

        return "redirect:/joingroup";
    }

    @RequestMapping(value = "/submitjoingroup", method = RequestMethod.POST, params={"!submit", "whatif"})
    @Transactional
    public String whatIfGroup(@RequestParam(value = "inputPerson") Long p,
                              @RequestParam(value = "inputGroup") Long g, Model model, final RedirectAttributes redirectAttributes) {

        if(service.isMember(service.getGroup(g), service.getPerson(p))){
            String msg = service.getPerson(p).getName() + " is already a member of the group: " + service.getGroup(g).getName() + "!";
            redirectAttributes.addFlashAttribute("ismember", msg);
        }
        else
        {
            groupAnalysisService.setRoot(service.getPerson(p));
            ArrayList<String> messages = groupAnalysisService.calculateFriendInGroup(service.getPerson(p), service.getGroup(g));/* Look at GroupAnalysisService under service*/

            String relationshipMsgs = "";
            String msg;

            String whatIfMsg = "What if " + service.getPerson(p).getName() + " were to join the group: " + service.getGroup(g).getName() +"?";
            redirectAttributes.addFlashAttribute("whatifmsg", whatIfMsg);

            // sort messages
            // mutual friends
            for(String m : messages){
                if(m.contains("low number of friend")){
                    relationshipMsgs = relationshipMsgs + "<div id=\"message\" class=\"alert alert-warning\"> <b>[WARN]     " + m +"</b></div>";
                }
                /*if(m.contains("an outlier")){
                    relationshipMsgs = relationshipMsgs + "<div id=\"message\" class=\"alert alert-warning\"> <b>[WARN]     " + m +"</b></div>";
                }*/

            }

            // add notifications
            if(relationshipMsgs==""){
                msg = "No issues here!";
                redirectAttributes.addFlashAttribute("relationshipsok", msg);
            }
            else {
                redirectAttributes.addFlashAttribute("relationships", relationshipMsgs);
            }
        }

        return "redirect:/joingroup";

    }

    protected void initDropDown(Model model){
        // person list
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = service.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
        // group list
        Map<Long, String> groups = new LinkedHashMap<Long, String>();
        ArrayList<Group> group = service.getAllGroups();
        for(int i = 0; i < group.size(); i++){
            groups.put(group.get(i).getNodeID(), group.get(i).getName());
        }
        model.addAttribute("groupList", groups);
    }

}

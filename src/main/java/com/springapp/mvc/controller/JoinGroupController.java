package com.springapp.mvc.controller;

import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Person;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class JoinGroupController {

    @Autowired
    PersonService personRepository;

    @Autowired
    @Qualifier("personValidator")
    private Validator personValidator;

    @Autowired
    PersonService groupRepository;

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

    @RequestMapping(value = "/submitjoingroup", method = RequestMethod.POST)
    @Transactional
    public String joinGroup(
            @ModelAttribute("SpringWeb")Person person,
            @ModelAttribute("SpringWeb")Group group,
            ModelMap model,
            @RequestParam(value = "inputPerson") String p,
            @RequestParam(value = "inputGroup") String g ){

        //groupRepository.addMember(person, group);


        return "redirect:/joingroup";
    }

    protected void initDropDown(Model model){
        // person list
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = personRepository.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
        // group list
        Map<Long, String> groups = new LinkedHashMap<Long, String>();
        ArrayList<Group> group = groupRepository.getAllGroups();
        for(int i = 0; i < group.size(); i++){
            groups.put(group.get(i).getNodeID(), group.get(i).getName());
        }
        model.addAttribute("groupList", groups);
    }

}

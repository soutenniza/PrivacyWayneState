package com.springapp.mvc.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class AddFriendController {
    @Autowired
    PersonService service;

    @Autowired
    @Qualifier("personValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(validator);
    }


    @RequestMapping(value = "/addfriend", method = RequestMethod.GET)
    public String displayAddFriend(Model model){
        Person person1 = new Person();
        model.addAttribute("inputPerson1", person1);
        Person person2 = new Person();
        model.addAttribute("inputPerson2", person2);
        initDropDown(model);
        return "addfriend";
    }

    @RequestMapping(value = "/submitaddfriend", method = RequestMethod.POST)
    @Transactional
    public String addFriend(@RequestParam(value = "inputPerson1") Long p1,
            @RequestParam(value = "inputPerson2") Long p2, Model model, final RedirectAttributes redirectAttributes){

        if(p1==p2){
            String msg = "A person cannot be friends with themselves!";
            redirectAttributes.addFlashAttribute("exists", msg);
        }
        else if((service.areFriends(service.getPerson(p1),service.getPerson(p2)))&&(p1!=p2)) {
            String msg = service.getPerson(p1).getName() + " and " + service.getPerson(p1).getName() + " are already friends!";
            redirectAttributes.addFlashAttribute("exists", msg);
        }
        else
        {
            service.addFriend(service.getPerson(p1), service.getPerson(p2));
            service.addFriend(service.getPerson(p2), service.getPerson(p1));
            String msg = "Friends Added!";
            redirectAttributes.addFlashAttribute("added", msg);
        }

        return "redirect:/addfriend";
    }

    protected void initDropDown(Model model){
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = service.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
    }


}

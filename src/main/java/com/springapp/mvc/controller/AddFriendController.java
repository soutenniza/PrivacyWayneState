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
    PersonService personRepository;

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
    public String createUser(@RequestParam(value = "inputPerson1") Long p1,
            @RequestParam(value = "inputPerson2") Long p2 ){

        personRepository.addFriend(personRepository.getPerson(p1), personRepository.getPerson(p2));
        personRepository.addFriend(personRepository.getPerson(p2), personRepository.getPerson(p1));

        return "redirect:/addfriend";
    }

    protected void initDropDown(Model model){
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = personRepository.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
    }


}

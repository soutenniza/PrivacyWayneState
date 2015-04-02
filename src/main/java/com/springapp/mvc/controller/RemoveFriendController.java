package com.springapp.mvc.controller;

import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class RemoveFriendController {

    @Autowired
    PersonService service;

    @Autowired
    @Qualifier("personValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(validator);
    }

    public Person personTarget;
    public Person personFriend;

    @RequestMapping(value = "/removefriend", method = RequestMethod.GET)
    public String displayRemoveFriend(Model model){
        Person person1 = new Person();
        model.addAttribute("inputPerson1", person1);
        Person person2 = new Person();
        model.addAttribute("inputPerson2", person2);
        initDropDown(model);
        initDropDownFriends(model, personTarget);
        return "removefriend";
    }

    @RequestMapping(value = "/submitremovefriend", method = RequestMethod.POST, params={"submit", "!remove"})
    @Transactional
    public String rmSelectTarget(@RequestParam(value = "inputPerson1") Long p1, final RedirectAttributes redirectAttributes){

        personTarget = service.getPerson(p1);
        String msg = "Select a friend of " + personTarget.getName() + " to un-friend:";
        redirectAttributes.addFlashAttribute("gotfriends", msg);

        return "redirect:/removefriend";
    }

    @RequestMapping(value = "/submitremovefriend", method = RequestMethod.POST, params={"!submit", "remove"})
    @Transactional
    public String rmSelectFriend(@RequestParam(value = "inputPerson2") Long p1, Model model, final RedirectAttributes redirectAttributes){

        personFriend = service.getPerson(p1);

        return "redirect:/removefriend";
    }

    protected void initDropDown(Model model){
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = service.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
    }

    protected void initDropDownFriends(Model model, Person person){
        if(person != null){
            System.out.println(person.getName());
            Map<Long, String> peoples = new LinkedHashMap<Long, String>();
            Collection<Person> people = person.getFriends();
            for(Person p : people){
                peoples.put(p.getNodeID(), p.getName());
                System.out.println(p.getName());
            }
            model.addAttribute("peopleList2", peoples);
        }
    }

}

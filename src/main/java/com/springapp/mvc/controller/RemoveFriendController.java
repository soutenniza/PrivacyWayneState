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

    public Long personTargetId;
    public Long personFriendId;

    @RequestMapping(value = "/removefriend", method = RequestMethod.GET)
    public String displayRemoveFriend(Model model){
        Person person1 = new Person();
        model.addAttribute("inputPerson1", person1);
        Person person2 = new Person();
        model.addAttribute("inputPerson2", person2);
        initDropDown(model);
        initDropDownFriends(model, personTargetId);
        return "removefriend";
    }

    @RequestMapping(value = "/submitremovefriend", method = RequestMethod.POST, params={"submit", "!remove"})
    @Transactional
    public String rmSelectTarget(@RequestParam(value = "inputPerson1") Long p1, final RedirectAttributes redirectAttributes){

        personTargetId = service.getPerson(p1).getNodeID();
        String msg = "Select a friend of " + service.getPerson(personTargetId).getName() + " to un-friend:";
        redirectAttributes.addFlashAttribute("gotfriends", msg);

        return "redirect:/removefriend";
    }

    @RequestMapping(value = "/submitremovefriend", method = RequestMethod.POST, params={"!submit", "remove"})
    @Transactional
    public String rmSelectFriend(@RequestParam(value = "inputPerson2") Long p1, Model model, final RedirectAttributes redirectAttributes){

        personFriendId = p1;

        String msg = service.getPerson(personTargetId).getName() + " and " + service.getPerson(personFriendId).getName() + " are no longer friends!";
        redirectAttributes.addFlashAttribute("success", msg);

        service.removeFriendship(service.getPerson(personFriendId), service.getPerson(personTargetId));

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

    protected void initDropDownFriends(Model model, Long pid){
        if(pid != null){
            Map<Long, String> peoples2 = new LinkedHashMap<Long, String>();
            Collection<Person> people = service.getPerson(pid).getFriends();
            for(Person p : people){
                peoples2.put(p.getNodeID(), service.getPerson(p.getNodeID()).getName());
            }
            model.addAttribute("peopleList2", peoples2);
        }
    }

}

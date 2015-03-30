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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Zachary on 3/16/2015.
 * Updated by Dishank on 3/29/2015.
 */
@Controller
public class RemoveUserController {

    @Autowired
    PersonService service;

    @Autowired
    @Qualifier("personValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/removeuser", method = RequestMethod.GET)
    public String displayRemoveUser(Model model){
        Person person1 = new Person();
        model.addAttribute("inputUser", person1);
        initDropDown(model);
        return "removeuser";
    }

    @RequestMapping(value = "/submitremoveuser", method = RequestMethod.POST)
    @Transactional
    public String addFriend(@RequestParam(value = "inputUser") long p, Model model, final RedirectAttributes redirectAttributes){

        String msg = "The user " + service.getPerson(p).getName() + " was deleted!";
        redirectAttributes.addFlashAttribute("message", msg);

        service.removeUser(p);

        return "redirect:/removeuser";
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

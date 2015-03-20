package com.springapp.mvc.controller;

import com.springapp.mvc.model.Person;
import com.springapp.mvc.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class CreateUserController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("createuser");
        return mav;
    }


    @RequestMapping(value = "/submitcreateuser", method = RequestMethod.POST)
    @Transactional
    public String createUser(@ModelAttribute("SpringWeb")Person person,
                             ModelMap model) {
        model.addAttribute("name", person.getName());
        personRepository.save(person);

        return "redirect:/";
    }
}

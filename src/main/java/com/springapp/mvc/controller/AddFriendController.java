package com.springapp.mvc.controller;

import com.springapp.mvc.model.Person;
import com.springapp.mvc.repository.PersonRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class AddFriendController {
    PersonRepositoryImpl personRepository;

    @RequestMapping(value = "/addfriend")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("addfriend");


        return mav;
    }

    @RequestMapping(value = "/submitaddfriend", method = RequestMethod.POST)
    @Transactional
    public String createUser(
            @ModelAttribute("SpringWeb")Person person1,
            @ModelAttribute("SpringWeb")Person person2,
            ModelMap model,
            @RequestParam(value = "inputPerson1") String p1,
            @RequestParam(value = "inputPerson2") String p2 ){

        personRepository.addFriend(person1, person2);

        //person1.friends(person2);

        return "redirect:/addfriend";
    }

    public void populateUserLists(){


    }


}

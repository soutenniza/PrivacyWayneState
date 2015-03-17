package com.springapp.mvc;

import com.springapp.mvc.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class CreateUserController {
    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("createuser");
        return mav;
    }


    @RequestMapping(value = "/submitcreateuser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("SpringWeb")Person person,
                             ModelMap model) {
        model.addAttribute("name", person.getName());


        return "result";
    }
}

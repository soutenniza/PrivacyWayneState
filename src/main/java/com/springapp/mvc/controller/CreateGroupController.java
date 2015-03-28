package com.springapp.mvc.controller;

import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.repository.GroupRepository;
import com.springapp.mvc.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class CreateGroupController {

    @Autowired
    GroupRepository groupRepository;

    @RequestMapping(value = "/creategroup")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("creategroup");
        return mav;
    }


    @RequestMapping(value = "/submitcreategroup", method = RequestMethod.POST)
    @Transactional
    public String createGroup(
            @ModelAttribute("SpringWeb")Group group,
            ModelMap model,
            @RequestParam(value = "inputGroupName") String gName){

        group.setName(gName);

        model.addAttribute("name", group.getName());

        groupRepository.save(group);

        return "redirect:/creategroup";
    }

}

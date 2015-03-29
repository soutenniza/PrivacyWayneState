package com.springapp.mvc.controller;

import com.springapp.mvc.model.Group;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class DeleteGroupController {

    @Autowired
    PersonService service;

    @Autowired
    @Qualifier("groupValidator")
    private Validator groupValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(groupValidator);
    }

    @RequestMapping(value = "/deletegroup")
    public String displayJoinGroup(Model model){
        Group group = new Group();
        model.addAttribute("inputGroup", group);
        initDropDown(model);
        return "/deletegroup";
    }

    @RequestMapping(value = "/submitdeletegroup", method = RequestMethod.POST)
    @Transactional
    public String addFriend(@RequestParam(value = "inputGroup") Long g, Model model, final RedirectAttributes redirectAttributes){

        String msg = "The group " + service.getGroup(g).getName() + " was deleted!";
        redirectAttributes.addFlashAttribute("message", msg);

        service.deleteGroup(g);

        return "redirect:/deletegroup";
    }

    protected void initDropDown(Model model){
        // group list
        Map<Long, String> groups = new LinkedHashMap<Long, String>();
        ArrayList<Group> group = service.getAllGroups();
        for(int i = 0; i < group.size(); i++){
            groups.put(group.get(i).getNodeID(), group.get(i).getName());
        }
        model.addAttribute("groupList", groups);
    }
}

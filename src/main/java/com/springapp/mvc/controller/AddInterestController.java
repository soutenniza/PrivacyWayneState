package com.springapp.mvc.controller;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Comment;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class AddInterestController {
    @Autowired
    PersonService service;

    @Autowired
    @Qualifier("personValidator")
    private Validator personValidator;

    @RequestMapping(value = "/addinterest")
    public String displayAddComment(Model model){
        Person person = new Person();
        model.addAttribute("inputPerson", person);
        initDropDown(model);
        return "addinterest";
    }

    @RequestMapping(value = "/submitnewinterest", method = RequestMethod.POST)
    @Transactional
    public String addInterest(@RequestParam(value = "inputPerson") Long p,
                                @RequestParam(value = "inputInterest") String t,
                                @RequestParam(value = "inputP") String pv,
                                @RequestParam(value = "inputV") String vv,
                                @RequestParam(value = "inputS") String sv,
                                Model model,
                                final RedirectAttributes redirectAttributes){

        Attribute a = service.createAttribute("interest", t);
        a = service.getAttributeWithId(a.getNodeID());

        if(service.hasInterest(service.getPerson(p).getNodeID(), a.getNodeID())){
            String msg = "Person already had this interest!";
            redirectAttributes.addFlashAttribute("messagefail", msg);
        }
        else{
            service.addAttribute(a, service.getPerson(p), Integer.parseInt(pv), Integer.parseInt(vv), Integer.parseInt(sv));
            String msg = "Interest added!";
            redirectAttributes.addFlashAttribute("message", msg);
        }

        return "redirect:/addinterest";
    }

    protected void initDropDown(Model model){
        // person list
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = service.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
    }


}

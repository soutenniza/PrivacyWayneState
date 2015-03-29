package com.springapp.mvc.controller;

import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.AnalysisService;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vannguyen on 3/11/15.
 */
@Controller
public class AnalysisController {
    @Autowired
    PersonService personService;

    @Autowired
    AnalysisService analysisService;

    @RequestMapping(value = "/analysis", method = RequestMethod.GET)
    public String displayAnalysisPage(Model model){
        Person person1 = new Person();
        model.addAttribute("inputPerson1", person1);
        initDropDown(model);
        return "analysis";
    }

    @RequestMapping(value = "/submitanalysis", method = RequestMethod.POST)
    @Transactional
    public String addFriend(@RequestParam(value = "inputPerson1") Long p1,
                            Model models){
        analysisService.setRoot(personService.getPerson(p1));
        analysisService.fullAnalysis();
        return "redirect:/analysis";
    }

    protected void initDropDown(Model model){
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = personService.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
    }
}

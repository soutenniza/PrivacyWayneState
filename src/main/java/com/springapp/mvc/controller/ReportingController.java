package com.springapp.mvc.controller;

import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.AnalysisService;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vannguyen on 3/11/15.
 */
@Controller
public class ReportingController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/reporting", method = RequestMethod.GET)
    public String displayAnalysisPage(Model model){
        Person person = new Person();
        model.addAttribute("inputPerson", person);
        initDropDown(model);
        return "reporting";
    }

    @RequestMapping(value = "/submitreport", method = RequestMethod.POST)
    @Transactional
    public String addFriend(@RequestParam(value = "inputPerson") Long p1,
                            Model models, final RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("ran", "loaded");
        redirectAttributes.addFlashAttribute("user", "<i>Privacy report for the user " + personService.getPerson(p1).getName() + ":</i>");


        System.out.println(personService.getLatestPrivacyScore(p1));

        int pscore = personService.getLatestPrivacyScore(p1);


        if(pscore == -1){
            redirectAttributes.addFlashAttribute("pscore", "No privacy score have been calculated for this user as of yet.");
        }
        else{
            redirectAttributes.addFlashAttribute("pscore", "Privacy score: " + pscore);
            redirectAttributes.addFlashAttribute("psdata", genPSDataLine("Privacy Score", p1));
        }

        return "redirect:/reporting";
    }

    protected void initDropDown(Model model){
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = personService.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
    }

    public String genPSDataLine(String name, Long pid){
        String msg = "['"+name+"', ";
        Collection<Integer> vals = personService.getPrivacyScoreRecord(pid);
        if(vals.isEmpty()){
            return "['No History']";
        }
        else{
            for(int x : vals){
                msg.concat(Integer.toString(x) + ", ");
            }
        }
        msg.concat("],");
        return msg;
    }

}

package com.springapp.mvc.controller;

import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.AnalysisService;
import com.springapp.mvc.service.ImportService;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class ImportController {
    @Autowired
    AnalysisService analysisService;

    @Autowired
    PersonService service;

    @Autowired
    ImportService importService;

    @RequestMapping(value = "/import")
    public String displayImport(){
        return "/import";
    }

    @RequestMapping(value = "/submitimport", method = RequestMethod.POST, params={"delete", "!import", "!"})
    @Transactional
    public String deleteData(@RequestParam(value = "pass") String s, @RequestParam(value = "inputNum") String n, final RedirectAttributes redirectAttributes){

        if(s.equals("Erfan")){
            redirectAttributes.addFlashAttribute("deleted", "All data deleted!");
            service.deleteAll();
        }
        else
        {
            redirectAttributes.addFlashAttribute("wrong", "Get out of here, you don't even know the passcode.");
        }
        return "redirect:/import";
    }

    @RequestMapping(value = "/submitimport", method = RequestMethod.POST, params={"!delete", "import", "!meganaalysis"})
    @Transactional
    public String importData(@RequestParam(value = "pass") String s, @RequestParam(value = "inputNum") String n, final RedirectAttributes redirectAttributes){

        if(s.equals("Erfan")){
            boolean pass = false;
            System.out.println(Integer.parseInt(n));
            pass = importService.importFromJSON(Integer.parseInt(n));
            if(pass){
                redirectAttributes.addFlashAttribute("pass", "Data set imported!");
            }
            else
            {
                redirectAttributes.addFlashAttribute("fail", "Import failed!");
            }
        }
        else
        {
            redirectAttributes.addFlashAttribute("wrong", "Get out of here, you don't even know the passcode.");
        }
        return "redirect:/import";
    }

    @RequestMapping(value = "/submitimport", method = RequestMethod.POST,  params={"!delete", "!import", "megaanalysis"})
    @Transactional
    public String allAnalysis(@RequestParam(value = "pass") String s, @RequestParam(value = "inputNum") String n, final RedirectAttributes redirectAttributes){
        if(s.equals("Erfan")){
            redirectAttributes.addFlashAttribute("pass", "Analysis for everyone done!");
            System.out.println("Starting analysis for all users.");
            Collection<Person> persons = service.getAllPersons();
            int numPeople = 0;
            for(Person p : persons){
                numPeople = numPeople + 1;
            }
            int counter = 1;
            for(Person p : persons){
                System.out.println("Running analysis for user " + Integer.toString(counter) + " of " + Integer.toString(numPeople)+".");
                analysisService.setRoot(service.getPerson(p.getNodeID()));
                analysisService.fullAnalysis();
                counter = counter + 1;
            }
            System.out.println("MEGAANALYSIS complete without critical error..");
        }
        else
        {
            redirectAttributes.addFlashAttribute("wrong", "Get out of here, you don't even know the passcode.");
        }

        return "redirect:/import";
    }
}

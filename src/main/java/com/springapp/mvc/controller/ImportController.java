package com.springapp.mvc.controller;

import com.springapp.mvc.model.Group;
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

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class ImportController {

    @Autowired
    PersonService service;

    @Autowired
    ImportService importService;

    @RequestMapping(value = "/import")
    public String displayImport(){
        return "/import";
    }

    @RequestMapping(value = "/submitdeleteset", method = RequestMethod.POST, params={"delete", "!import"})
    @Transactional
    public String deleteData(@RequestParam(value = "pass") String s, final RedirectAttributes redirectAttributes){

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

    @RequestMapping(value = "/submitimport", method = RequestMethod.POST, params={"!delete", "import"})
    @Transactional
    public String importData(final RedirectAttributes redirectAttributes){

        boolean pass = false;

        service.deleteAll();

        pass = importService.importFromJSON();

        if(pass){
            redirectAttributes.addFlashAttribute("pass", "Data set imported!");
        }
        else
        {
            redirectAttributes.addFlashAttribute("fail", "Import failed!");
        }
        return "redirect:/import";
    }

}

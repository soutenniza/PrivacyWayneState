package com.springapp.mvc.controller;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.HasRelationship;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.PersonService;
import org.neo4j.cypher.internal.compiler.v1_9.commands.Has;
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
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class ChangePrivacyLevelsController {

    @Autowired
    PersonService service;

    @Autowired
    @Qualifier("personValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(validator);
    }

    public Long personId;
    public Long attId;

    @RequestMapping(value = "/changeplevels", method = RequestMethod.GET)
    public String displayChangeplevels(Model model){
        Person person = new Person();
        model.addAttribute("inputPerson", person);
        Attribute att = new Attribute();
        model.addAttribute("inputAtt", att);
        initDropDown(model);
        initDropDownAtts(model, personId);
        return "changeplevels";
    }

    @RequestMapping(value = "/submitsavechanges", method = RequestMethod.POST, params={"submit", "!savechanges", "!dynamicv"})
    @Transactional
    public String attSelectTarget(@RequestParam(value = "inputPerson1") Long p1, final RedirectAttributes redirectAttributes){

        personId = service.getPerson(p1).getNodeID();

        String msg = "Select one of " + service.getPerson(personId).getName() + "'s attributes to modify:";
        redirectAttributes.addFlashAttribute("gotatts", msg);

        return "redirect:/changeplevels";
    }

    @RequestMapping(value = "/submitsavechanges", method = RequestMethod.POST, params={"!submit", "!savechanges", "dynamicv"})
    @Transactional
    public String setDynamicV(@RequestParam(value = "inputAtt1") Long a1, final RedirectAttributes redirectAttributes){
        attId = a1;

        int vv = service.updateHasRelationshipDynamicVisibility(personId, attId);

        String msg = service.getPerson(personId).getName() + "'s visibility values for the " +
                service.getAttributeWithId(attId).getLabel() + " attribute have been updated dynamically";
        redirectAttributes.addFlashAttribute("success", msg);

        return "redirect:/changeplevels";
    }

    @RequestMapping(value = "/submitsavechanges", method = RequestMethod.POST, params={"!submit", "savechanges", "!dynamicv"})
    @Transactional
    public String attSelectFriend(@RequestParam(value = "inputAtt1") Long a1,
                                 @RequestParam(value = "inputP") String pv,
                                 @RequestParam(value = "inputV") String vv,
                                 @RequestParam(value = "inputS") String sv,
                                 Model model,
                                 final RedirectAttributes redirectAttributes){

        attId = a1;

        service.updateHasRelationship(personId, attId, Integer.parseInt(pv), Integer.parseInt(vv), Integer.parseInt(sv));

        String msg = service.getPerson(personId).getName() + "'s privacy values for the " +
                service.getAttributeWithId(attId).getLabel() + " attribute have been updated!";
        redirectAttributes.addFlashAttribute("success", msg);


        return "redirect:/changeplevels";
    }

    protected void initDropDown(Model model){
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = service.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
    }

    protected void initDropDownAtts(Model model, Long pid){
        if(pid != null){
            Collection<HasRelationship> has = service.getPerson(pid).getAttributeRelationships();
            Map<Long, String> atts = new LinkedHashMap<Long, String>();
            for(HasRelationship h : has){
                h = service.getHasRelationship(h.getId());
                Attribute a = service.getAttributeWithId(h.getEnd().getNodeID());
                String label = a.getLabel()+": "+a.getValue()+" | P: "+h.getPv()+", V: "+h.getVv()+", S: "+h.getSv();
                //System.out.println(label);
                atts.put(a.getNodeID(), label);
            }
            model.addAttribute("attList", atts);
        }
    }
}

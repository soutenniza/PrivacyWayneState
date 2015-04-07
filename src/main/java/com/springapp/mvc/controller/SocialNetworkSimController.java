package com.springapp.mvc.controller;

/**
 * Created by Zack on 4/6/15.
 */

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Comment;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


@Controller
public class SocialNetworkSimController {

    public Long userId;

    @Autowired
    PersonService service;

    @Autowired
    @Qualifier("personValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/snviewlogin")
    public String displayLogin(Model model){
        Person person = new Person();
        model.addAttribute("inputPerson", person);
        initDropDown(model);
        return "/snviewlogin";
    }

    @RequestMapping(value = "/snviewhome")
    public String displaySNSim(Model model, final RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("user", service.getPerson(userId).getName());


        return "snviewhome";
    }

    @RequestMapping("/snviewprofile")
    public ModelAndView viewProfile(@RequestParam("id") Long id ) {

        ModelAndView mav = new ModelAndView("snviewprofile");

        mav.addObject("user", service.getPerson(userId).getName());

        mav.addObject("username", service.getPerson(id).getName());
        mav.addObject("attributes", mgenAttributeList(service.getPerson(id)));
        mav.addObject("friends", mgenFriendsList(service.getPerson(id)));
        mav.addObject("comments", mgenCommentsList(service.getPerson(id)));
        mav.addObject("likes", mgenLikesList(service.getPerson(id)));
        mav.addObject("groups", mgenGroupsList(service.getPerson(id)));

        return mav;
    }

    @RequestMapping("/snviewpost")
    public ModelAndView viewPost(@RequestParam("id") Long id ) {

        ModelAndView mav = new ModelAndView("snviewpost");

        mav.addObject("user", service.getPerson(userId).getName());

        mav.addObject("username", service.getPerson(service.getComment(id).getOwnerID()).getName());
        mav.addObject("originaltext", service.getComment(id).getText());
        mav.addObject("replies", mgenReplies(id));




        return mav;
    }

    @RequestMapping(value = "/submitsession", method = RequestMethod.POST, params={"myprofile"})
    @Transactional
    public String viewHome(){
        return "redirect:/snviewprofile/?id=" + userId;
    }

    @RequestMapping(value = "/submitsession", method = RequestMethod.POST, params={"logout"})
    @Transactional
    public String logOut(){ return "redirect:/snviewlogin"; }

    @RequestMapping(value = "/submitlogin", method = RequestMethod.POST, params={"new"})
    @Transactional
    public String deleteData(){ return "redirect:/createuser"; }

    @RequestMapping(value = "/submitlogin", method = RequestMethod.POST, params={"login"})
    @Transactional
    public String showSNSim(@RequestParam(value = "inputPerson") Long pid){
        userId = pid;
        return "redirect:/snviewprofile/?id=" + userId;
    }



//================================================== FUNCTIONAL THINGS ================================================//

    protected void initDropDown(Model model){
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = service.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
    }

    public String normalizeString(String val){
        if(val.length() == 0){
            return "";
        }
        if(val.length() == 1){
            return val.toUpperCase();
        }
        return val.substring(0,1).toUpperCase() + val.substring(1).toLowerCase();
    }

    //============================================ MGEN ===============================================================//

    public String mgenAttributeList(Person p){
        String html = "";

        Collection<Attribute> atts = p.getAttributes();
        if(atts.isEmpty()){
            html = p.getName() + " somehow has no attributes. Something went terribly wrong. Please email me at zackrzot@gmail.com.";
        }
        else{
            html = "<h3>Attributes:</h3><br><ul class=\"list-group\">";
            for(Attribute a : atts){
                Long pID = a.getNodeID();
                Attribute aa = service.getAttributeWithId(pID);
                html = html.concat("<li class=\"list-group-item\">" + normalizeString(aa.getLabel()) + ": " + aa.getValue() + "</li>");
            }
            html = html.concat("</ul>");
        }
        return html;
    }

    public String mgenFriendsList(Person p){
        String html = "";
        Collection<Person> persons = p.getFriends();
        if(persons.isEmpty()){
            html = p.getName() + " has no friends..  :( ";
        }
        else{
            html = "<h3>Friends:</h3><br><div class=\"list-group\">";
            for(Person ap : persons){
                Long pID = ap.getNodeID();
                html = html.concat("<a href=\"" +  "/snviewprofile/?id=" + service.getPerson(pID).getNodeID() + "\" class= \"list-group-item\">" + service.getPerson(pID).getName() + "</a>");
            }
            html = html.concat("</div>");
        }
        return html;
    }

    public String mgenCommentsList(Person p){
        String html = "";
        Collection<Comment> comments = p.getComments();
        if(comments.isEmpty()){
            html = p.getName() + " has not made any comments yet!";
        }
        else{
            html = "<h3>Comments:</h3><br><div class=\"list-group\">";
            for(Comment ac : comments){
                Long pID = ac.getNodeID();
                html = html.concat("<a href=\"" +  "/snviewpost/?id=" + service.getComment(pID).getNodeID() + "\" class= \"list-group-item\">" + service.getComment(pID).getText() + "</a>");
            }
            html = html.concat("</div>");
        }
        return html;
    }

    public String mgenLikesList(Person p){
        String html = "";
        Collection<Comment> likes = p.getLikes();
        if(likes.isEmpty()){
            html = p.getName() + " has not liked any comments yet!";
        }
        else{
            html = "<h3>Liked comments:</h3><br><ul class=\"list-group\">";
            for(Comment ac : likes){
                Long pID = ac.getNodeID();
                Comment acc = service.getComment(pID);
                html = html.concat("<li class=\"list-group-item\">" + service.getPerson(acc.getOwnerID()).getName() + ": " + acc.getText() + "</li>");
            }
            html = html.concat("</ul>");
        }
        return html;
    }

    public String mgenGroupsList(Person p){
        String html = "";
        Collection<Group> groups = p.getGroups();
        if(groups.isEmpty()){
            html = p.getName() + " is not a member of any group!";
        }
        else{
            html = "<h3>Groups:</h3><br><ul class=\"list-group\">";
            for(Group ac : groups){
                Long pID = ac.getNodeID();
                html = html.concat("<li class=\"list-group-item\">" + service.getGroup(pID).getName() + "</li>");
            }
            html = html.concat("</ul>");
        }
        return html;
    }

    public String mgenReplies(Long id){
        String html = "<li class=\"list-group-item\">Jonny Tompson: This is a test.</li>";


        //<li class="list-group-item">Cras justo odio</li>


        return html;
    }

}
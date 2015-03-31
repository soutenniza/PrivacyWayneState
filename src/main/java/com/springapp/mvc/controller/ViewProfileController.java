package com.springapp.mvc.controller;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Comment;
import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.repository.CommentRepository;
import com.springapp.mvc.repository.PersonRepository;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
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
public class ViewProfileController {

    @Autowired
    PersonService service;

    @RequestMapping(value = "/viewprofile")
    public String displayLikeComment(Model model){
        Person person = new Person();
        model.addAttribute("inputPerson", person);
        initDropDown(model);
        return "viewprofile";
    }

    @RequestMapping(value = "/submitviewprofile", method = RequestMethod.POST)
    @Transactional
    public String addFriend(@RequestParam(value = "inputPerson") Long p, Model model, final RedirectAttributes redirectAttributes){

        Person user = service.getPerson(p);
        String userNameMsg = user.getName();
        redirectAttributes.addFlashAttribute("username", userNameMsg);

        genAttributeList(user, redirectAttributes);
        genFriendsList(user, redirectAttributes);
        genGroupsList(user, redirectAttributes);
        genCommentsList(user, redirectAttributes);
        genLikesList(user, redirectAttributes);

        return "redirect:/viewprofile";
    }


    protected void initDropDown(Model model) {
        // person list
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = service.getAllPersons();
        for (int i = 0; i < people.size(); i++) {
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
    }

    public void genAttributeList(Person p, RedirectAttributes r){
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
        r.addFlashAttribute("attributes", html);
    }

    public void genFriendsList(Person p, RedirectAttributes r){
        String html = "";
        Collection<Person> persons = p.getFriends();
        if(persons.isEmpty()){
            html = p.getName() + " has no friends..  :( ";
        }
        else{
            html = "<h3>Friends:</h3><br><ul class=\"list-group\">";
            for(Person ap : persons){
                Long pID = ap.getNodeID();
                html = html.concat("<li class=\"list-group-item\">" + service.getPerson(pID).getName() + "</li>");
            }
            html = html.concat("</ul>");
        }
        r.addFlashAttribute("friends", html);
    }

    public void genCommentsList(Person p, RedirectAttributes r){
        String html = "";
        Collection<Comment> comments = p.getComments();
        if(comments.isEmpty()){
            html = p.getName() + " has not made any comments yet!";
        }
        else{
            html = "<h3>Comments:</h3><br><ul class=\"list-group\">";
            for(Comment ac : comments){
                Long pID = ac.getNodeID();
                html = html.concat("<li class=\"list-group-item\">" + service.getComment(pID).getText() + "</li>");
            }
            html = html.concat("</ul>");
        }
        r.addFlashAttribute("comments", html);
    }

    public void genLikesList(Person p, RedirectAttributes r){
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
        r.addFlashAttribute("likes", html);
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

    public void genGroupsList(Person p, RedirectAttributes r){
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
        r.addFlashAttribute("groups", html);
    }

}

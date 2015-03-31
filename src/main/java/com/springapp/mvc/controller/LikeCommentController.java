package com.springapp.mvc.controller;

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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class LikeCommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService service;

    @Autowired
    @Qualifier("personValidator")
    private Validator personValidator;

    @Autowired
    @Qualifier("commentValidator")
    private Validator commentValidator;


    @RequestMapping(value = "/likecomment")
    public String displayLikeComment(Model model){
        Person person = new Person();
        model.addAttribute("inputPerson", person);
        Comment comment = new Comment();
        model.addAttribute("inputComment", comment);
        initDropDown(model);
        return "likecomment";
    }

    @RequestMapping(value = "/submitlikecomment", method = RequestMethod.POST)
    @Transactional
    public String addFriend(@RequestParam(value = "inputPerson") Long p,
                            @RequestParam(value = "inputComment") Long c, Model model, final RedirectAttributes redirectAttributes){

        if(service.likesComment(service.getComment(c), service.getPerson(p))){
            String msg = service.getPerson(p).getName() + " already likes the selected comment!";
            redirectAttributes.addFlashAttribute("alreadylikes", msg);
        }
        else
        {
            service.addLike(service.getComment(c), service.getPerson(p));
            String msg = service.getPerson(p).getName() + " likes the selected comment!";
            redirectAttributes.addFlashAttribute("message", msg);
        }

        return "redirect:/likecomment";
    }

    protected void initDropDown(Model model) {
        // person list
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = service.getAllPersons();
        for (int i = 0; i < people.size(); i++) {
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
        // comment list
        Map<Long, String> comments = new LinkedHashMap<Long, String>();
        ArrayList<Comment> comment = service.getAllComments();
        for(int i = 0; i < comment.size(); i++){
           comments.put(comment.get(i).getNodeID(), service.getCommentOwner(comment.get(i)).getName() + ": " + comment.get(i).getText());
        }
        model.addAttribute("commentList", comments);
    }



}

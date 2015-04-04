package com.springapp.mvc.controller;

import com.springapp.mvc.model.Person;
import com.springapp.mvc.model.Comment;
import com.springapp.mvc.repository.CommentRepository;
import com.springapp.mvc.repository.PersonRepository;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
public class AddCommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PersonService service;

    @Autowired
    @Qualifier("personValidator")
    private Validator personValidator;

    @Autowired
    @Qualifier("commentValidator")
    private Validator commentValidator;

    @RequestMapping(value = "/addcomment")
    public String displayAddComment(Model model){
        Person person = new Person();
        model.addAttribute("inputPerson", person);
        initDropDown(model);
        return "addcomment";
    }

    @RequestMapping(value = "/submitnewcomment", method = RequestMethod.POST)
    @Transactional
    public String addFriend(@RequestParam(value = "inputPerson") Long p,
                            @ModelAttribute("SpringWeb")Comment comment,
                            @RequestParam(value = "inputComment") String t, Model model, final RedirectAttributes redirectAttributes){


        if(service.commentExists(t)){
            String msg = "This comment has already been made. Why don't you say something new?";
            redirectAttributes.addFlashAttribute("fail", msg);
        }
        else
        {
            comment.setText(t);
            comment.setOwnerId(p);
            commentRepository.save(comment);

            service.addComment(comment, service.getPerson(p));

            String msg = "Comment created!";
            redirectAttributes.addFlashAttribute("message", msg);
        }

        return "redirect:/addcomment";
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

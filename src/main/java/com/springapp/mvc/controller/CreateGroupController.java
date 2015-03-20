package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class CreateGroupController {
    @RequestMapping(value = "/creategroup")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("creategroup");
        return mav;
    }
}

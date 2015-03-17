package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class RemoveUserContoller {
    @RequestMapping(value = "/removeuser")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("removeuser");
        return mav;
    }
}

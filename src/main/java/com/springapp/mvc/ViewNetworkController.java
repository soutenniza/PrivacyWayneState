package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by vannguyen on 3/11/15.
 */
@Controller
public class ViewNetworkController {
    @RequestMapping(value = "/network")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("network");
        return mav;
    }
}

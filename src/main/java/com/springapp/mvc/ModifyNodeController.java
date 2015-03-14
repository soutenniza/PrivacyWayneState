package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Zachary on 3/11/2015.
 */
@Controller
public class ModifyNodeController {
    @RequestMapping(value = "/modifynode")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("modifynode");
        return mav;
    }
}

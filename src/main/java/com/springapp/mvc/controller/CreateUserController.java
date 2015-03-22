package com.springapp.mvc.controller;

import com.springapp.mvc.model.Person;
import com.springapp.mvc.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class CreateUserController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("createuser");
        return mav;
    }

    @RequestMapping(value = "/submitcreateuser", method = RequestMethod.POST)
    @Transactional
    public String createUser(
            @ModelAttribute("SpringWeb")Person person,
            ModelMap model,
            @RequestParam(value = "inputFirstName") String fName,
            @RequestParam(value = "inputLastName") String lName,
            @RequestParam(value = "inputAge") String age,
            @RequestParam(value = "inputAgeP") String ageP,
            @RequestParam(value = "inputAgeV") String ageV,
            @RequestParam(value = "inputAgeS") String ageS,
            @RequestParam(value = "inputGender") String gender,
            @RequestParam(value = "inputGenderP") String genderP,
            @RequestParam(value = "inputGenderV") String genderV,
            @RequestParam(value = "inputGenderS") String genderS,
            @RequestParam(value = "inputLocation") String location,
            @RequestParam(value = "inputLocationP") String locationP,
            @RequestParam(value = "inputLocationV") String locationV,
            @RequestParam(value = "inputLocationS") String locationS,
            @RequestParam(value = "inputPolitical") String political,
            @RequestParam(value = "inputPoliticalP") String politicalP,
            @RequestParam(value = "inputPoliticalV") String politicalV,
            @RequestParam(value = "inputPoliticalS") String politicalS,
            @RequestParam(value = "inputWork") String work,
            @RequestParam(value = "inputWorkP") String workP,
            @RequestParam(value = "inputWorkV") String workV,
            @RequestParam(value = "inputWorkS") String workS,
            @RequestParam(value = "inputEducation") String education,
            @RequestParam(value = "inputEducationP") String educationP,
            @RequestParam(value = "inputEducationV") String educationV,
            @RequestParam(value = "inputEducationS") String educationS,
            @RequestParam(value = "inputBday") String bday,
            @RequestParam(value = "inputBdayP") String bdayP,
            @RequestParam(value = "inputBdayV") String bdayV,
            @RequestParam(value = "inputBdayS") String bdayS,
            @RequestParam(value = "inputPhone") String phone,
            @RequestParam(value = "inputPhoneP") String phoneP,
            @RequestParam(value = "inputPhoneV") String phoneV,
            @RequestParam(value = "inputPhoneS") String phoneS,
            @RequestParam(value = "inputInterest1") String interest1,
            @RequestParam(value = "inputInterest1P") String interest1P,
            @RequestParam(value = "inputInterest1V") String interest1V,
            @RequestParam(value = "inputInterest1S") String interest1S ){

                person.setName(fName+" "+lName);
                person.setAge(age);

                //System.out.println("Name captured from web page: "+fName+" "+lName);

                model.addAttribute("name", person.getName());
                model.addAttribute("age", person.getAge());
                personRepository.save(person);

                return "redirect:/createuser";
            }


}

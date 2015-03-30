package com.springapp.mvc.controller;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.repository.AttributeRepository;
import com.springapp.mvc.repository.PersonRepository;
import com.springapp.mvc.service.PersonService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Zachary on 3/16/2015.
 */
@Controller
public class CreateUserController {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    PersonService service;

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("createuser");
        return mav;
    }
    @RequestMapping(value = "/submitcreateuser", method = RequestMethod.POST)
    @Transactional
    public String createUser(   @RequestParam(value = "inputFirstName") String fName,
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
                                @RequestParam(value = "inputInterest1S") String interest1S,
                                final RedirectAttributes redirectAttributes
                                ){

        if(service.personExists(fName + " " + lName)){
            String msg = fName + " " + lName + " is already a user!";
            redirectAttributes.addFlashAttribute("exists", msg);
        }
        else {
            Person p = new Person(fName + " " + lName);
            personRepository.save(p);

            Attribute attAge = new Attribute("age", age);
            attributeRepository.save(attAge);
            service.addAttribute(attAge, p, Integer.parseInt(ageP), Integer.parseInt(ageV), Integer.parseInt(ageS));

            Attribute attGender = new Attribute("gender", gender);
            attributeRepository.save(attGender);
            service.addAttribute(attGender, p, Integer.parseInt(genderP), Integer.parseInt(genderV), Integer.parseInt(genderS));

            Attribute attLocation = new Attribute("location", location);
            attributeRepository.save(attLocation);
            service.addAttribute(attLocation, p, Integer.parseInt(locationP), Integer.parseInt(locationV), Integer.parseInt(locationS));

            Attribute attPolitical = new Attribute("political view", political);
            attributeRepository.save(attPolitical);
            service.addAttribute(attPolitical, p, Integer.parseInt(politicalP), Integer.parseInt(politicalV), Integer.parseInt(politicalS));

            Attribute attWork = new Attribute("work", work);
            attributeRepository.save(attWork);
            service.addAttribute(attWork, p, Integer.parseInt(workP), Integer.parseInt(workV), Integer.parseInt(workS));

            Attribute attEdu = new Attribute("education", education);
            attributeRepository.save(attEdu);
            service.addAttribute(attEdu, p, Integer.parseInt(educationP), Integer.parseInt(educationV), Integer.parseInt(educationS));

            Attribute attBd = new Attribute("birthday", bday);
            attributeRepository.save(attBd);
            service.addAttribute(attBd, p, Integer.parseInt(bdayP), Integer.parseInt(bdayV), Integer.parseInt(bdayS));

            Attribute attPh = new Attribute("phone numebr", phone);
            attributeRepository.save(attPh);
            service.addAttribute(attPh, p, Integer.parseInt(phoneP), Integer.parseInt(phoneV), Integer.parseInt(phoneS));

            Attribute attIt1 = new Attribute("interest", interest1);
            attributeRepository.save(attIt1);
            service.addAttribute(attIt1, p, Integer.parseInt(interest1P), Integer.parseInt(interest1V), Integer.parseInt(interest1S));

            String msg = "Created the user " + fName + " " + lName + "!";
            redirectAttributes.addFlashAttribute("valid", msg);
        }

        return "redirect:/createuser";
    }


}

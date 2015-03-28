package com.springapp.mvc.controller;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.repository.AttributeRepository;
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

    @Autowired
    AttributeRepository attributeRepository;

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("createuser");
        return mav;
    }
    @RequestMapping(value = "/submitcreateuser", method = RequestMethod.POST)
    @Transactional
    public String createUser(   @ModelAttribute("person")Person person,
                                @ModelAttribute("attAge")Attribute attAge,
                                @ModelAttribute("attGen")Attribute attGender,
                                @ModelAttribute("attLoc")Attribute attLocation,
                                @ModelAttribute("attPol")Attribute attPolitical,
                                @ModelAttribute("attWor")Attribute attWork,
                                @ModelAttribute("attEdu")Attribute attEdu,
                                @ModelAttribute("attBdy")Attribute attBd,
                                @ModelAttribute("attPhn")Attribute attPh,
                                @ModelAttribute("attIt1")Attribute attIt1,
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
                                @RequestParam(value = "inputInterest1S") String interest1S,
                                ModelMap modelPerson,
                                ModelMap modelAttAge,
                                ModelMap modelAttGender,
                                ModelMap modelAttLocation,
                                ModelMap modelAttPolitical,
                                ModelMap modelAttWork,
                                ModelMap modelAttEdu,
                                ModelMap modelAttBd,
                                ModelMap modelAttPh,
                                ModelMap modelAttIt1
                                ){

        person.setName(fName + " " + lName);
        modelPerson.addAttribute("name", person.getName());
        personRepository.save(person);

        attAge.setLabel("age");
        modelAttAge.addAttribute("label", attAge.getLabel());
        attAge.setValue(age);
        modelAttAge.addAttribute("value", attAge.getValue());
        attributeRepository.save(attAge);

        attGender.setLabel("gender");
        modelAttGender.addAttribute("label", attGender.getLabel());
        attGender.setValue(gender);
        modelAttGender.addAttribute("value", attGender.getValue());
        attributeRepository.save(attGender);

        attLocation.setLabel("location");
        modelAttLocation.addAttribute("label", attLocation.getLabel());
        attLocation.setValue(location);
        modelAttLocation.addAttribute("value", attLocation.getValue());
        attributeRepository.save(attLocation);

        attPolitical.setLabel("political view");
        modelAttPolitical.addAttribute("label", attPolitical.getLabel());
        attPolitical.setValue(political);
        modelAttPolitical.addAttribute("value", attPolitical.getValue());
        attributeRepository.save(attPolitical);

        attWork.setLabel("work");
        modelAttWork.addAttribute("label", attWork.getLabel());
        attWork.setValue(work);
        modelAttWork.addAttribute("value", attWork.getValue());
        attributeRepository.save(attWork);

        attEdu.setLabel("education");
        modelAttEdu.addAttribute("label", attEdu.getLabel());
        attEdu.setValue(education);
        modelAttEdu.addAttribute("value", attEdu.getValue());
        attributeRepository.save(attEdu);

        attBd.setLabel("birthday");
        modelAttBd.addAttribute("label", attBd.getLabel());
        attBd.setValue(bday);
        modelAttBd.addAttribute("value", attBd.getValue());
        attributeRepository.save(attBd);

        attPh.setLabel("phone number");
        modelAttPh.addAttribute("label", attPh.getLabel());
        attPh.setValue(phone);
        modelAttPh.addAttribute("value", attPh.getValue());
        attributeRepository.save(attPh);

        attIt1.setLabel("interest");
        modelAttIt1.addAttribute("label", attIt1.getLabel());
        attIt1.setValue(interest1);
        modelAttIt1.addAttribute("value", attIt1.getValue());
        attributeRepository.save(attIt1);

        return "redirect:/createuser";
    }


}

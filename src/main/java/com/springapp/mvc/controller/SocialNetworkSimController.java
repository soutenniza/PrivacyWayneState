package com.springapp.mvc.controller;

/**
 * Created by Zack on 4/6/15.
 */

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Comment;
import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.service.AnalysisService;
import com.springapp.mvc.service.PersonService;
import com.springapp.mvc.service.PrivacyProfileAnalysisService;
import edu.stanford.nlp.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


@Controller
public class SocialNetworkSimController {

    public Long userId;
    public Long previousId;
    public Long previousCommentId;

    @Autowired
    PersonService service;

    @Autowired
    AnalysisService analysisService;



    @Autowired
    @Qualifier("personValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/snviewlogin")
    public String displayLogin(Model model){
        Person person = new Person();
        model.addAttribute("inputPerson", person);
        initDropDown(model);
        return "/snviewlogin";
    }

    @RequestMapping("/snlikeredirect")
    public String redirectLike(@RequestParam("id") Long id, final RedirectAttributes redirectAttributes ) {

        String msg = "Liked comment!";
        redirectAttributes.addFlashAttribute("commentpass", msg);

        return "redirect:/snviewprofile/?id=" + previousId;
    }

    @RequestMapping("/snunlikeredirect")
    public String redirectUnlike(@RequestParam("id") Long id, final RedirectAttributes redirectAttributes ) {

        String msg = "Unliked comment!";
        redirectAttributes.addFlashAttribute("commentpass", msg);

        return "redirect:/snviewprofile/?id=" + previousId;
    }


        @RequestMapping("/snviewprofile")
    public ModelAndView viewProfile(@RequestParam("id") Long id ) {

        ModelAndView mav = new ModelAndView("snviewprofile");

        mav.addObject("user", service.getPerson(userId).getName());
        mav.addObject("username", service.getPerson(id).getName());
        mav.addObject("privacy", mgenPrivacyScore(service.getPerson(id)));
        mav.addObject("attributes", mgenAttributeList(service.getPerson(id)));
        mav.addObject("friends", mgenFriendsList(service.getPerson(id)));
        mav.addObject("comments", mgenCommentsList(service.getPerson(id)));
        mav.addObject("groups", mgenGroupsList(service.getPerson(id)));

        previousId = id;



        // IF ON USERS OWN PAGE
        if(userId.equals(id)){
            mav.addObject("home", id);
            previousId = userId;
        }

        return mav;
    }

    @RequestMapping("/snviewpost")
    public ModelAndView viewPost(@RequestParam("id") Long id ) {

        ModelAndView mav = new ModelAndView("snviewpost");

        mav.addObject("user", service.getPerson(userId).getName());

        mav.addObject("username", service.getPerson(service.getComment(id).getOwnerID()).getName());
        mav.addObject("originaltext", service.getComment(id).getText());

        previousCommentId = id;

        return mav;
    }

    @RequestMapping(value = "/submitsession", method = RequestMethod.POST, params={"myprofile"})
    @Transactional
    public String viewHome(){
        return "redirect:/snviewprofile/?id=" + userId;
    }

    @RequestMapping(value = "/snsubmitcomment", method = RequestMethod.POST, params={"add"})
    @Transactional
    public String redirectComment(@RequestParam(value = "inputComment") String t, final RedirectAttributes redirectAttributes){
        boolean created = service.createComment(service.getPerson(userId).getNodeID(), t);
        if(created){
            String msg = "Comment created!";
            redirectAttributes.addFlashAttribute("commentpass", msg);
        }
        else
        {
            String msg = "This comment has already been made. Why don't you say something new?";
            redirectAttributes.addFlashAttribute("commentfail", msg);
        }
        return "redirect:/snviewprofile/?id=" + userId;
    }

    @RequestMapping(value = "/snsubmitreply", method = RequestMethod.POST, params={"reply"})
    @Transactional
    public String redirectReply(@RequestParam(value = "inputComment") String t, final RedirectAttributes redirectAttributes){
        boolean created = service.createReply(service.getPerson(userId).getNodeID(), previousCommentId, t);
        if(created){
            String msg = "Reply sent!";
            redirectAttributes.addFlashAttribute("commentpass", msg);
        }
        else
        {
            String msg = "This comment has already been made. Why don't you say something new?";
            redirectAttributes.addFlashAttribute("commentfail", msg);
        }
        return "redirect:/snviewprofile/?id=" + previousId;
    }

    @RequestMapping(value = "/submitsession", method = RequestMethod.POST, params={"logout"})
    @Transactional
    public String logOut(){ return "redirect:/snviewlogin"; }

    @RequestMapping(value = "/submitlogin", method = RequestMethod.POST, params={"new"})
    @Transactional
    public String deleteData(){ return "redirect:/createuser"; }

    @RequestMapping(value = "/submitlogin", method = RequestMethod.POST, params={"login"})
    @Transactional
    public String showSNSim(@RequestParam(value = "inputPerson") Long pid){
        userId = pid;
        return "redirect:/snviewprofile/?id=" + userId;
    }



//================================================== FUNCTIONAL THINGS ================================================//

    protected void initDropDown(Model model){
        Map<Long, String> peoples = new LinkedHashMap<Long, String>();
        ArrayList<Person> people = service.getAllPersons();
        for(int i = 0; i < people.size(); i++){
            peoples.put(people.get(i).getNodeID(), people.get(i).getName());
        }
        model.addAttribute("peopleList", peoples);
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

    //============================================ MGEN ===============================================================//

    public String mgenPrivacyScore(Person p){
        String html = "";

        double ps = analysisService.getPrivacyScore(service.getPerson(p.getNodeID()));
        html = String.format("<h4>Privacy Score: %.2f</h4>", ps);
        return html;
    }

    public String mgenAttributeList(Person p){
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
        return html;
    }

    public String mgenFriendsList(Person p){
        analysisService.setRoot(service.getPerson(p.getNodeID()));
        String html = "";
        Collection<Person> persons = p.getFriends();
        if(persons.isEmpty()){
            html = p.getName() + " has no friends..  :( ";
        }
        else{
            html = "<h3>Friends:</h3><br><div class=\"list-group\">";
            double thresholdRS = analysisService.getThresholdRS(service.getPerson(p.getNodeID()));
            for(Person ap : persons){
                Long pID = ap.getNodeID();
                double rs = analysisService.getRelationshipStrength(service.getPerson(p.getNodeID()), service.getPerson(pID));
                html = html.concat("<a href=\"" +  "/snviewprofile/?id=" + service.getPerson(pID).getNodeID() + "\" class= \"list-group-item\">" + service.getPerson(pID).getName());
                String format = String.format("%.2f", rs);
                html = html + "<button " + "type=\"button\" " + "class=\"btn btn-sm ";
                if(rs < thresholdRS){
                    html += "btn-warning";
                }else
                    html += "btn-success";
                html = html + "\" style=\"float: right;\">" +
                        "<span aria-hidden=\"true\"></span>Relationship Strength: " + format +
                        "</button>"+"</a>";
            }
            html = html.concat("</div>");
        }
        return html;
    }

    public String mgenCommentsList(Person p){
        String html = "";
        Collection<Comment> comments = p.getComments();
        System.out.println(comments.isEmpty());
        if(comments.isEmpty()){
            html = p.getName() + " has not made any comments yet!";
        }
        else{
            html = "<h3>Comments:</h3><br><div class=\"list-group\">";
            for(Comment ac : comments){
                Long pID = ac.getNodeID();
                if(service.getComment(pID).isRoot()) {
                    html = html + constructReplyThread(service.getComment(ac.getNodeID()).getNodeID(), 0);
                }

            }
            html = html.concat("</div>");
        }
        return html;
    }

    public String constructReplyThread(Long cid, int depth){
        String tab = "<span class=\"tab\"></span>";
        String html = "";

        String likeStr = genLike(cid);
        String replyStr = genReplyBtn(cid);

        String tabs = StringUtils.repeat(tab, depth);
        html = html + "<div class= \"list-group-item\">" + tabs + service.getPerson(service.getComment(cid).getOwnerID()).getName() + ": " + service.getComment(cid).getText() + replyStr + likeStr + "</div>";

        while(service.getComment(cid).hasChildren()){
            Collection<Comment> replies = service.getComment(cid).getReplies();

            for(Comment child : replies){
                int d = depth+1;
                child = service.getComment(child.getNodeID());
                html = html + constructReplyThread(child.getNodeID(), d);
            }
            break;
        }
        return html;
    }


    public String mgenGroupsList(Person p){
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
        return html;
    }

    public String genLike(Long cid){
        String html = "";
        boolean liked = false;
        Collection<Comment> likes = service.getPerson(userId).getLikes();
        if(likes.isEmpty()){
            // It's OK
        }
        else{
            for(Comment ac : likes) {
                if (service.getComment(cid).getNodeID().equals(service.getComment(ac.getNodeID()).getNodeID())) {
                    html = html.concat("<button onclick=\"location.href='/snunlikeredirect/?id=" + service.getComment(cid).getNodeID() +  "'\" " +
                            "type=\"button\" class=\"btn btn-default btn-sm\" style=\"float: right;\">" +
                            "  <span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span> LIKE" +
                            "</button>");
                    liked = true;
                    break;
                }
            }
        }
        if(liked==false){
            html = html + "<button onclick=\"location.href='/snunlikeredirect/?id=" + service.getComment(cid).getNodeID() +  "'\" " +
                    "type=\"button\" class=\"btn btn-default btn-sm\" style=\"float: right;\">" +
                    "  <span class=\"glyphicon glyphicon-star-empty\" aria-hidden=\"true\"></span> LIKE" +
                    "</button>";
        }
        return html;
    }

    public String genReplyBtn(Long cid){
        String html = "";
        html = html + "<button onclick=\"location.href='/snviewpost/?id=" + service.getComment(cid).getNodeID() +  "'\" " +
                " type=\"button\" class=\"btn btn-default btn-sm\" style=\"float: right;\">" +
                "  <span class=\"glyphicon glyphicon-share-alt\" aria-hidden=\"true\"></span> REPLY" +
                "</button>";
        return html;
    }


}

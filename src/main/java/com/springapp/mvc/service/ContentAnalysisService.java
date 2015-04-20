package com.springapp.mvc.service;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Comment;
import com.springapp.mvc.model.HasRelationship;
import com.springapp.mvc.model.Person;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.FieldSelector;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.wordnet.SynExpand;
import org.apache.lucene.wordnet.SynonymMap;
import org.neo4j.cypher.internal.compiler.v1_9.commands.Has;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created by Zachary on 4/4/2015.
 * This service is dedicated to Privacy analysis of content
 */
@Service
@Transactional
public class ContentAnalysisService {

    @Autowired
    PersonService service;

    SynonymMap map;

    public void initMap(){
        try{
            Resource resource = new ClassPathResource("wn_s.pl");
            map = new SynonymMap(resource.getInputStream());
        }catch(FileNotFoundException e){
            System.out.println("PREDICTCONTEXT ERROR: " + e);
        }catch(IOException e){
            System.out.println("PREDICTCONTEXT ERROR: " + e);
        }
    }

    public ArrayList calculateAll(Person root){

        initMap();

        ArrayList<String> messaages = new ArrayList<String>();
        // get and add sentiment analysis messages
        ArrayList<String> sentMsgs = runSentimentAnalysisForPerson(root);
        for(String s : sentMsgs){
            messaages.add(s);
        }

        // get and add content analysis messages
        ArrayList<String> contentMsgs = runContentAnalysisForPerson(root);
        for(String s : contentMsgs){
            messaages.add(s);
        }

        // gen ingoing/outgoing communication charts for user
        genCommunicationChart(root.getNodeID());

        // gen sentiment charts for user and friends
        genSentChart(root.getNodeID());

        return messaages;
    }

    public ArrayList<String> runSentimentAnalysisForPerson(Person root){
        ArrayList<String> messages = new ArrayList<String>() {{
            add("head");
        }};
        Collection<Person> friends = root.getFriends();
        for(Person f : friends){
            int score = 0;
            Collection<Comment> comments = service.getPerson(f.getNodeID()).getComments();
            for(Comment c : comments){
                score = score + c.getSentiment();
                System.out.println("score: " + score);
            }
            String msg = "";
            if(score < -1){
                msg = "Your friend " + service.getPerson(f.getNodeID()).getName() + " generally makes negitive comments/posts. SCORE: " + score;
            }
            else if(score > 1){
                msg = "Your friend " + service.getPerson(f.getNodeID()).getName() + " generally makes positive comments/posts. SCORE: " + score;
            }
            else{
                msg = "Your friend " + service.getPerson(f.getNodeID()).getName() + " generally makes neutral comments/posts. SCORE: " + score;
            }
            messages.add(msg);
        }
        return messages;
    }

    public int getNumericVal(String str){
        int i = 0;
        if(str.contains("Very negative")){
            i = -2;
        }
        if(str.contains("Negative")){
            i = -1;
        }
        if(str.contains("Neutral")){
            i = 0;
        }
        if(str.contains("Positive")){
            i = 1;
        }
        if(str.contains("Very positive")){
            i = 2;
        }
        return i;
    }

    public ArrayList<String> runContentAnalysisForPerson(Person root) {
        ArrayList<String> messages = new ArrayList<String>() {{
            add("head");
        }};

        root = service.getPerson(root.getNodeID());

        // Get comments and NOT replies made by user
        ArrayList<Comment> usersComments = new ArrayList<Comment>();
        Collection<Comment> allComments = service.getAllComments();
        for (Comment c : allComments) {
            c = service.getComment(c.getNodeID());
            if ((c.getOwnerID().equals(root.getNodeID()))&&(c.isRoot())) {
                usersComments.add(c);
            }
        }

        // Get users has relationships
        Collection<HasRelationship> hasRels = root.getAttributeRelationships();

        // iterate though all relationships and see if any attributes are mentioned in any comments
        for (HasRelationship h : hasRels) {
            h = service.getHasRelationship(h.getId());
            Attribute a = service.getAttributeWithId(h.getEnd().getNodeID());
            String label = a.getLabel().toLowerCase();
            String value = a.getValue().toLowerCase();
            for (Comment c : usersComments) {
                String commentText = service.getComment(c.getNodeID()).getText();
                String normalized = commentText.replaceAll("[!?,{}()_+-=]", "");
                normalized = normalized.toLowerCase();
                String[] words = normalized.split("\\s+");
                boolean lfound = false;
                boolean vfound = false;
                for(String w : words){
                    if (w.equals(label)) {
                        lfound = true;
                    }
                    if (w.equals(value)) {
                        vfound = true;
                    }
                    //System.out.println(label + " " + value + " " + vfound + " " + lfound);
                }
                if((lfound) && (!label.equals("interest"))){
                    if(h.getVv() < 2){
                        messages.add("You gave the attribute " + label +
                                " a low visibility score, but you talk about it in the comment: \"" +
                                commentText + "\"<br>Visibility Value: " + h.getVv());
                    }
                    if(h.getSv() > 2){
                        messages.add("You gave the attribute " + label +
                                " a high sensitivity score, but you talk about it in the comment: \"" +
                                commentText + "\"<br>Sensitivity Value: " + h.getSv());
                    }
                    if(h.getPv() > 2){
                        messages.add("You gave the attribute " + label +
                                " a high privacy score, but you talk about it in the comment: \"" +
                                commentText + "\"<br>Privacy Value: " + h.getPv());
                    }
                }
                if(vfound){
                    String type = "";
                    if(label.equals("interest")) {
                        type = "interest";
                    } else{
                        type = "attribute";
                    }
                    if(h.getVv() < 2){
                        messages.add("You gave the " + type + " " + value +
                                " a low visibility score, but you talk about it in the comment: \"" +
                                commentText + "\"<br>Visibility Value: " + h.getVv());
                    }
                    if(h.getSv() > 2){
                        messages.add("You gave the " + type + " " + value +
                                " a high sensitivity score, but you talk about it in the comment: \"" +
                                commentText + "\"<br>Sensitivity Value: " + h.getSv());
                    }
                    if(h.getPv() > 2){
                        messages.add("You gave the " + type + " " + value +
                                " a high privacy score, but you talk about it in the comment: \"" +
                                commentText + "\"<br>Privacy Value: " + h.getPv());
                    }
                }
                // make predictions on your own comment
                if(h.getVv()==0){
                    messages.add(predictOwnContext(commentText, label));
                    messages.add(predictOwnContext(commentText, value));
                }
            }
        }

        // add the analysis of other peoples comments/replies mentioning the owners invisible attributes
        messages.addAll(predictAttributeValueFromComment(root));

        return messages;
    }

    public int runSentimentAnalysisForText(String text){
        int score = 0;
        if(text != null){
            Properties props = new Properties();
            props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
            StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

            Annotation annotation = pipeline.process(text);
            List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
            for (CoreMap sentence : sentences) {
                String result = sentence.get(SentimentCoreAnnotations.ClassName.class);
                score = score + getNumericVal(result);
            }
        }
        else{
            System.out.println("SntAnlysSrvc: Comment was null!");
        }

        return score;
    }

    /**
     * For a comment m on a person p's post c, predict the value of an attribute dk from his profile
     * based on NLP Analysis of:
     * 1. the text in the comment (look for keywords correlated with pronouns)
     * 2. the person p2 who posted the comment
     * 3. other related attributes e.g. date of the post or the comment (relate that with birthday
     */
    public ArrayList<String> predictAttributeValueFromComment(Person p){
        ArrayList<String> messages = new ArrayList<String>() {{
            add("head");
        }};
        String msg = "";
        //case1: dk = age visibility = 0
        //text in the comment: keyword = birthday, pronoun = your
        //date of post = april 13
        //if the year is hidden, do more analysis on other attributes e.g., what year they joined college or attended high school

        //case2: dk=political view with visibility =0
        // from text in comments in group discussions
        //relate teh name of the group  (political)
        //relate negative vs. positive sentiment
        //names of politicians mentioned

        //case3:  dk = location/address visibility 0
        //text in POST : keword= Detroit, pronoun I am in
        //text in comment : keword= Detroit, pronoun you are in, day: today
        //date : over a time frame (you are visiting in summer vs. you live here)

        p = service.getPerson(p.getNodeID());
        String predictMsg = "";

        // we need to get a collection of attributes that have visibility set to 0
        ArrayList<Attribute> invisibleAtts = new ArrayList<Attribute>();
        Collection<HasRelationship> hasRels = p.getAttributeRelationships();
        for(HasRelationship h : hasRels){
            h = service.getHasRelationship(h.getId());
            if(h.getVv()==0){
                invisibleAtts.add(service.getAttributeWithId(h.getEnd().getNodeID()));
            }
        }

        // if the user has no invisible attributes, the next part can be skipped
        if(!invisibleAtts.isEmpty()){

            // the users own comments are already checked for sensitive information, so in this case
            // we need to iterate through all comments and replies that are NOT the owners.
            Collection<Comment> allComments = service.getAllComments();
            //Collection<Comment> ownersComments = p.getComments();

            for(Comment c : allComments){
                // skip the owners comments and don't check root posts
                c = service.getComment(c.getNodeID());
                if((!c.getOwnerID().equals(p.getNodeID()))&&(!c.isRoot())){
                    // iterate through invisible attributes
                    for(Attribute a : invisibleAtts){
                        a = service.getAttributeWithId(a.getNodeID());
                        String label = a.getLabel().toLowerCase();
                        String value = a.getValue().toLowerCase();
                        String commentText = c.getText();
                        String normalized = commentText.replaceAll("[!?,{}()_+-=]", "");
                        normalized = normalized.toLowerCase();
                        String[] words = normalized.split("\\s+");
                        boolean lfound= false;
                        boolean vfound= false;
                        for(String w : words){
                            if(w.equals(label)){
                                lfound = true;
                            }
                            if(w.equals(value)){
                                vfound = true;
                            }
                        }
                        System.out.println(vfound + " " + lfound);
                        if(lfound){
                            msg = service.getPerson(c.getOwnerID()).getName() + "'s reply to one of your comments: \"" + c.getText() +
                                    "\" indirectly mentions the attribute \"" + a.getLabel() + "\" that you set as invisible to others.";
                            messages.add(msg);
                        }
                        if(vfound){
                            String type = "";
                            if (label.equals("interest")) {
                                type = "interest";
                            } else {
                                type = "attribute";
                            }
                            msg = service.getPerson(c.getOwnerID()).getName() + "'s reply to one of your comments: \"" + c.getText() +
                                    "\" directly mentions the "+type+" \"" + a.getLabel() + "\" that you set as invisible to others.";
                            messages.add(msg);
                        }
                        predictMsg = predictContext(c.getText(), a.getLabel(), service.getPerson(c.getOwnerID()).getName());
                        if(!predictMsg.equals("")){
                            messages.add(predictMsg);
                        }
                        predictMsg = predictContext(c.getText(), a.getValue(), service.getPerson(c.getOwnerID()).getName());
                        if(!predictMsg.equals("")){
                            messages.add(predictMsg);
                        }

                    }

                }
            }
        }

        return messages;
    }

    public double getRootOutgoingVal(Long pid, Long fid){
        double val = 0;
        double totalInterations = 0;
        for(Comment c1 : service.getPerson(pid).getLikes()){
            for(Comment c2 : service.getPerson(fid).getLikes()) {
                if(service.getComment(c1.getNodeID()).getNodeID().equals(service.getComment(c2.getNodeID()).getNodeID())){
                    totalInterations = totalInterations + 1;
                    val = val + 1;
                }
            }
        }
        for(Comment c1 : service.getPerson(fid).getLikes()){
            for(Comment c2 : service.getPerson(pid).getLikes()) {
                if(service.getComment(c1.getNodeID()).getNodeID().equals(service.getComment(c2.getNodeID()).getNodeID())){
                    totalInterations = totalInterations + 1;
                }
            }
        }
        for(Comment c1 : service.getPerson(pid).getComments()){
            for(Comment c2 : service.getPerson(fid).getComments()) {
                if(!service.getComment(c1.getNodeID()).isRoot()){
                    if(service.getComment(service.getComment(c1.getNodeID()).getParentID()).getNodeID().equals(service.getComment(c2.getNodeID()).getNodeID())){
                        totalInterations = totalInterations + 1;
                        val = val + 1;
                    }
                }
            }
        }
        for(Comment c1 : service.getPerson(fid).getComments()){
            for(Comment c2 : service.getPerson(pid).getComments()) {
                if(!service.getComment(c1.getNodeID()).isRoot()) {
                    if (service.getComment(service.getComment(c1.getNodeID()).getParentID()).getNodeID().equals(service.getComment(c2.getNodeID()).getNodeID())) {
                        totalInterations = totalInterations + 1;
                    }
                }
            }
        }
        val = val / totalInterations;
        return val;
    }

    public void genCommunicationChart(Long pid){
        String msg = "";
        String nomsg = "<center>";
        int counter = 0;
        String id;
        Collection<Person> persons = service.getAllPersons();
        for(Person person : persons){
            if(!(person.getNodeID().equals(pid))) {
                person = service.getPerson(person.getNodeID());
                double outgoing = getRootOutgoingVal(pid, person.getNodeID());
                System.out.println("outval:" + outgoing);
                id = "cfid" + Integer.toString(counter);
                counter = counter + 1;
                String friendStr = "";
                if(service.areFriends(person, service.getPerson(pid))){
                    friendStr = "[Friend]";
                }
                else{
                    friendStr = "[Non-friend]";
                }
                if (Double.isNaN(outgoing)) {
                    //nomsg = nomsg + "No interactions have been made with the person " + person.getName() + " as of yet! "+friendStr+"<br>";
                } else {
                    msg = msg + "<div id=\"chart" + id + "\"></div>" +
                            "<script language=\"JavaScript\">" +
                            "var chart" + id + " = c3.generate({\n" +
                            "bindto: '#chart" + id + "'," +
                            "    data: {\n" +
                            "        selection: {" +
                            "           enabled: true" +
                            "        }," +
                            "        columns: [\n" +
                            "            ['Outgoing', " + Double.toString(outgoing) + "],\n" +
                            "            ['Incoming', " + Double.toString(1.0 - outgoing) + "],\n" +
                            "        ],\n" +
                            "        type : 'donut',\n" +
                            "        onclick: function (d, i) { console.log(\"onclick\", d, i); },\n" +
                            "        onmouseover: function (d, i) { console.log(\"onmouseover\", d, i); },\n" +
                            "        onmouseout: function (d, i) { console.log(\"onmouseout\", d, i); }\n" +
                            "    },\n" +
                            "    donut: {\n" +
                            "        title: \"" + person.getName() + "\"\n" +
                            "    }\n" +
                            "});\n" +
                            "var label = d3.select('#chart" + id + "').select('text.c3-chart-arcs-title');\n" +
                            "label.html('');\n" +
                            "label.insert('tspan').text('" + person.getName() + "').attr('dy',0).attr('x', 0).attr('class','big-font');\n" +
                            "label.insert('tspan').text('" + friendStr + "').attr('dy',20).attr('x', 0).attr('class','small-font');\n" +
                            "</script>";
                }
            }
        }

        msg = nomsg + msg + "</center>";
        Person p = service.getPerson(pid);
        service.setCommunicationCharts(p.getNodeID(), msg);
    }

    public String predictContext(String text, String word, String owner){
        String original = text;
        String msg = "";
        word = word.toLowerCase();
        text = text.toLowerCase();

        String synonyms[] = map.getSynonyms(word);

        System.out.println("analyzing context for: " + word);
        //System.out.println(Arrays.asList(synonyms).toString());

        ArrayList<String> synsFound = new ArrayList<String>();

        for(int i=0; i < synonyms.length; i++){
            if(text.contains(synonyms[i])){
                synsFound.add(synonyms[i].toString());
            }
        }

        double conf = 0;

        //System.out.println(synsFound);

        ArrayList<String> pronouns = new ArrayList<String>();
        pronouns.add("you");
        pronouns.add("your");
        pronouns.add("yourself");
        pronouns.add("our");
        pronouns.add("ours");
        pronouns.add("yours");

        ArrayList<String> pronounsFound = new ArrayList<String>();

        for(String s : pronouns){
            if(text.contains(s)){
                pronounsFound.add(s);
            }
        }

        if(synsFound.isEmpty()){
            msg="";
        }
        else{
            conf = conf + (20*synsFound.size());
            if(!pronounsFound.isEmpty()){
                conf = conf + (30*pronounsFound.size());
                for(String s : pronounsFound){
                    synsFound.add(s);
                }
            }
            String flaggedWords = "";
            for(String s : synsFound){
                flaggedWords = flaggedWords + "[ " + s + " ] ";
            }

            msg = "The reply made by " + owner + " to one of your posts: \"" + original + "\" seems to be referring to your attribute \"" + word +
                    "\" that you have set invisible to others. Flagged words: " + flaggedWords + "Confidence: " + Double.toString(conf) + "%";
        }

        return msg;
    }

    public String predictOwnContext(String text, String word){
        String original = text;
        String msg = "";

        String synonyms[] = map.getSynonyms(word);

        System.out.println("analyzing context for: " + word);
        //System.out.println(Arrays.asList(synonyms).toString());

        ArrayList<String> synsFound = new ArrayList<String>();

        for(int i=0; i < synonyms.length; i++){
            if(text.contains(synonyms[i])){
                synsFound.add(synonyms[i].toString());
            }
        }

        double conf = 0;

        //System.out.println(synsFound);

        ArrayList<String> pronouns = new ArrayList<String>();
        pronouns.add("me");
        pronouns.add("i");
        pronouns.add("im");
        pronouns.add("my");
        pronouns.add("myself");
        pronouns.add("our");
        pronouns.add("ours");
        pronouns.add("mine");

        ArrayList<String> pronounsFound = new ArrayList<String>();

        for(String s : pronouns){
            if(text.contains(s)){
                pronounsFound.add(s);
            }
        }

        if(synsFound.isEmpty()){
            msg="";
        }
        else{
            conf = conf + (20*synsFound.size());
            if(!pronounsFound.isEmpty()){
                conf = conf + (30*pronounsFound.size());
                for(String s : pronounsFound){
                    synsFound.add(s);
                }
            }
            String flaggedWords = "";
            for(String s : synsFound){
                flaggedWords = flaggedWords + "[ " + s + " ] ";
            }

            msg = "Your own comment : \"" + original + "\" seems to be referring to your attribute \"" + word +
                    "\" that you have set invisible to others. Flagged words: " + flaggedWords + "Confidence: " + Double.toString(conf) + "%";
        }

        return msg;
    }

    public void genSentChart(Long pid){
        String msg = "";
        String nomsg = "<center>";
        int counter = 0;
        String id;
        Person p = service.getPerson(pid);
        Collection<Person> friends = p.getFriends();
        friends.add(p);
        for(Person friend : friends){
            friend = service.getPerson(friend.getNodeID());
            ArrayList<Double> svals = getSentVals(friend.getNodeID());
            id = "csid" + Integer.toString(counter);
            counter = counter + 1;
            String friendStr = "";
            if(!friend.getNodeID().equals(p.getNodeID())){
                friendStr = "[Friend]";
            }
            else{
                friendStr = "[You]";
            }
            double sum = 0;
            for(Double d : svals){
                sum = sum + d;
            }
            System.out.println("================= sum: " + sum);
            if (sum==0.0) {
                //nomsg = nomsg + "No interactions have been made with the person " + person.getName() + " as of yet! "+friendStr+"<br>";
            } else {
                msg = msg + "<div id=\"chart" + id + "\"></div>" +
                        "<script language=\"JavaScript\">" +
                        "var chart" + id + " = c3.generate({\n" +
                        "bindto: '#chart" + id + "'," +
                        "    data: {\n" +
                        "        selection: { \n" +
                        "        enabled: true" +
                        "        }, \n" +
                        "        columns: [\n" +
                        "            ['Very Negative', " + Double.toString(svals.get(0)) + "],\n" +
                        "            ['Negative', " + Double.toString(svals.get(1)) + "],\n" +
                        "            ['Neutral', " + Double.toString(svals.get(2)) + "],\n" +
                        "            ['Positive', " + Double.toString(svals.get(3)) + "],\n" +
                        "            ['Very Positive', " + Double.toString(svals.get(4)) + "],\n" +
                        "        ],\n" +
                        "        type : 'donut',\n" +
//                        "        colors: { \n" +
//                        "            Very Negative: '#700000', \n" +
//                        "            Negative: '#FF0000', \n" +
//                        "            Neutral: '#FFFF00' \n" +
//                        "            Positive: '#00FF00' \n" +
//                        "            Very Positive: '#006600' \n" +
//                        "        }, \n" +
                        "        onclick: function (d, i) { console.log(\"onclick\", d, i); },\n" +
                        "        onmouseover: function (d, i) { console.log(\"onmouseover\", d, i); },\n" +
                        "        onmouseout: function (d, i) { console.log(\"onmouseout\", d, i); }\n" +
                        "    },\n" +
                        "    donut: {\n" +
                        "        title: \"" + friend.getName() + "\"\n" +
                        "    }\n" +
                        "});\n" +
                        "var label = d3.select('#chart" + id + "').select('text.c3-chart-arcs-title');\n" +
                        "label.html('');\n" +
                        "label.insert('tspan').text('" + friend.getName() + "').attr('dy',0).attr('x', 0).attr('class','big-font');\n" +
                        "label.insert('tspan').text('" + friendStr + "').attr('dy',20).attr('x', 0).attr('class','small-font');\n" +
                        "</script>";
            }
        }

        msg = nomsg + msg + "</center>";
        service.setSentCharts(p.getNodeID(), msg);
    }

    public ArrayList<Double> getSentVals(Long pid){
        ArrayList<Double> vals = new ArrayList<>();

        double n2 = 0;
        double n1 = 0;
        double n = 0;
        double p1 = 0;
        double p2 = 0;

        Person p = service.getPerson(pid);
        Collection<Comment> allcomments = service.getAllComments();
        for(Comment c : allcomments){
            c = service.getComment(c.getNodeID());
            if(c.getOwnerID().equals(p.getNodeID())){
                if(c.getSentiment()==-2){
                    n2 = n2 + 1;
                }
                if(c.getSentiment()==-1){
                    n1 = n1 + 1;
                }
                if(c.getSentiment()==0){
                    n = n + 1;
                }
                if(c.getSentiment()==1){
                    p1 = p1 + 1;
                }
                if(c.getSentiment()==2){
                    p2 = p2 + 1;
                }
            }
        }

        vals.add(n2);
        vals.add(n1);
        vals.add(n);
        vals.add(p1);
        vals.add(p2);

        return vals;
    }
}

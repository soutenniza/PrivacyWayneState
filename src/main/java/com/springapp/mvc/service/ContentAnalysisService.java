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
import org.neo4j.cypher.internal.compiler.v1_9.commands.Has;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Created by Zachary on 4/4/2015.
 * This service is dedicated to Privacy analysis of content
 */
@Service
@Transactional
public class ContentAnalysisService {

    @Autowired
    PersonService service;

    public ArrayList calculateAll(Person root){
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

        // get the root persons comments and attributes
        Collection<Attribute> attributes = service.getPerson(root.getNodeID()).getAttributes();
        Collection<Comment> comments = service.getPerson(root.getNodeID()).getComments();

        // iterate through all of the persons comments
        for(Comment c : comments){
            String text = service.getComment(c.getNodeID()).getText();
            // for each comment, iterate through each attribute
            for(Attribute a : attributes){
                a = service.getAttributeWithId(a.getNodeID());
                // if the comment text contains the attribute label or value
                if((text.contains(a.getLabel()))||((text.contains(a.getValue())))){
                    Person person = service.getPerson(root.getNodeID());
                    // get the persons has relationships to check for sensitive information
                    Collection<HasRelationship> relationships = person.getAttributeRelationships();
                    // iterate through each has relationship
                    for(HasRelationship r : relationships){
                        r = service.getHasRelationship(r.getId());
                        Attribute aa = service.getAttributeWithId(r.getEnd().getNodeID());
                        // skip the label interest
                        if(!aa.getLabel().contains("interest")){
                            // identify if the attribute was marked as sensitive
                            if((aa.getLabel().contains(a.getLabel()))){
                                if(r.getPv()>2){
                                    messages.add("You gave the attribute " + aa.getLabel() + " a high privacy score, but you talk about it in a comment. PV: " + r.getPv());
                                }
                                if(r.getVv()<2){
                                    messages.add("You gave the attribute " + aa.getLabel() + " a low visibility score, but you talk about it in a comment. VV: " + r.getVv());
                                }
                                if(r.getSv()>2){
                                    messages.add("You gave the attribute " + aa.getLabel() + " a high sensitivity score, but you talk about it in a comment. SV: " + r.getSv());
                                }
                            }
                        }
                        // do the same for values
                        if((aa.getValue().contains(a.getValue()))){
                            if(r.getPv()>2){
                                messages.add("You gave the attribute " + aa.getValue() + " a high privacy score, but you mentioned it in a comment. PV: " + r.getPv());
                            }
                            if(r.getVv()<2){
                                messages.add("You gave the attribute " + aa.getValue() + " a low visibility score, but you mentioned it in a comment. VV: " + r.getVv());
                            }
                            if(r.getSv()>2){
                                messages.add("You gave the attribute " + aa.getValue() + " a high sensitivity score, but you mentioned it in a comment. SV: " + r.getSv());
                            }
                        }
                    }
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
            Collection<Comment> ownersComments = p.getComments();
            for(Comment c : allComments){
                // skip the owners comments
                if(!ownersComments.contains(c)){
                    c = service.getComment(c.getNodeID());
                    // iterate through invisible attributes
                    for(Attribute a : invisibleAtts){
                        if(c.getText().contains(" "+a.getLabel()+" ")){
                            msg = service.getPerson(c.getOwnerID()).getName() + "'s comment: \"" + c.getText() +
                                    "\" indirectly mentions the attribute \"" + a.getLabel() + "\" that you set as invisible to others.";
                            messages.add(msg);
                        }
                        if(c.getText().contains(" "+a.getValue()+" ")){
                            msg = service.getPerson(c.getOwnerID()).getName() + "'s comment: \"" + c.getText() +
                                    "\" directly mentions the attribute \"" + a.getLabel() + "\" that you set as invisible to others.";
                            messages.add(msg);
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

}

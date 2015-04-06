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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Created by Zachary on 4/4/2015.
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
        Collection<Attribute> attributes = service.getPerson(root.getNodeID()).getAttributes();
        Collection<Comment> comments = service.getPerson(root.getNodeID()).getComments();
        for(Comment c : comments){
            String text = service.getComment(c.getNodeID()).getText();
            for(Attribute a : attributes){
                a = service.getAttributeWithId(a.getNodeID());
                if((text.contains(a.getLabel()))||((text.contains(a.getValue())))){
                    Person person = service.getPerson(root.getNodeID());
                    Collection<HasRelationship> relationships = person.getAttributeRelationships();
                    for(HasRelationship r : relationships){
                        r = service.getHasRelationship(r.getId());
                        Attribute aa = service.getAttributeWithId(r.getEnd().getNodeID());
                        if((aa.getLabel().contains(a.getLabel()))){
                            if(r.getPv()>2){
                                messages.add("You gave the attribute " + aa.getLabel() + " a high privacy score, but you talk about it in a comment. PV: " + r.getPv());
                            }
                            if(r.getVv()>2){
                                messages.add("You gave the attribute " + aa.getLabel() + " a high visibility score, but you talk about it in a comment. VV: " + r.getVv());
                            }
                            if(r.getSv()>2){
                                messages.add("You gave the attribute " + aa.getLabel() + " a high sensitivity score, but you talk about it in a comment. SV: " + r.getSv());
                            }
                        }
                        if((aa.getValue().contains(a.getValue()))){
                            if(r.getPv()>2){
                                messages.add("You gave the attribute " + aa.getValue() + " a high privacy score, but you mentioned it in a comment. PV: " + r.getPv());
                            }
                            if(r.getVv()>2){
                                messages.add("You gave the attribute " + aa.getValue() + " a high visibility score, but you mentioned it in a comment. VV: " + r.getVv());
                            }
                            if(r.getSv()>2){
                                messages.add("You gave the attribute " + aa.getValue() + " a high sensitivity score, but you mentioned it in a comment. SV: " + r.getSv());
                            }
                        }
                    }
                }
            }
        }

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

}

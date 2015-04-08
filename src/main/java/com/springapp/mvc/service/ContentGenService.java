package com.springapp.mvc.service;

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Comment;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by Zachary on 4/4/2015.
 * This service is dedicated to anything related to content generation
 */
@Service
@Transactional
public class ContentGenService {

    @Autowired
    PersonService service;

    @Autowired
    ContentAnalysisService contentAnalysisService;

    ArrayList<String> commentNeg = new ArrayList<String>() {{
        add("I am mad and full of rage!");
        add("I feel violent and sassy!");
        add("I cry all the time.");
        add("You are ugly and not good.");
        add("Everyone smells bad.");
        add("I am mad. Someone stole my car.");
        add("I am going to jail next tuesday.");
        add("Wow did you hear of that murder?");
        add("I hate stuff!!!");
        add("Black rain mud sadness!");
        add("He is bad evil man who smells and has guns.");
    }};

    ArrayList<String> commentPos = new ArrayList<String>() {{
        add("I am happy!");
        add("I love life!");
        add("I laugh all of the time.");
        add("You are beautiful!");
        add("Happy flowers and sunshine.");
        add("I love you?");
        add("I am going to the park to have fun.");
        add("Wow did you hear about that party?");
        add("I love stuff!!!");
        add("Blue skies make me feel good.");
        add("She is a nice and good girl who is kind.");
    }};

    ArrayList<String> commentNeu = new ArrayList<String>() {{
        add("I have a shirt on.");
        add("Water is wet.");
        add("Today is Monday.");
        add("The earth is round.");
        add("I have a hat on.");
        add("Hey!");
        add("The number 100.");
        add("What time is it?");
        add("Hello!!!");
        add("The cat is here.");
        add("Today is the third day of the month.");
    }};

    ArrayList<String> reply = new ArrayList<String>() {{
        add("I agree.");
        add("I disagree.");
        add("I strongly disagree!.");
        add("I strongly agree!.");
        add("You are wrong about this. I don't like you.");
        add("You are right about this. I like you.");
        add("This made me laugh.");
        add("This made me cry.");
        add("Who cares?");
        add("I care about this.");
        add("LOL!!");
    }};


    public void addComments(Long pid){

        // 0 - attribute comment
        // 1 - negative comment
        // 2 - neutral comment
        // 3 - positive comment

        int path = getVal(3);
        int numComments = (getVal(3)+1);
        int count;
        Comment c;
        String text;
        if(path == 0){
            for(count = 0; count < numComments; count++){
                text = getAtt(pid);
                service.createComment(pid, text);
            }
        }
        if(path == 1){
            for(count = 0; count < numComments; count++){
                c = new Comment();
                text = getNeg();
                service.createComment(pid, text);
            }
        }
        if(path == 2){
            for(count = 0; count < numComments; count++){
                c = new Comment();
                text = getPos();
                service.createComment(pid, text);
            }
        }
        if(path == 3){
            for(count = 0; count < numComments; count++) {
                c = new Comment();
                text = getNeu();
                service.createComment(pid, text);
            }
        }
    }

    public String getNeg(){
        return commentNeg.get(getVal(commentNeg.size()));
    }

    public String getPos(){
        return commentPos.get(getVal(commentPos.size()));
    }

    public String getNeu(){
        return commentNeu.get(getVal(commentNeu.size()));
    }

    public String getRep(){
        return reply.get(getVal(reply.size()));
    }

    public String getAtt(Long pid){
        String msg = "";

        Person p = service.getPerson(pid);
        Collection<Attribute> atts = p.getAttributes();

        int rando = getVal(atts.size());
        int count = 0;
        String attStrL = "";
        String attStrV = "";

        for(Attribute a : atts){
            if(count == rando){
                attStrL = service.getAttributeWithId(a.getNodeID()).getLabel();
                attStrV = service.getAttributeWithId(a.getNodeID()).getValue();
                break;
            }
            count++;
        }

        msg = "I just wanted to publicly say that my " + attStrL + " is " + attStrV + ".";

        return msg;
    }

    public int getVal(int max){
        Random gen = new Random();
        int i = gen.nextInt(max);
        return i;
    }

    public void addLike(Long pid){
        Collection<Comment> comments = service.getAllComments();
        int rando = getVal(comments.size());
        int count = 0;

        for(Comment c : comments){
            if(count == rando){
                if(service.likesComment(service.getComment(c.getNodeID()), service.getPerson(pid))){
                    System.out.println("ContentGen: [WARN] Tried to like something more than once.");
                }
                else
                {
                    service.addLike(service.getComment(c.getNodeID()), service.getPerson(pid));
                }
                break;
            }
            count++;
        }
    }

    public void addReply(Long pid){
        Collection<Comment> comments = service.getAllComments();
        int rando = getVal(comments.size());
        int count = 0;
        for(Comment c : comments){
            if(count == rando){
                service.createReply(service.getPerson(pid).getNodeID(), service.getComment(c.getNodeID()).getNodeID(), getRep());
                break;
            }
            count++;
        }
    }

    public void addLikes(Long pid){
        int numLikes = (getVal(3)+1);
        for(int x = 0; x < numLikes; x++){
            addLike(pid);
        }

    }

    public void addReplies(Long pid){
        int numReplies = (getVal(3)+1);
        for(int x = 0; x < numReplies; x++){
            addReply(pid);
        }
    }
}

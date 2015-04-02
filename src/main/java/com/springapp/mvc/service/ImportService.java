package com.springapp.mvc.service;

/**
 * Created by Zack on 4/2/15.
 */

import com.springapp.mvc.model.Attribute;
import com.springapp.mvc.model.Group;
import com.springapp.mvc.model.Person;
import com.springapp.mvc.repository.GroupRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;


/**
 * Created by Zachary on 3/1/2015.
 */

@Service
@Transactional
public class ImportService {

    @Autowired
    PersonService service;

    @Autowired
    GroupRepository groupRepository;



    private static final String JSON_PATH = "http://zackrzot.com/data.json";

    public boolean importFromJSON() {

        boolean worked = false;

        System.out.println("Import: Starting import.");

        String name;
        String gender;
        String age;
        String birthday;
        String politicalView;
        String location;
        String phone;
        String education;
        String work;
        String interest;
        String friend;

        try{
            InputStreamReader reader = new InputStreamReader(new URL(JSON_PATH).openStream(), "UTF-8");
            System.out.println("Import: File loaded.");
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray users = (JSONArray) jsonObject.get("user");

            // Add the users to the database
            for (int i = 0; i < users.size(); i++) {
                System.out.println("Import: Importing person " + Integer.toString((i+1)) + "." );
                JSONObject user = (JSONObject) users.get(i);
                name = (String) user.get("name");
                gender = (String) user.get("gender");
                age = (String) user.get("age");
                birthday = (String) user.get("birthday");
                politicalView = (String) user.get("political_view");
                location = (String) user.get("location");
                phone = (String) user.get("phone");
                education = (String) user.get("education");
                work = (String) user.get("work");

                // add the user to the network
                System.out.println("Import: Adding user to db.");
                if(service.personExists(name)){
                    System.out.println("Import: Tried to import an existing user.");
                }
                else {
                    Person p = service.createPerson(name);
                    System.out.println("Import: Person created.");
                    Attribute attAge = service.createAttribute("age", age);
                    service.addAttribute(attAge, p, getVal(), getVal(), getVal());
                    System.out.println("Import: Attribute created.");
                    Attribute attGender = service.createAttribute("gender", gender);
                    service.addAttribute(attGender, p, getVal(), getVal(), getVal());

                    Attribute attLocation = service.createAttribute("location", location);
                    service.addAttribute(attLocation, p, getVal(), getVal(), getVal());

                    Attribute attPolitical = service.createAttribute("political view", politicalView);
                    service.addAttribute(attPolitical, p, getVal(), getVal(), getVal());

                    Attribute attWork = service.createAttribute("work", work);
                    service.addAttribute(attWork, p, getVal(), getVal(), getVal());

                    Attribute attEdu = service.createAttribute("education", education);
                    service.addAttribute(attEdu, p, getVal(), getVal(), getVal());

                    Attribute attBd = service.createAttribute("birthday", birthday);
                    service.addAttribute(attBd, p, getVal(), getVal(), getVal());

                    Attribute attPh = service.createAttribute("phone number", phone);
                    service.addAttribute(attPh, p, getVal(), getVal(), getVal());
                    System.out.println("Import: Adding user interests.");
                    // add interests
                    JSONArray interests = (JSONArray) user.get("interests");
                    for (int x = 0; x < interests.size(); x++) {
                        interest = (String) interests.get(x);
                        Attribute att = service.createAttribute("interest", interest);
                        service.addAttribute(att, p, getVal(), getVal(), getVal());
                    }
                }
            }

            System.out.println("Import: Adding friends.");
            // Add friend relationships
            for (int i = 0; i < users.size(); i++) {
                JSONObject user = (JSONObject) users.get(i);
                String userS = (String) user.get("name");
                JSONArray userFriends = (JSONArray) user.get("friends");
                for(int j=0; j<userFriends.size(); j++){
                    friend = (String) userFriends.get(j);

                    Long p1 = service.getPersonByName(userS);
                    Long p2 = service.getPersonByName(friend);

                    if((service.areFriends(service.getPerson(p1),service.getPerson(p2)))&&(p1!=p2)) {
                        System.out.println("Import: Users are already friends.");
                    }
                    else
                    {
                        service.addFriend(service.getPerson(p1), service.getPerson(p2));
                        service.addFriend(service.getPerson(p2), service.getPerson(p1));
                    }
                }
            }

            System.out.println("Import: Adding groups.");
            // Add users to / create groups

            for(int i=0; i<users.size(); i++) {
                JSONObject user = (JSONObject) users.get(i);
                String nameS = (String) user.get("name");
                JSONArray userGroups = (JSONArray) user.get("groups");
                for(int j=0; j<userGroups.size(); j++){
                    String groupS = (String) userGroups.get(j);

                    if(service.groupExists(groupS)){
                        System.out.println("Import: Group exists.");
                    }
                    else{
                        Group group = new Group();
                        group.setName(groupS);
                        groupRepository.save(group);

                        Long gid = group.getNodeID();

                        Long p1 = service.getPersonByName(nameS);

                        if(service.isMember(service.getGroup(gid), service.getPerson(p1))){
                            System.out.println("Import: Already member of group.");
                        }
                        else
                        {
                            service.addMember(service.getGroup(gid), service.getPerson(p1));
                        }
                    }
                }
            }
        }
        catch(IOException e){e.printStackTrace(); return false; }
        catch (org.json.simple.parser.ParseException e){e.printStackTrace(); return false; }

        return true;
    }


    public int getVal(){
        Random gen = new Random();
        int i = gen.nextInt(4);
        return i;
    }
}
package com.example.androidknomar.util;

import com.example.androidknomar.R;
import com.example.androidknomar.model.Tweet;
import com.example.androidknomar.model.User;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marchal.vincent on 25/01/14.
 */
public class UserParser extends AbstractParser{

    private static final String CONST_URL = "http://pagesperso-systeme.lip6.fr/Etienne.Renault/androidtweets/users.xml";

    private List<User> listUser;
    private User currentUser;
    private String currentElem = "";


    public UserParser() throws Exception
    {
        super(new URI(CONST_URL));
    }

    @Override
    void parseSource(XmlPullParser xmlPullParser) throws Exception {
        int eventType = 0;
        try {
            eventType = xmlPullParser.getEventType();

            do {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT :
                        listUser = new ArrayList<User>();
                        break;

                    case XmlPullParser.END_DOCUMENT :
                        System.out.println("End file");
                        break;

                    case XmlPullParser.START_TAG :
                        currentElem = xmlPullParser.getName();
                        if(currentElem.equals(User.CONST_USER))
                            currentUser = new User();
                        break;

                    case XmlPullParser.END_TAG :
                        currentElem = "";
                        if(xmlPullParser.getName().equals(Tweet.CONST_TWEET)){
                            listUser.add(currentUser);
                            currentUser = null;
                        }
                        break;

                    case XmlPullParser.TEXT :
                        parseText(xmlPullParser);
                        break;

                    default :
                        System.out.println("End file");
                        break;
                }
                eventType = xmlPullParser.next();

            } while (eventType != xmlPullParser.END_DOCUMENT);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error Parsing : " + e.getMessage());
        }
    }

    public void parseText(XmlPullParser xmlPullParser) throws Exception {
        try {
            String value = xmlPullParser.getText();
            if(currentElem.equals(User.CONST_NAME)){
                currentUser.setName( value.replaceAll("\n+",""));
            }else if(currentElem.equals("url")){
                currentUser.setUri( new URI(value.replaceAll("\\s+","")) );
            }
        } catch (Exception e) {
            throw new Exception("Error Parsing user : " + currentUser.getName());

        }
    }

}
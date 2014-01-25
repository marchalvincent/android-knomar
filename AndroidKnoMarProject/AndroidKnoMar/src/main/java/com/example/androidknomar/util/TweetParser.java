package com.example.androidknomar.util;

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
public class TweetParser extends AbstractParser {

    private List<Tweet> listTweet;
    private Tweet currentTweet;
    private User currentUser;
    private String currentElem = "";

    public TweetParser(User user) throws Exception
    {
        super(user.getUri());
        currentUser = user;
    }

    public List<Tweet> loadAndParseTweetFile() throws Exception {

        InputStream inputStream = getStream();
        XmlPullParser xmlPullParser = initializer(inputStream);
        this.parseSource(xmlPullParser);

        return listTweet;
    }

    @Override
    void parseSource(XmlPullParser xmlPullParser) throws Exception {
        int eventType;
        try {
            eventType = xmlPullParser.getEventType();

            do {
                switch (eventType)
                {
                    case XmlPullParser.START_DOCUMENT :
                        listTweet = new ArrayList<Tweet>();
                        break;

                    case XmlPullParser.END_DOCUMENT :
                        System.out.println("End file");
                        break;

                    case XmlPullParser.START_TAG :
                        currentElem = xmlPullParser.getName();
                        if(currentElem.equals(Tweet.CONST_TWEET))
                            currentTweet = new Tweet(currentUser);
                        break;

                    case XmlPullParser.END_TAG :
                        currentElem = "";
                        if(xmlPullParser.getName().equals(Tweet.CONST_TWEET)){
                            currentTweet.setUser(currentUser);
                            listTweet.add(currentTweet);
                            currentTweet = null;
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

    public void parseText(XmlPullParser xmlPullParser) throws Exception
    {
        String value;
        if (currentElem.equals(Tweet.CONST_HASHTAG)) {
            value = xmlPullParser.getText();
            currentTweet.setHashtag(value);

        } else if (currentElem.equals(Tweet.CONST_MESSAGE)) {
            value = xmlPullParser.getText();
            currentTweet.setMessage(value);

        } else if (currentElem.equals(Tweet.CONST_DATE)) {
            value = xmlPullParser.getText();
            currentTweet.setDate(value);

        } else if (currentElem.equals(Tweet.CONST_URL)) {
            value = xmlPullParser.getText();
            currentTweet.setUrl(new URI(value));

        } else {
            //throw new Exception("Error Parser : this kind of message does not exist ! " + currentElem);
        }
    }
}

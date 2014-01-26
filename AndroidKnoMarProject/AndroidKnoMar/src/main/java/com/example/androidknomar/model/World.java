package com.example.androidknomar.model;

import com.example.androidknomar.util.TweetParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent & Michel on 25/01/14.
 */
public class World implements Serializable
{
    public static World instance = new World();

    private List<User> listUser;

    private World(){
        listUser = new ArrayList<User>();
    }

    public List<User> getUsers()
    {
        return listUser;
    }

    public void loadWorld(List<User> users) throws Exception
    {
        listUser = users;

        TweetParser parser;
        for(User user : listUser)
        {
            parser = new TweetParser(user);
            user.setListTweet(parser.loadAndParseTweetFile());
        }
    }

}
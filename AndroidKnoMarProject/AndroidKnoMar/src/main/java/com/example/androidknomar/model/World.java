package com.example.androidknomar.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marchal.vincent on 25/01/14.
 */
public class World implements Serializable
{
    private List<User> listUser;
    private List<Tweet> listTweet;

    public World(){}

    public List<User> getUsers() {
        return listUser;
    }

    public void setUsers(List<User> users) {
        listUser = users;
    }

    public List<Tweet> getTweets() {
        return listTweet;
    }

    public void setTweets(List<Tweet> tweets) {
        listTweet = tweets;
    }

    public void addTweets(List<Tweet> tweets){
        if(listTweet == null){
            listTweet = tweets;
        }else{
            listTweet.addAll(tweets);
        }
    }
}
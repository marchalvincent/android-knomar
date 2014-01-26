package com.example.androidknomar.model;

import android.net.Uri;

import java.io.Serializable;
import java.net.URI;

/**
 * Created by mknoertzer on 25/01/14.
 */
public class Tweet implements Serializable
{
    public static final String CONST_TWEET = "tweet";
    public static final String CONST_HASHTAG = "hashtag";
    public static final String CONST_MESSAGE = "message";
    public static final String CONST_URL = "url";
    public static final String CONST_DATE= "date";


    private User user;
    private String message;
    private String hashtag;
    private String url;
    private String date;

    public Tweet(User user){
        user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag.replaceAll("\n+","");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getUser().getName() + "\n" + getHashtag();
    }
}

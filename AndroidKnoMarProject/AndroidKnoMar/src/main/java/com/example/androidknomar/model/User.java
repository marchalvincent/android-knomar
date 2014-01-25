package com.example.androidknomar.model;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

/**
 * Created by marchal.vincent on 25/01/14.
 */
public class User implements Serializable
{
    private String name;
    private URI uri;
    private boolean isFollowed = false;

    private List<Tweet> listTweet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(boolean isFollowed) {
        this.isFollowed = isFollowed;
    }

    public List<Tweet> getListTweet() {
        return listTweet;
    }

    public void setListTweet(List<Tweet> listTweet) {
        this.listTweet = listTweet;
    }
}
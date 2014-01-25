package com.example.androidknomar.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marchal.vincent on 25/01/14.
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

    public void setUsers(List<User> users) {
        listUser = users;
    }

}
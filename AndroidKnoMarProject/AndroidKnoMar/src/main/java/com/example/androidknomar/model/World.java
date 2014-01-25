package com.example.androidknomar.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by marchal.vincent on 25/01/14.
 */
public class World implements Serializable
{
    private List<User> listUser;

    public World(){}

    public List<User> getUsers() {
        return listUser;
    }

    public void setUsers(List<User> users) {
        listUser = users;
    }

}
package com.example.androidknomar.util;

import com.example.androidknomar.model.Tweet;
import com.example.androidknomar.model.User;

/**
 * Created by marchal.vincent on 26/01/14.
 */
public class State {

    public static Tweet tweetToPrint;

    public static ViewTweetsState state = ViewTweetsState.def;
    public static User oneUserOrNull;

    public static void cleanState() {
        tweetToPrint = null;
        state = ViewTweetsState.def;
        oneUserOrNull = null;
    }

    public enum ViewTweetsState {
        def,
        showAllUsers,
        showAllTweets,
        showOneUser
    }
}

package com.example.androidknomar.util;

import com.example.androidknomar.model.Tweet;

/**
 * Created by marchal.vincent on 26/01/14.
 */
public class State {

    public static Tweet tweetToPrint;

    public static void cleanState() {
        tweetToPrint = null;
    }
}

package com.example.androidknomar.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.example.androidknomar.R;
import com.example.androidknomar.model.Tweet;
import com.example.androidknomar.model.User;
import com.example.androidknomar.model.World;
import com.example.androidknomar.util.TweetParser;
import com.example.androidknomar.util.UserParser;
import com.example.androidknomar.view.ViewUsers;

import java.util.List;

/**
 * Created by Vincent & Michel on 25/01/14.
 */
public class LoadWorldAsyncTask extends AsyncTask<Void, Integer, World> {

    private Activity activity;

    public LoadWorldAsyncTask(Activity activity) {
        super();
        this.activity = activity;
    }

    @Override
    protected World doInBackground(Void... voids) {
        // TODO Michel see URL.USERS
        World world = null;
        try {
            UserParser userparser = new UserParser();
            world = World.instance;
            world.loadWorld(userparser.loadAndParseUsersFile());

        } catch(Exception e) {
            e.printStackTrace();
        }
        return world;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
    }

    @Override
    protected void onPreExecute() {
        System.out.println("Début du parsing xml");
    }

    @Override
    protected void onPostExecute(World result) {
        System.out.println("Parsing xml finit");
        // on fait passer la page suivante, une nouvelle activité
        Intent i = new Intent(activity.getApplicationContext(), ViewUsers.class);
        activity.startActivity(i);
        activity.finish();
    }

    @Override
    protected void onCancelled(World result) {
        System.out.println(new StringBuilder().append("Arret du parsing xml ").append(result).toString());
    }

    @Override
    protected void onCancelled() {
        System.out.println("Arret du parsing xml");
    }
}

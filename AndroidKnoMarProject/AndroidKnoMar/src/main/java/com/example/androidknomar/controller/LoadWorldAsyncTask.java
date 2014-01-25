package com.example.androidknomar.controller;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.androidknomar.R;
import com.example.androidknomar.model.World;
import com.example.androidknomar.view.ViewUsers;

/**
 * Created by marchal.vincent on 25/01/14.
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
        try {
            Thread.sleep(2000);
        } catch(Exception e) {

        }
        return null;
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
        // on fait passer la page suivante
        activity.getFragmentManager().beginTransaction()
                .replace(R.id.container, new ViewUsers(result))
                .commit();
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

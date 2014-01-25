package com.example.androidknomar.view;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidknomar.R;
import com.example.androidknomar.model.Tweet;
import com.example.androidknomar.model.User;
import com.example.androidknomar.model.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marchal.vincent on 25/01/14.
 */
public class ViewTweets extends ListActivity {

    private World world;
    public static ViewTweets viewTweets;

    public ViewTweets() {
        super();
        setTitle("Tweets from");
        world = World.instance;
        viewTweets = this;
    }

    protected Object mActionMode;
    public int selectedItem = -1;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_tweets);
/*
        List<User> users = new ArrayList<User>();
        for(User u : world.getUsers()) {
            if (u.isFollowed()) {
                users.add(u);
            }
        }
*/
        User a = new User();
        a.setName("toto");
        User b = new User();
        b.setName("tata");
        final List<User> users = new ArrayList<User>();
        users.add(a);
        users.add(b);

        final List<Tweet> allTweets = new ArrayList<Tweet>();
        for (User u : users) {
            allTweets.addAll(u.getListTweet());
        }

        this.showListUser(users);

        // set the action to the buttons
        Button buttonTweets = (Button) findViewById(R.id.menu_show_tweets);
        buttonTweets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // print tweets
                viewTweets.showListTweets(allTweets);
            }
        });

        Button buttonUsers = (Button) findViewById(R.id.menu_show_user);
        buttonUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // print users
                viewTweets.showListUser(users);
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO
        Object o = l.getItemAtPosition(position);
        if (o instanceof User) {
            // need to display the tweets of the user
            User user = (User) o;
            this.showUserTweets(user);
        }
        else if (o instanceof Tweet) {
            // need to display the details of the tweet in a new activity detail
            Tweet tweet = (Tweet) o;

            // TODO
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_tweets_bar, menu);
        return true;
    }

    private void showListUser(List<User> users) {
        ArrayAdapter<User> array = new ArrayAdapter<User>(this, android.R.layout.list_content, users);
        setListAdapter(array);
        getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);
    }

    private void showUserTweets(User user) {
        ArrayAdapter<Tweet> array = new ArrayAdapter<Tweet>(this, android.R.layout.list_content, user.getListTweet());
        setListAdapter(array);
        getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);
    }

    private void showListTweets(List<Tweet> tweets) {

    }
}

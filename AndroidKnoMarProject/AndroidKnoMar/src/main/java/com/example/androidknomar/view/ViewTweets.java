package com.example.androidknomar.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidknomar.R;
import com.example.androidknomar.model.Tweet;
import com.example.androidknomar.model.User;
import com.example.androidknomar.model.World;
import com.example.androidknomar.util.State;

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

        this.showListUser();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Object o = l.getItemAtPosition(position);
        if (o instanceof User) {
            // need to display the tweets of the user
            User user = (User) o;
            this.showUserTweets(user);
        }
        else if (o instanceof Tweet) {
            // need to display the details of the tweet in a new activity detail
            Tweet tweet = (Tweet) o;
            Intent i = new Intent(this.getApplicationContext(), ViewDetailTweet.class);
            State.tweetToPrint = tweet;
            this.startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_tweets_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_show_user:
                // go to the next activity
                this.showListUser();
                return true;
            case R.id.menu_show_tweets:
                // go to the next activity
                this.showListTweets();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<User> getFollowedUsers() {
        List<User> users = new ArrayList<User>();
        for(User u : world.getUsers()) {
            if (u.isFollowed()) {
                users.add(u);
            }
        }
        return users;
    }

    private List<Tweet> getFollowedUsersTweets() {
        List<User> users = this.getFollowedUsers();
        List<Tweet> allTweets = new ArrayList<Tweet>();
        for (User u : users) {
            allTweets.addAll(u.getListTweet());
        }
        return allTweets;
    }

    private void showListUser() {
        ArrayAdapter<User> array = new ArrayAdapter<User>(this,
                android.R.layout.simple_list_item_1,
                getFollowedUsers());
        setListAdapter(array);
    }

    private void showUserTweets(User user) {
        ArrayAdapter<Tweet> array = new ArrayAdapter<Tweet>(this,
                android.R.layout.simple_list_item_1,
                user.getListTweet());
        setListAdapter(array);
    }

    private void showListTweets() {
        ArrayAdapter<Tweet> array = new ArrayAdapter<Tweet>(this,
                android.R.layout.simple_list_item_1,
                this.getFollowedUsersTweets());
        setListAdapter(array);
    }
}

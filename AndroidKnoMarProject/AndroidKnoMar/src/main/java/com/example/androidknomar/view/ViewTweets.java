package com.example.androidknomar.view;

import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by Vincent & Michel on 25/01/14.
 */
public class ViewTweets extends ListActivity {

    private World world;
    public static ViewTweets viewTweets;

    public ViewTweets() {
        super();
        world = World.instance;
        viewTweets = this;
    }

    protected Object mActionMode;
    public int selectedItem = -1;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_tweets);

        if (State.state == State.ViewTweetsState.showOneUser && State.oneUserOrNull != null) {
            this.showUserTweets(State.oneUserOrNull);
        } else if (State.state == State.ViewTweetsState.showAllTweets) {
            this.showListTweets();
        } else {
            this.showListUser();
        }
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
            case R.id.menu_show_tweets_back:
                State.state = State.ViewTweetsState.def;
                State.oneUserOrNull = null;
                Intent i = new Intent(this.getApplicationContext(), ViewUsers.class);
                this.startActivity(i);
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
        State.state = State.ViewTweetsState.showAllUsers;
        State.oneUserOrNull = null;
        setListAdapter(array);
    }

    private void showUserTweets(User user) {
        ArrayAdapter<Tweet> array = new ArrayAdapter<Tweet>(this,
                android.R.layout.simple_list_item_1,
                user.getListTweet());
        State.state = State.ViewTweetsState.showOneUser;
        State.oneUserOrNull = user;
        setListAdapter(array);
    }

    private void showListTweets() {
        ArrayAdapter<Tweet> array = new ArrayAdapter<Tweet>(this,
                android.R.layout.simple_list_item_1,
                this.getFollowedUsersTweets());
        State.state = State.ViewTweetsState.showAllTweets;
        State.oneUserOrNull = null;
        setListAdapter(array);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_no_follow, container, false);
            return rootView;
        }
    }
}

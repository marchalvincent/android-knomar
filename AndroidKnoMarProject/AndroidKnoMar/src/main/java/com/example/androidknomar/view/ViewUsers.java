package com.example.androidknomar.view;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
import com.example.androidknomar.model.User;
import com.example.androidknomar.model.World;

/**
 * Created by Vincent & Michel on 25/01/14.
 */
public class ViewUsers extends ListActivity {

    private World world;

    public ViewUsers() {
        super();
        world = World.instance;
    }

    protected Object mActionMode;
    public int selectedItem = -1;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_users);

        // set "no follow" by default
        for(User u : world.getUsers())
            u.setIsFollowed(false);

        ArrayAdapter<User> array = new ArrayAdapter<User>(this,
                android.R.layout.simple_list_item_multiple_choice,
                world.getUsers());
        setListAdapter(array);
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        User user = (User) l.getItemAtPosition(position);
        user.setIsFollowed(!user.isFollowed());
        Log.e("Salut", user.getName() + " " + user.isFollowed());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select_user_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_follow:
                // go to the next activity
                Intent i = new Intent(this.getApplicationContext(), ViewTweets.class);
                this.startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Quit ?")
                .setMessage("Really want to quit ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ViewUsers.super.onBackPressed();
                        finish();
                        //System.exit(0);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}

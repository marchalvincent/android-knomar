package com.example.androidknomar.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidknomar.R;
import com.example.androidknomar.model.User;
import com.example.androidknomar.model.World;

/**
 * Created by marchal.vincent on 25/01/14.
 */
public class ViewUsers extends ListActivity {

    private World world;

    public ViewUsers() {
        super();
        //setTitle("Follow someone");
        world = World.instance;
    }

    protected Object mActionMode;
    public int selectedItem = -1;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_users);

        User a = new User();
        a.setName("toto");
        User b = new User();
        b.setName("tata");
        User[] users = new User[] { a, b };

        ArrayAdapter<User> array = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_multiple_choice, users);
        setListAdapter(array);
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // set the action to the next button
        Button button = (Button) findViewById(R.id.menu_follow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to the next activity
                // on fait passer la page suivante, une nouvelle activité
                Intent i = new Intent(getApplicationContext(), ViewTweets.class);
                startActivity(i);
            }
        });
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
}

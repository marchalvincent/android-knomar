package com.example.androidknomar.view;

import android.app.Fragment;
import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidknomar.R;
import com.example.androidknomar.model.User;
import com.example.androidknomar.model.World;
import com.example.androidknomar.util.MySimpleArrayAdapter;

/**
 * Created by marchal.vincent on 25/01/14.
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

        User a = new User();
        a.setName("toto");
        User b = new User();
        b.setName("tata");
        User[] users = new User[] { a, b };

        ArrayAdapter<User> array = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_multiple_choice, users);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

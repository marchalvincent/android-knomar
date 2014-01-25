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
import com.example.androidknomar.model.World;
import com.example.androidknomar.util.MySimpleArrayAdapter;

/**
 * Created by marchal.vincent on 25/01/14.
 */
public class ViewUsers extends ListActivity {

    private World world;
    private String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2" };

    public ViewUsers() {
        super();

    }

    protected Object mActionMode;
    public int selectedItem = -1;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_users);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2" };

        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, values);
        setListAdapter(adapter);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                if (mActionMode != null) {
                    return false;
                }
                selectedItem = position;

                // start the CAB using the ActionMode.Callback defined above
                mActionMode = ViewUsers.this
                        .startActionMode(mActionModeCallback);
                view.setSelected(true);
                return true;
            }
        });
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // called when the action mode is created; startActionMode() was called
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            // assumes that you have "contexual.xml" menu resources
            inflater.inflate(R.menu.rowselection, menu);
            return true;
        }

        // the following method is called each time
        // the action mode is shown. Always called after
        // onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // called when the user selects a contextual menu item
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menuitem1_show:
                    show();
                    // the Action was executed, close the CAB
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        // called when the user exits the action mode
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
            selectedItem = -1;
        }
    };

    private void show() {
        Toast.makeText(ViewUsers.this,
                String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
    }
}

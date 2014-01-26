package com.example.androidknomar.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidknomar.R;
import com.example.androidknomar.controller.AsyncUploadImage;
import com.example.androidknomar.controller.LoadWorldAsyncTask;
import com.example.androidknomar.model.Tweet;
import com.example.androidknomar.util.State;

/**
 * Created by Vincent & Michel on 26/01/14.
 */
public class ViewDetailTweet extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tweet);

        Tweet t = State.tweetToPrint;
        TextView hashtag = (TextView) findViewById(R.id.hashtag);
        hashtag.setText(t.getHashtag());
        TextView message = (TextView) findViewById(R.id.message);
        message.setText(t.getMessage());
        ImageView image = (ImageView) findViewById(R.id.imageView);
        new AsyncUploadImage(image).execute(t.getUrl());
        TextView publish = (TextView) findViewById(R.id.publish);
        publish.setText("Publish on " + t.getDate().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_tweet_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_back:
                // go back to the ViewTweets view
                Intent i = new Intent(this.getApplicationContext(), ViewTweets.class);
                this.startActivity(i);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                        //ViewDetailTweet.super.onBackPressed();
                        finish();
                        //System.exit(0);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}

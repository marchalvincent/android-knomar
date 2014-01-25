package com.example.androidknomar.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidknomar.R;
import com.example.androidknomar.model.World;

/**
 * Created by marchal.vincent on 25/01/14.
 */
public class ViewUsers extends Fragment {

    public ViewUsers(World world) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_users, container, false);
        return rootView;
    }
}

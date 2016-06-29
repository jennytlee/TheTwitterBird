package com.codepath.apps.restclienttemplate.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.fragments.MyHeaderFragment;
import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;
import com.codepath.apps.restclienttemplate.models.User;

import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        String screenName = getIntent().getStringExtra("screen_name");
        String uid = getIntent().getStringExtra("uid");

        getSupportActionBar().setTitle("@" + screenName);
        UserTimelineFragment userFragment = UserTimelineFragment.newInstance(screenName);
        MyHeaderFragment myHeaderFragment = new MyHeaderFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState == null) {
            ft.add(R.id.flTimeline, userFragment);
            ft.add(R.id.flProfileHeader, myHeaderFragment);
        }
        ft.commit();
    }
}

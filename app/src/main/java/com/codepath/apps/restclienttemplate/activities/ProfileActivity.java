package com.codepath.apps.restclienttemplate.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.fragments.MyHeaderFragment;
import com.codepath.apps.restclienttemplate.fragments.UserHeaderFragment;
import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;

import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        long uid = getIntent().getLongExtra("user_id", 0);
        String screenName = getIntent().getStringExtra("screen_name");

        getSupportActionBar().setTitle("@" + screenName);
        UserTimelineFragment userFragment = UserTimelineFragment.newInstance(screenName);
        UserHeaderFragment userHeaderFragment = UserHeaderFragment.newInstance(uid,
                screenName);
        MyHeaderFragment myHeaderFragment = new MyHeaderFragment();

        if (screenName == null) {
            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
            if (savedInstanceState == null) {
                ft2.replace(R.id.flTimeline, userFragment);
                ft2.replace(R.id.flProfileHeader, myHeaderFragment);
            }
            ft2.commit();
        }

        else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (savedInstanceState == null) {
                ft.replace(R.id.flTimeline, userFragment);
                ft.replace(R.id.flProfileHeader, userHeaderFragment);
                //ft.replace(R.id.flProfileHeader, myHeaderFragment);
            }
            ft.commit();
        }

    }
}

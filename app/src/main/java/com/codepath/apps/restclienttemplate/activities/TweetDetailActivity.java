package com.codepath.apps.restclienttemplate.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.fragments.TweetDetailFragment;
import com.codepath.apps.restclienttemplate.fragments.UserHeaderFragment;

public class TweetDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        long tweet_id = getIntent().getLongExtra("tweet_id", 0);
        long uid = getIntent().getLongExtra("user_id", 0);
        String screenName = getIntent().getStringExtra("screen_name");


        TweetDetailFragment tweetDetailFragment = TweetDetailFragment.newInstance(tweet_id);
        UserHeaderFragment userHeaderFragment = UserHeaderFragment.newInstance(uid,
                screenName);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState == null) {
            ft.replace(R.id.flProfileHeader2, userHeaderFragment);
            ft.replace(R.id.flTweetDetail, tweetDetailFragment);
            //ft.replace(R.id.flProfileHeader, myHeaderFragment);
        }
        ft.commit();

    }


}

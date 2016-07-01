package com.codepath.apps.restclienttemplate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.codepath.apps.restclienttemplate.adapters.SmartFragmentStatePagerAdapter;
import com.codepath.apps.restclienttemplate.fragments.ComposeTweetDialogFragment;
import com.codepath.apps.restclienttemplate.fragments.HomeTimelineFragment;
import com.codepath.apps.restclienttemplate.fragments.MentionsTimelineFragment;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity implements ComposeTweetDialogFragment.ComposeTweetDialogListener {

    Tweet newTweet;
    TweetsPagerAdapter adapterViewPager;
    private TwitterClient client;

    @BindView(R.id.vpPager) ViewPager vpPager;
    @BindView(R.id.sliding_tabs) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);

        client = TwitterApp.getRestClient();
        adapterViewPager = new TweetsPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        tabLayout.setupWithViewPager(vpPager);

    }

    public void onProfileView(MenuItem item) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);

    }

    public void composeTweet(View view) {

        FragmentManager fm = getSupportFragmentManager();
        ComposeTweetDialogFragment composeTweetDialogFragment = new ComposeTweetDialogFragment();
        composeTweetDialogFragment.show(fm, "fragment_compose_tweet");

    }

    @Override
    public void onFinishEditDialog(final String inputText) {

        client.postTweet(inputText, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                newTweet = Tweet.fromJSON(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

        HomeTimelineFragment fragmentHomeTweets = (HomeTimelineFragment) adapterViewPager.getRegisteredFragment(0);
        fragmentHomeTweets.appendTweet(newTweet);


    }

    // return order of fragments in viewpager
    public class TweetsPagerAdapter extends SmartFragmentStatePagerAdapter {
        private String tabTitles[] = {"Home", "Mentions"};

        public TweetsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new HomeTimelineFragment();
            } else if (position == 1) {
                return new MentionsTimelineFragment();
            } else {
                return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchView.clearFocus();

                // perform query here
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                i.putExtra("query", query);
                startActivity(i);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}

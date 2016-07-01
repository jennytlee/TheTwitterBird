package com.codepath.apps.restclienttemplate.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.fragments.SearchTweetsFragment;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String query = getIntent().getStringExtra("query");

        SearchTweetsFragment searchTweetsFragment = SearchTweetsFragment.newInstance(query);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState == null) {
            ft.replace(R.id.flSearch, searchTweetsFragment);
        }
        ft.commit();

    }
}

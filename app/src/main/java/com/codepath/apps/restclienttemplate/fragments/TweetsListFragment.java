package com.codepath.apps.restclienttemplate.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.activities.TweetDetailActivity;
import com.codepath.apps.restclienttemplate.adapters.TweetsArrayAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jennytlee on 6/27/16.
 */
public class TweetsListFragment extends Fragment {

    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    @BindView(R.id.lvTweets) ListView lvTweets;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, parent, false);
        ButterKnife.bind(this, v);

        lvTweets.setAdapter(aTweets);

        lvTweets.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), TweetDetailActivity.class);

                Tweet tweet = aTweets.getItem(position);

                long tweetId = tweet.getTweetId();
                long uid = tweet.getUser().getUid();
                String screenName = tweet.getUser().getScreenname();

                i.putExtra("tweet_id", tweetId);
                i.putExtra("user_id", uid);
                i.putExtra("screen_name", screenName);
                startActivity(i);

            }
        });

        return v;
    }


    // creation lifecycle


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);

    }

    /*public void add(int position, Tweet tweet) {
        aTweets.insert(tweet, position);
        aTweets.notifyDataSetChanged();
    }*/

    public void addAll(List<Tweet> tweets) {
        aTweets.addAll(tweets);
    }

    public void clear() {
        aTweets.clear();
    }


}

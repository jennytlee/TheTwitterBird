package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by jennytlee on 6/30/16.
 */
public class TweetDetailFragment extends Fragment {

    TwitterClient client;
    Tweet tweet;

    @BindView(R.id.tvBody)
    TextView tvBodyText;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvRetweetCount)
    TextView tvRetweetCount;
    @BindView(R.id.tvLikeCount)
    TextView tvLikeCount;
    @BindView(R.id.ivReply)
    ImageView ivReply;
    @BindView(R.id.ivRetweeter)
    ImageView ivRetweet;
    @BindView(R.id.ivLike)
    ImageView ivLike;
    @BindView(R.id.ivShare)
    ImageView ivShare;


    public static TweetDetailFragment newInstance(long tweetId) {
        TweetDetailFragment tweetDetailFragment = new TweetDetailFragment();
        Bundle args = new Bundle();
        args.putLong("tweet_id", tweetId);
        tweetDetailFragment.setArguments(args);
        return tweetDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweet_detail, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = TwitterApp.getRestClient();
        populateTweet();
    }

    private void populateTweet() {

        final long tweetId = getArguments().getLong("tweet_id");

        client.getTweetInfo(tweetId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                tweet = Tweet.fromJSON(response);

                tvBodyText.setText(tweet.getBody());
                tvDate.setText(tweet.getTimestamp());
                tvRetweetCount.setText(tweet.getRetweetCount());
                tvLikeCount.setText(tweet.getLikeCount());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }
}

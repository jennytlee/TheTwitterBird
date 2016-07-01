package com.codepath.apps.restclienttemplate.models;

import android.net.ParseException;
import android.text.format.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jennytlee on 6/27/16.
 */
public class Tweet {

    private String body;
    private long tweetId;
    private User user;
    private String timestamp;
    private String retweetCount;
    private String likeCount;

    public String getBody() {
        return body;
    }

    public long getTweetId() {
        return tweetId;
    }

    public User getUser() {
        return user;
    }

    public String getTimestamp() {
        return getRelativeTimeAgo(timestamp);
    }

    public String getRetweetCount() {
        return retweetCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public static Tweet fromJSON(JSONObject jsonObject) {
        Tweet tweet = new Tweet();

        try {

            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
            tweet.body = jsonObject.getString("text");
            tweet.tweetId = jsonObject.getLong("id");
            tweet.timestamp = jsonObject.getString("created_at");
            tweet.retweetCount = String.valueOf(jsonObject.getInt("retweet_count"));
            tweet.likeCount = String.valueOf(jsonObject.getInt("favorite_count"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tweet;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if (tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }

        return tweets;
    }


    private String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();

            if (Character.isDigit(relativeDate.charAt(0))) {
                StringBuilder sb = new StringBuilder(relativeDate);

                if (!Character.isDigit(relativeDate.charAt(1))) {
                    sb.deleteCharAt(1);
                    relativeDate = sb.toString().substring(0, 2);
                } else {
                    sb.deleteCharAt(2);
                    relativeDate = sb.toString().substring(0, 3);
                }
            }



        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }


}

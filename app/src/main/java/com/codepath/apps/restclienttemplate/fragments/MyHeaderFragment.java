package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jennytlee on 6/28/16.
 */
public class MyHeaderFragment extends ProfileHeaderFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = TwitterApp.getRestClient();
        populateProfileHeader();

    }

    private void populateProfileHeader() {
        client.getMyInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                user = User.fromJSON(response);

                tvName.setText(user.getName());
                tvTagline.setText(user.getTagline());
                tvFollowers.setText(user.getFollowers() + " Followers");
                tvFollowing.setText(user.getFollowing() + " Following");
                Glide.with(getContext()).load(user.getProfileImageUrl()).fitCenter().into(ivProfileImage);
                // set tile bar as user's screen name

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}

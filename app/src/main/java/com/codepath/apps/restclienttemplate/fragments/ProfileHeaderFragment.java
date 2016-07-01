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
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.codepath.apps.restclienttemplate.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jennytlee on 6/28/16.
 */
public class ProfileHeaderFragment extends Fragment {

    TwitterClient client;
    User user;

    @BindView(R.id.tvBodyText)
    TextView tvName;
    @BindView(R.id.tvTagline) TextView tvTagline;
    @BindView(R.id.tvFollowers) TextView tvFollowers;
    @BindView(R.id.tvFollowing) TextView tvFollowing;
    @BindView(R.id.ivProfileImage)
    ImageView ivProfileImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_header, null);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = new User();

    }
}

package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.activities.ProfileActivity;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jennytlee on 6/27/16.
 */
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
    }

    public static class ViewHolder {
        @BindView(R.id.tvBody) TextView tvBody;
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvUsername) TextView tvUsername;
        @BindView(R.id.ivProfileImage) ImageView ivProfileImage;
        @BindView(R.id.tvTimestamp) TextView tvTimestamp;
        @BindView(R.id.ibShare) ImageView ibShare;
        @BindView(R.id.ibLikes) ImageView ibLikes;
        @BindView(R.id.ibRetweets) ImageView ibRetweets;
        @BindView(R.id.tvRetweetCount) TextView tvRetweetCount;
        @BindView(R.id.tvLikeCount) TextView tvLikeCount;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get tweet
        Tweet tweet = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final long uid = tweet.getUser().getUid();
        final String screenName = tweet.getUser().getScreenname();

        viewHolder.tvName.setText(tweet.getUser().getName());
        viewHolder.tvUsername.setText("@" + screenName);
        viewHolder.tvBody.setText(tweet.getBody());
        viewHolder.tvTimestamp.setText(tweet.getTimestamp());
        viewHolder.tvRetweetCount.setText(tweet.getRetweetCount());
        viewHolder.tvLikeCount.setText(tweet.getLikeCount());

//            viewHolder.ivProfileImage.setImageResource(android.R.color.transparent);
        Glide.with(getContext()).load(tweet.getUser().getProfileImageUrl()).fitCenter().into(viewHolder.ivProfileImage);

        viewHolder.ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), ProfileActivity.class);
                i.putExtra("user_id", uid);
                i.putExtra("screen_name", screenName);
                v.getContext().startActivity(i);

                /*UserTimelineFragment userFragment = UserTimelineFragment.newInstance(screenName);
                UserHeaderFragment userHeaderFragment = UserHeaderFragment.newInstance(uid,
                        screenName);
                FragmentTransaction ft = ((ProfileActivity) getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flTimeline, userFragment);ft.replace(R.id.flProfileHeader, userHeaderFragment);
                ft.commit();
                */

            }
        });

        return convertView;

    }

    @Override
    public void add(Tweet object) {
        super.add(object);
        notifyDataSetChanged();
    }
}

package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
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
        @BindView(R.id.tvUsername) TextView tvUsername;
        @BindView(R.id.ivProfileImage) ImageView ivProfileImage;

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
        viewHolder.tvUsername.setText(tweet.getUser().getScreenname());
        viewHolder.tvBody.setText(tweet.getBody());

//            viewHolder.ivProfileImage.setImageResource(android.R.color.transparent);
        Glide.with(getContext()).load(tweet.getUser().getProfileImageUrl()).fitCenter().into(viewHolder.ivProfileImage);

        //add other views here



        return convertView;

    }
}

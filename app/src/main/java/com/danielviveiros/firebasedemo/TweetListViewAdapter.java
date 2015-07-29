package com.danielviveiros.firebasedemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Custom adapter to handle artist list (search) information
 * Created by dviveiros on 24/06/15.
 */
public class TweetListViewAdapter extends ArrayAdapter<Tweet> {

    private Context context;

    public TweetListViewAdapter(Context context, int resourceId, List<Tweet> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /* private view holder class */
    private class ViewHolder {
        TextView tweetView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Tweet tweet = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tweets_listitem, null);
            holder = new ViewHolder();
            holder.tweetView = (TextView) convertView.findViewById(R.id.tweet);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tweetView.setText(tweet.getAuthor() + ": " + tweet.getMessage() );
        return convertView;
    }

}

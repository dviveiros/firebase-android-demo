package com.danielviveiros.firebasedemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    /** Max number of tweets */
    private static final Integer MAX_TWEETS = 5;

    /** Adapter to deal with the list of tweets */
    private TweetListViewAdapter mTweetListAdapter;

    /** List view */
    private ListView mTweetsListView;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //prepare the list view and adapter
        mTweetListAdapter = new TweetListViewAdapter(getActivity(),
                R.layout.tweets_listitem, new ArrayList<Tweet>());
        mTweetsListView = (ListView) rootView.findViewById(R.id.listview_tweets);
        mTweetsListView.setAdapter(mTweetListAdapter);

        //Firebase
        Firebase ref = new Firebase("https://gds-2015.firebaseio.com/tweets");

        // read tweets
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot tweetSnapshot : snapshot.getChildren()) {
                    Tweet tweet = tweetSnapshot.getValue(Tweet.class);
                    updateTweetList(tweet);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        return rootView;
    }

    /**
     * Adds a new tweet
     */
    private void updateTweetList( Tweet tweet ) {
        if ( mTweetListAdapter.getCount() >= MAX_TWEETS ) {
            mTweetListAdapter.remove( mTweetListAdapter.getItem( 0 ) );
        }
        mTweetListAdapter.insert(tweet, 0);
    }
}

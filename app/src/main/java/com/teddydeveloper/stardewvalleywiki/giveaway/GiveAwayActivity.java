package com.teddydeveloper.stardewvalleywiki.giveaway;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.ads.interstitial.RevMobFullscreen;
import com.teddydeveloper.stardewvalleywiki.R;

public class GiveAwayActivity extends AppCompatActivity {


    private final String LOG_TAG = GiveAwayActivity.class.getSimpleName();

    RevMob revmob;
    RevMobFullscreen rewardedVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giveaway_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        revmob=RevMob.startWithListener(this,revmobListener);
    }


    RevMobAdsListener revmobListener = new RevMobAdsListener() {

        @Override
        public void onRevMobSessionIsStarted() {
            loadRewardedVideo(); // pre-load it without showing it
        }

        public void onRevMobRewardedVideoLoaded() {
            gameOverScreen(); // You can already show it here if you prefer
        }
    };

    public void loadRewardedVideo() {
        rewardedVideo = revmob.createRewardedVideo(this, revmobListener);
    }

    public void gameOverScreen() {
        //In this example, when it goes to gameOverScreen we show the video:
        if(revmob.isRewardedVideoLoaded()) rewardedVideo.showRewardedVideo();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
           finish();
       }

    }





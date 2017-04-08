package com.teddydeveloper.stardewvalleywiki;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.util.List;

public class RssFeedActivity extends AppCompatActivity {
    //  implements Callback
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /**    setContentView(R.layout.activity_rss_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        String link = "http://steamcommunity.com/games/413150/rss/";

        //TextView t = (TextView) findViewById(R.id.title_rss_feed);

        String url = "http://steamcommunity.com/games/413150/rss/";
        PkRSS.with(this).load(url).callback(this).async(); */

    }
/**
    @Override
    public void onPreload() {

    }

    @Override
    public void onLoaded(List<Article> newArticles) {


        Article article = newArticles.get(0);
        //  Toast.makeText(this,article.toString(),Toast.LENGTH_LONG);

        for (Article item : newArticles) {
            Log.i("PkRSS", item.toString());
           // Toast.makeText(this, item.getDescription().toShortString(), Toast.LENGTH_LONG);

        }
    }
    @Override
    public void onLoadFailed() {
        Toast.makeText(this, "FAIL", Toast.LENGTH_LONG);
    }
*/
}

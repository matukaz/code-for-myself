package com.teddydeveloper.stardewvalleywiki;

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

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    String itemType;
    String title;

    private RevMob revmob;
    private Activity currentActivity;


    private final String LOG_TAG = WebActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        itemType = this.getIntent().getStringExtra("itemType");
        title = this.getIntent().getStringExtra("title");

        webView = (WebView) findViewById(R.id.webView);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setWebChromeClient(new WebChromeClient() {

        });

        setTitle(toTitleCase(itemType));
        if(itemType.toLowerCase().equals("spring")) {
            webView.loadUrl("file:///android_asset/html/spring.html");
        }else if(itemType.toLowerCase().equals("summer")){
            webView.loadUrl("file:///android_asset/html/summer.html");
        }else if(itemType.toLowerCase().equals("fall")){
            webView.loadUrl("file:///android_asset/html/fall.html");
        }else if(itemType.toLowerCase().equals("winter")){
            webView.loadUrl("file:///android_asset/html/winter.html");
        }
        else if(itemType.toLowerCase().equals("fishes")){
            setTitle("Fish");
            webView.loadUrl("file:///android_asset/html/fishes.html");
            Log.d("Can go back?" + webView.canGoBack(), LOG_TAG);
        }
        else if(itemType.toLowerCase().equals("animals")){
            webView.loadUrl("file:///android_asset/html/animals.html");
        }
        else if(itemType.toLowerCase().equals("boots")){
            showAds();
            webView.loadUrl("file:///android_asset/html/boots.html");
        }
        else if(itemType.toLowerCase().equals("hats")){
            showAds();
            //todo change it back
            webView.loadUrl("file:///android_asset/html/hats.html");
        }

        else if(itemType.toLowerCase().equals("npc")){
           // showAds();
            webView.loadUrl("file:///android_asset/html/" + title.toLowerCase() + ".html");
        }
        else if(itemType.toLowerCase().equals("crop")){
            showAds();
            String newTitle = toTitleCase(title).replaceAll(" ", "_");
            webView.loadUrl("file:///android_asset/crop/" + newTitle + ".html");
        } else if(itemType.toLowerCase().equals("resources")){
            showAds();
            setTitle("Resources & Minerals");
            String newTitle = toTitleCase(title).replaceAll(" ", "_");
            webView.loadUrl("file:///android_asset/mineral/" + newTitle + ".html");

        }


        else if(itemType.toLowerCase().equals("food")){


            showAds();
            String newTitle = toTitleCase(title).replaceAll(" ", "_");

            webView.loadUrl("file:///android_asset/food/" + newTitle + ".html");
        }

    }
    public void showAds(){

        final AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();

        //   AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
        });

    }


    public static String toTitleCase(String givenString) {
        String[] arr = givenString.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
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
       if( webView.canGoBack()){
           webView.goBack();
       } else{

           finish();
       }

    }
}




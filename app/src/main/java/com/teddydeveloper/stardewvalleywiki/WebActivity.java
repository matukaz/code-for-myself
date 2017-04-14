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
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.adcash.mobileads.ui.AdcashBannerView;
import com.amplitude.api.Amplitude;
import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.revmob.RevMob;
import com.teddydeveloper.stardewvalleywiki.Helper.Helper;

import java.util.Random;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    String itemType;
    String title;

    private RevMob revmob;
    private Activity currentActivity;

    private AdcashBannerView mBanner;


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
        WebViewClient client  = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        };

        webView.setWebViewClient(client);

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
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(this, "webConnector");
            webView.addJavascriptInterface(this, "toaster");

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

        ImageView reserverSpot = (ImageView) findViewById(R.id.reserver);
        reserverSpot.setVisibility(View.VISIBLE);


        Random rand = new Random();
        int value = rand.nextInt(10);
        if (value == 5 || value == 6 || value == 7) {
            showGoForItBotAd();
        } else {
            showAppodealAds();
            // showAdMobAds();
        }
    }

    private void showAdMobAds() {
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

    private void showAppodealAds() {
        String appKey = "4ac68d25a80d5a2dfb41e77ea789b604b82d572964e86344";
        Appodeal.disableLocationPermissionCheck();
        Appodeal.cache(this, Appodeal.BANNER_BOTTOM, 3);
        Appodeal.initialize(this, appKey, Appodeal.BANNER_BOTTOM);
        Appodeal.show(this, Appodeal.BANNER_BOTTOM);
    }

    private void showGoForItBotAd() {
        Amplitude.getInstance().logEvent("BANNER_GOFORIT_SHOWN");
        ImageView goForItBotBanner = (ImageView) findViewById(R.id.goforitbanner);
        goForItBotBanner.setVisibility(View.VISIBLE);
        goForItBotBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplitude.getInstance().logEvent("BANNER_GOFORIT_CLICKED");
                Helper.openWebPage(v.getContext(), "https://www.facebook.com/GoforitBot/");
             }
         });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Appodeal.onResume(this, Appodeal.BANNER_BOTTOM);
    }

    @Override
    protected void onDestroy() {
     //   mBanner.destroy();
        super.onDestroy();

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




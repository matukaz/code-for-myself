package com.teddydeveloper.stardewvalleywiki;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.amplitude.api.Amplitude;
import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.ads.interstitial.RevMobFullscreen;
import com.teddydeveloper.stardewvalleywiki.giveaway.GiveAwayActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Kasutada mingit basic activityt. 
 *
 *
 * https://snow.dog/blog/material-design-flexible-space-header-with-image/
 *http://blog.grafixartist.com/toolbar-animation-with-android-design-support-library/
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DataAdapter.DataAdapterClickListener {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.scrollableview)
    RecyclerView recyclerView;

    private List<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Amplitude.getInstance().initialize(this, "b5197a3f05e7ebbc5e4753671d7c8c86").enableForegroundTracking(getApplication());
        Amplitude.getInstance().logEvent("OPEN_APP");
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);


        //TODO delete this
        initializeData();


        DataAdapter adapter = new DataAdapter(data);
        recyclerView.setAdapter(adapter);
        adapter.setmDataAdapterClickListener(this);
        //recyclerView.setDataListdapterClickListener(this);

    }



    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        data = new ArrayList<>();

        data.add(new Data("basic","Basic", R.drawable.main_basic));
        data.add(new Data("npc","Npc", R.drawable.abigail_icon));
        data.add(new Data("seasons","Calendar & Seasons", R.drawable.main_calendar));
        data.add(new Data("crop","Crops & Fruits", R.drawable.parsnip));
        data.add(new Data("items","Items", R.drawable.main_items));
     // data.add(new Data("Valley", R.drawable.pickaxe));
     // data.add(new Data("Environment", R.drawable.pickaxe));
        data.add(new Data("resources", "Resources & Minerals",R.drawable.coal));
        data.add(new Data("food","Food", R.drawable.food));
        data.add(new Data("fishes","Fish", R.drawable.main_fish));
        data.add(new Data("animals","Animals", R.drawable.cow));
        data.add(new Data("boots","Boots", R.drawable.boots));
        data.add(new Data("hats","Hats", R.drawable.hats));
        data.add(new Data("map","Map", R.drawable.main_map));
        data.add(new Data("links","Useful links", R.drawable.url));


        data.add(new Data("giveaway","Giveaway", android.R.drawable.ic_menu_compass));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            Amplitude.getInstance().logEvent("DRAWER_OPENED");
        } else {
            super.onBackPressed();
        }
    }

    /**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    } **/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_share) {
            Amplitude.getInstance().logEvent("SHARING_APP");
            shareApp();
        } else if(id==R.id.nav_rate){
            Amplitude.getInstance().logEvent("RATE_APP");
            showGooglePlayStore();
        } else if (id == R.id.nav_feedback) {
            Amplitude.getInstance().logEvent("SEND_FEEDBACK");
            sendFeedback();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void sendFeedback() {

        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[] { getString(R.string.teddyEmail) });
        Email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.FeedbackOrBug));
        Email.putExtra(Intent.EXTRA_TEXT, getString(R.string.deviceInfo) + android.os.Build.MODEL + " ("+ Build.VERSION.RELEASE +")"+
                getString(R.string.dear_dev));
        startActivity(Intent.createChooser(Email, getString(R.string.sendFeedback)));
    }

    private void shareApp() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "https://play.google.com/store/apps/details?id="+this.getPackageName();
        String subjectText = getString(R.string.look_at_this_app);
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subjectText);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_via)));
    }

    public void showGooglePlayStore() {

        try {
            //Open google play market
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+this.getPackageName())));
        } catch (ActivityNotFoundException e) {
            //if Google play market is not found and throws exception
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+this.getPackageName())));
        } catch (Exception e) {
            //Error message
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.showGooglePlayStoreErrorMessage).show();
        }
    }

    @Override
    public void itemClicked(View viewm,int pos) {
        Log.d("itemClicked" + data.get(pos).itemType + " " + data.get(pos).getItemType(), LOG_TAG);


        String itemTypeClickedOn = data.get(pos).getItemType();
        Amplitude.getInstance().logEvent("itemClicked" + itemTypeClickedOn);

        if(itemTypeClickedOn == "giveaway"){
            Intent listActivity = new Intent(this, GiveAwayActivity.class);
            startActivity(listActivity);
        }





        if(itemTypeClickedOn == "fishes" || itemTypeClickedOn == "animals" || itemTypeClickedOn == "boots" || itemTypeClickedOn == "hats" ){

            Log.d("List!",LOG_TAG);
            Intent listActivity = new Intent(this, WebActivity.class);
            listActivity.putExtra("itemType", itemTypeClickedOn);

            startActivity(listActivity);


         }else if(itemTypeClickedOn == "npc" || itemTypeClickedOn =="food" || itemTypeClickedOn == "crop" || itemTypeClickedOn =="resources") {
            Log.d("List!",LOG_TAG);
            Intent listActivity = new Intent(this, SearchListActivity.class);
            listActivity.putExtra("itemType", itemTypeClickedOn);
            startActivity(listActivity);
        }else if(itemTypeClickedOn != "map" && itemTypeClickedOn != "calendar") {
            Log.d("List!",LOG_TAG);
            Intent listActivity = new Intent(this, ListActivity.class);
            listActivity.putExtra("itemType", itemTypeClickedOn);
            startActivity(listActivity);
        } else if(itemTypeClickedOn.toLowerCase() == "map" || itemTypeClickedOn.toLowerCase() == "calendar"){
            Log.d("Pictures!!",LOG_TAG);
            Intent listActivity = new Intent(this, MapActivity.class);
            listActivity.putExtra("itemType", itemTypeClickedOn);
            startActivity(new Intent(listActivity));
        }

    }
}

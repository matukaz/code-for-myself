package com.teddydeveloper.stardewvalleywiki;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
import android.widget.ImageButton;

import com.adcash.mobileads.ui.AdcashInterstitial;
import com.amplitude.api.Amplitude;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.teddydeveloper.stardewvalleywiki.Const.Const;
import com.teddydeveloper.stardewvalleywiki.Giveaway.GiveAwayActivity;
import com.teddydeveloper.stardewvalleywiki.Map.MapActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Kasutada mingit basic activityt.
 * <p>
 * <p>
 * https://snow.dog/blog/material-design-flexible-space-header-with-image/
 * http://blog.grafixartist.com/toolbar-animation-with-android-design-support-library/
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

    private AdcashInterstitial mInterstitial;

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

        ImageButton closeBtn = (ImageButton) findViewById(R.id.google_form_close_btn);


        final CardView googleFormCardView = (CardView) findViewById(R.id.google_form_cardview);
        if (!isGoogleFormVisible()) {
            googleFormCardView.setVisibility(View.VISIBLE);
        }


        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set cardview to gone
                googleFormCardView.setVisibility(View.GONE);
                saveGoogleFormPref();
            }
        });

        recyclerView.setLayoutManager(linearLayoutManager);


        //TODO delete this
        initializeData();


        DataAdapter adapter = new DataAdapter(data);
        recyclerView.setAdapter(adapter);
        adapter.setmDataAdapterClickListener(this);
        //recyclerView.setDataListdapterClickListener(this);

        googleFormCardView.setVisibility(View.GONE);

    }

    private boolean isGoogleFormVisible() {
        SharedPreferences prefs = getSharedPreferences(Const.PREF_GOOGLE_FORM, MODE_PRIVATE);
        return prefs.getBoolean("googleFormVisible", true);
    }

    private void saveGoogleFormPref() {

        SharedPreferences.Editor editor = getSharedPreferences(Const.PREF_GOOGLE_FORM, MODE_PRIVATE).edit();
        editor.putBoolean("googleFormVisible", false);
        editor.apply();

    }


    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData() {
        data = new ArrayList<>();

        // Refactor me
        data.add(new Data("basic", "Basic", R.drawable.main_basic));
        data.add(new Data("npc", "Npc", R.drawable.abigail_icon));
        data.add(new Data("seasons", "Calendar & Seasons", R.drawable.main_calendar));
        data.add(new Data("crop", "Crops & Fruits", R.drawable.parsnip));
        data.add(new Data("items", "Items", R.drawable.main_items));
        data.add(new Data("resources", "Resources & Minerals", R.drawable.coal));
        data.add(new Data("food", "Food", R.drawable.food));
        data.add(new Data("fishes", "Fish", R.drawable.main_fish));
        data.add(new Data("animals", "Animals", R.drawable.cow));
        data.add(new Data("boots", "Boots", R.drawable.boots));
        data.add(new Data("hats", "Hats", R.drawable.hats));
        data.add(new Data("map", "Map", R.drawable.main_map));
        data.add(new Data("links", "Useful links", R.drawable.url));


     //   data.add(new Data("giveaway", "Giveaway", android.R.drawable.ic_menu_compass));
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_share) {
            Amplitude.getInstance().logEvent("SHARING_APP");
            shareApp();
        } else if (id == R.id.nav_rate) {
            Amplitude.getInstance().logEvent("RATE_APP");
            showGooglePlayStore();
        } else if (id == R.id.nav_feedback) {
            Amplitude.getInstance().logEvent("SEND_FEEDBACK");
            sendFeedback();
        } else if(id == R.id.nav_form) {
            //todo open google form
            saveGoogleFormPref();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://goo.gl/forms/t6RGzRBIAM"));
            startActivity(browserIntent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void sendFeedback() {

        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.teddyEmail)});
        Email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.FeedbackOrBug));
        Email.putExtra(Intent.EXTRA_TEXT, getString(R.string.deviceInfo) + android.os.Build.MODEL + " (" + Build.VERSION.RELEASE + ")" +
                getString(R.string.dear_dev));
        startActivity(Intent.createChooser(Email, getString(R.string.sendFeedback)));
    }

    private void shareApp() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "https://play.google.com/store/apps/details?id=" + this.getPackageName();
        String subjectText = getString(R.string.look_at_this_app);
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subjectText);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_via)));
    }

    public void showGooglePlayStore() {

        try {
            //Open google play market
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + this.getPackageName())));
        } catch (ActivityNotFoundException e) {
            //if Google play market is not found and throws exception
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + this.getPackageName())));
        } catch (Exception e) {
            //Error message
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.showGooglePlayStoreErrorMessage).show();
        }
    }

    @Override
    public void itemClicked(View viewm, int pos) {
        Log.d("itemClicked" + data.get(pos).itemType + " " + data.get(pos).getItemType(), LOG_TAG);


        String itemTypeClickedOn = data.get(pos).getItemType();
        Amplitude.getInstance().logEvent("itemClicked" + itemTypeClickedOn);

        if (itemTypeClickedOn == "giveaway") {
            Intent listActivity = new Intent(this, GiveAwayActivity.class);
            startActivity(listActivity);
        }
        if (itemTypeClickedOn == "fishes" || itemTypeClickedOn == "animals" || itemTypeClickedOn == "boots" || itemTypeClickedOn == "hats") {

            Log.d("List!", LOG_TAG);
            Intent listActivity = new Intent(this, WebActivity.class);
            listActivity.putExtra("itemType", itemTypeClickedOn);

            startActivity(listActivity);


        } else if (itemTypeClickedOn == "npc" || itemTypeClickedOn == "food" || itemTypeClickedOn == "crop" || itemTypeClickedOn == "resources") {
            Log.d("List!", LOG_TAG);
            Intent listActivity = new Intent(this, SearchListActivity.class);
            listActivity.putExtra("itemType", itemTypeClickedOn);
            startActivity(listActivity);
        } else if (itemTypeClickedOn != "map" && itemTypeClickedOn != "calendar") {
            Log.d("List!", LOG_TAG);
            Intent listActivity = new Intent(this, ListActivity.class);
            listActivity.putExtra("itemType", itemTypeClickedOn);
            startActivity(listActivity);
        } else if (itemTypeClickedOn.toLowerCase() == "map" || itemTypeClickedOn.toLowerCase() == "calendar") {
            Log.d("Pictures!!", LOG_TAG);
            Intent listActivity = new Intent(this, MapActivity.class);
            listActivity.putExtra("itemType", itemTypeClickedOn);
            startActivity(new Intent(listActivity));
        }

    }
}

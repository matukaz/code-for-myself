package com.teddydeveloper.stardewvalleywiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.amplitude.api.Amplitude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teddydeveloper.stardewvalleywiki.Artifacts.DataDetailsActivity;
import com.teddydeveloper.stardewvalleywiki.Json.Json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.teddydeveloper.stardewvalleywiki.Models.DataFiles.ARTIFACTS;
import static com.teddydeveloper.stardewvalleywiki.Models.DataFiles.RINGS;

/**
 * A placeholder fragment containing a simple view.
 */
public class SearchListActivityFragment extends Fragment implements SearchDataAdapter.SearchDataAdapterClickListener, SearchView.OnQueryTextListener {

    private final String LOG_TAG = SearchListActivityFragment.class.getSimpleName();

    public SearchListActivityFragment() {
    }

    //TODO delete this
    private List<Data> data;
    View view;
    RecyclerView recyclerView;
    Toolbar toolbar;

    private List<?> artifactses = null;

    SearchDataAdapter adapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_search,
                container, false);

        setHasOptionsMenu(true);

        initalizeList();
        return view;
    }

    private void initalizeList() {
        initializeData();


        // RecyclerView can perform several optimizations if it can know in advance that changes in adapter content cannot change the size of the RecyclerView itself.
        recyclerView = (RecyclerView) view.findViewById(R.id.dataRecyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter2 = new SearchDataAdapter(data);

        //This might be bad code
        adapter2.setmDataAdapterClickListener(this);
        recyclerView.setAdapter(adapter2);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_search, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
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

        // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){


        //TODO recode it, not good practice
        String itemType = getActivity().getIntent().getExtras().getString("itemType");
        setTitle(toTitleCase(itemType));
        data = new ArrayList<>();

        if(itemType.toLowerCase().equals("npc")) {
            Log.d("NPC!!!" +itemType.toLowerCase(), LOG_TAG);
            String[] npcList = {"Abigail","Alex","Elliott","Harvey","Sam","Sebastian","Shane","Abigail","Emily","Haley","Leah","Maru","Penny","Caroline","Clint","Demetrius","Dwarf","Evelyn","George","Gus","Jas","Jodi","Kent","Krobus","Lewis","Linus","Marnie","Pam","Pierre","Robin","Sandy","Vincent","Willy","Wizard","Bouncer","Gil","Governor","Grandpa","Gunther","Henchman","Marlon","Morris","Mr._Qi"};

            for(String npc: npcList) {
                String json = Json.JSONFileToString(getContext(), "json/npc/"+ npc +".json");
                jsonToDataObjectNpc(json);
            }

            for(Data item: data) {
                item.setPhotoId(getContext().getResources().getIdentifier(item.getImage().toLowerCase(), "drawable", getContext().getPackageName()));
            }

        } else if(itemType.toLowerCase().equals("food")) {
            data.add(new Data("Food", "Algae Soup", R.drawable.algae_soup));
            data.add(new Data("Food", "Artichoke Dip", R.drawable.artichoke_dip));
            data.add(new Data("Food", "Blueberry Tart", R.drawable.blueberry_tart));
            data.add(new Data("Food", "Baked Fish", R.drawable.baked_fish));
            data.add(new Data("Food", "Bean Hotpot", R.drawable.bean_hotpot));
            data.add(new Data("Food", "Blackberry Cobbler", R.drawable.blackberry_cobbler));
            data.add(new Data("Food", "Bread", R.drawable.bread));
            data.add(new Data("Food", "Bruschetta", R.drawable.bruschetta));
            data.add(new Data("Food", "Carp Surprise", R.drawable.carp_surprise));
            data.add(new Data("Food", "Cheese Cauliflower", R.drawable.cheese_cauliflower));
            data.add(new Data("Food", "Chocolate Cake", R.drawable.chocolate_cake));
            data.add(new Data("Food", "Chowder", R.drawable.chowder));
            data.add(new Data("Food", "Coleslaw", R.drawable.coleslaw));
            data.add(new Data("Food", "Complete Breakfast", R.drawable.complete_breakfast));
            data.add(new Data("Food", "Cookie", R.drawable.cookie));
            data.add(new Data("Food", "Crab Cakes", R.drawable.crab_cakes));
            data.add(new Data("Food", "Cranberry Candy", R.drawable.cranberry_candy));
            data.add(new Data("Food", "Cranberry Sauce", R.drawable.cranberry_sauce));
            data.add(new Data("Food", "Crispy Bass", R.drawable.crispy_bass));
            data.add(new Data("Food", "Dish O The Sea", R.drawable.dish_o_the_sea));
            data.add(new Data("Food", "Eggplant Parmesan", R.drawable.eggplant_parmesan));
            data.add(new Data("Food", "Escargot", R.drawable.escargot));
            data.add(new Data("Food", "Farmers Lunch", R.drawable.farmers_lunch));
            data.add(new Data("Food", "Fiddlehead Risotto", R.drawable.fiddlehead_risotto));
            data.add(new Data("Food", "Fish Stew", R.drawable.fish_stew));
            data.add(new Data("Food", "Fish Taco", R.drawable.fish_taco));
            data.add(new Data("Food", "Fried Calamari", R.drawable.fried_calamari));
            data.add(new Data("Food", "Fried Eel", R.drawable.fried_eel));
            data.add(new Data("Food", "Fried Egg", R.drawable.fried_egg));
            data.add(new Data("Food", "Fried Mushroom", R.drawable.fried_mushroom));
            data.add(new Data("Food", "Fruit Salad", R.drawable.fruit_salad));
            data.add(new Data("Food", "Glazed Yams", R.drawable.glazed_yams));
            data.add(new Data("Food", "Hashbrowns", R.drawable.hashbrowns));
            data.add(new Data("Food", "Ice Cream", R.drawable.ice_cream));
            data.add(new Data("Food", "Lobster Bisque", R.drawable.lobster_bisque));
            data.add(new Data("Food", "Lucky Lunch", R.drawable.lucky_lunch));
            data.add(new Data("Food", "Maki Roll", R.drawable.maki_roll));
            data.add(new Data("Food", "Maple Bar", R.drawable.maple_bar));
            data.add(new Data("Food", "Miners Treat", R.drawable.miners_treat));
            data.add(new Data("Food", "Omelet", R.drawable.omelet));
            data.add(new Data("Food", "Pale Broth", R.drawable.pale_broth));
            data.add(new Data("Food", "Pancakes", R.drawable.pancakes));
            data.add(new Data("Food", "Parsnip Soup", R.drawable.parsnip_soup));
            data.add(new Data("Food", "Pepper Poppers", R.drawable.pepper_poppers));
            data.add(new Data("Food", "Pink Cake", R.drawable.pink_cake ));
            data.add(new Data("Food", "Pizza", R.drawable.pizza));
            data.add(new Data("Food", "Plum Pudding", R.drawable.plum_pudding));
            data.add(new Data("Food", "Pumpkin Pie", R.drawable.pumpkin_pie));
            data.add(new Data("Food", "Poppyseed Muffin", R.drawable.poppyseed_muffin));
            data.add(new Data("Food", "Pumpkin Soup", R.drawable.pumpkin_soup));
            data.add(new Data("Food", "Red Plate", R.drawable.red_plate));
            data.add(new Data("Food", "Radish Salad", R.drawable.radish_salad));
            data.add(new Data("Food", "Rhubarb Pie", R.drawable.rhubarb_pie));
            data.add(new Data("Food", "Rice Pudding", R.drawable.rice_pudding));
            data.add(new Data("Food", "Roasted Hazelnuts", R.drawable.roasted_hazelnuts));
            data.add(new Data("Food", "Roots Platter", R.drawable.roots_platter));
            data.add(new Data("Food", "Salad", R.drawable.salad));
            data.add(new Data("Food", "Salmon Dinner", R.drawable.salmon_dinner));
            data.add(new Data("Food", "Sashimi", R.drawable.sashimi));
            data.add(new Data("Food", "Spaghetti", R.drawable.spaghetti));
            data.add(new Data("Food", "Spicy Eel", R.drawable.spicy_eel));
            data.add(new Data("Food", "Stir Fry", R.drawable.stir_fry));
            data.add(new Data("Food", "Strange Bun", R.drawable.strange_bun));
            data.add(new Data("Food", "Stuffing", R.drawable.stuffing));
            data.add(new Data("Food", "Super Meal", R.drawable.super_meal));
            data.add(new Data("Food", "Survival Burger", R.drawable.survival_burger));
            data.add(new Data("Food", "Tom Kha Soup", R.drawable.tom_kha_soup));
            data.add(new Data("Food", "Tortilla", R.drawable.tortilla));
            data.add(new Data("Food", "Trout Soup", R.drawable.trout_soup));
            data.add(new Data("Food", "Vegetable Medley", R.drawable.vegetable_medley));
        }else if(itemType.toLowerCase().equals("crop")) {

            setTitle("Crops & Fruits");
            data.add(new Data("crop","Amaranth", R.drawable.amaranth, "Season: Fall"));
            data.add(new Data("crop","Ancient Fruit", R.drawable.ancient_fruit, "Season: Spring. Summer, Fall"));
            data.add(new Data("crop","Artichoke", R.drawable.artichoke, "Season: Fall"));
            data.add(new Data("crop","Beet", R.drawable.beet, "Season: Fall"));
            data.add(new Data("crop","Blue Jazz", R.drawable.blue_jazz));
            data.add(new Data("crop","Blueberry", R.drawable.blueberry));
            data.add(new Data("crop","Bok Choy", R.drawable.bok_choy));
            data.add(new Data("crop","Cauliflower", R.drawable.cauliflower));
            data.add(new Data("crop","Corn", R.drawable.corn));
            data.add(new Data("crop","Cranberries", R.drawable.cranberries));
            data.add(new Data("crop","Eggplant", R.drawable.eggplant));
            data.add(new Data("crop","Fairy Rose", R.drawable.fairy_rose));
            data.add(new Data("crop","Garlic", R.drawable.garlic));
            data.add(new Data("crop","Grape", R.drawable.grape));
            data.add(new Data("crop","Green Bean", R.drawable.green_bean));
            data.add(new Data("crop","Hops", R.drawable.hops));
            data.add(new Data("crop","Hot Pepper", R.drawable.hot_pepper));
            data.add(new Data("crop","Kale", R.drawable.kale));
            data.add(new Data("crop","Melon", R.drawable.melon));
            data.add(new Data("crop","Parsnip", R.drawable.parsnip));
            data.add(new Data("crop","Poppy", R.drawable.poppy));
            data.add(new Data("crop","Potato", R.drawable.potato));
            data.add(new Data("crop","Pumpkin", R.drawable.pumpkin));
            data.add(new Data("crop","Radish", R.drawable.radish));
            data.add(new Data("crop","Red Cabbage", R.drawable.red_cabbage));
            data.add(new Data("crop","Rhubarb", R.drawable.rhubarb));
            data.add(new Data("crop","Starfruit", R.drawable.starfruit));
            data.add(new Data("crop","Strawberry", R.drawable.strawberry));
            data.add(new Data("crop","Summer Spangle", R.drawable.summer_spangle));
            data.add(new Data("crop","Sunflower", R.drawable.sunflower));
            data.add(new Data("crop","Sweet Gem Berry", R.drawable.sweet_gem_berry));
            data.add(new Data("crop","Tomato", R.drawable.tomato));
            data.add(new Data("crop","Tulip", R.drawable.tulip));
            data.add(new Data("crop","Wheat", R.drawable.wheat));
            data.add(new Data("crop","Yam", R.drawable.yam));
            data.add(new Data("crop","Apricot", R.drawable.apricot));
            data.add(new Data("crop","Cherry", R.drawable.cherry));
            data.add(new Data("crop","Orange", R.drawable.orange));
            data.add(new Data("crop","Peach", R.drawable.peach));
            data.add(new Data("crop","Apple", R.drawable.apple));
            data.add(new Data("crop","Pomegranate", R.drawable.pomegranate));

        }

        else if(itemType.toLowerCase().equals("resources")){

            setTitle("Resources & Minerals");
            data.add(new Data("resources","Coal", R.drawable.coal));
            data.add(new Data("resources","Copper Ore", R.drawable.copper_ore));
            data.add(new Data("resources","Gold Ore", R.drawable.gold_ore));
            data.add(new Data("resources","Iron Ore", R.drawable.iron_ore));
            data.add(new Data("resources","Iridium Ore", R.drawable.iridium_ore));
            data.add(new Data("resources","Copper Bar", R.drawable.copper_bar));
            data.add(new Data("resources","Gold Bar", R.drawable.gold_bar));
            data.add(new Data("resources","Iron Bar", R.drawable.iron_bar));
            data.add(new Data("resources","Iridium Bar", R.drawable.iridium_bar));
            data.add(new Data("resources","Refined Quartz", R.drawable.refined_quartz));
            data.add(new Data("resources","Hematite", R.drawable.hematite));
            data.add(new Data("resources","Amethyst", R.drawable.amethyst));
            data.add(new Data("resources","Lemon Stone", R.drawable.lemon_stone));
            data.add(new Data("resources","Frozen Tear", R.drawable.frozen_tear));
            data.add(new Data("resources","Helvite", R.drawable.helvite));
            data.add(new Data("resources","Jasper", R.drawable.jasper));
            data.add(new Data("resources","Granite", R.drawable.granite));
            data.add(new Data("resources","Obsidian", R.drawable.obsidian));
            data.add(new Data("resources","Dolomite", R.drawable.dolomite));
            data.add(new Data("resources","Malachite", R.drawable.malachite));
            data.add(new Data("resources","Aerinite", R.drawable.aerinite));
            data.add(new Data("resources","Marble", R.drawable.marble));
            data.add(new Data("resources","Fairy Stone", R.drawable.fairy_stone));
            data.add(new Data("resources","Lunarite", R.drawable.lunarite));
            data.add(new Data("resources","Basalt", R.drawable.basalt));
            data.add(new Data("resources","Fluorapatite", R.drawable.fluorapatite));
            data.add(new Data("resources","Baryte", R.drawable.baryte));
            data.add(new Data("resources","Ocean Stone", R.drawable.ocean_stone));
            data.add(new Data("resources","Esperite", R.drawable.esperite));
            data.add(new Data("resources","Alamite", R.drawable.alamite));
            data.add(new Data("resources","Jagoite", R.drawable.jagoite));
            data.add(new Data("resources","Neptunite", R.drawable.neptunite));
            data.add(new Data("resources","Kyanite", R.drawable.kyanite));
            data.add(new Data("resources","Fire Opal", R.drawable.fire_opal));
            data.add(new Data("resources","Jamborite", R.drawable.jamborite));
            data.add(new Data("resources","Bixite", R.drawable.bixite));
            data.add(new Data("resources","Aquamarine", R.drawable.aquamarine));
            data.add(new Data("resources","Limestone", R.drawable.limestone));
            data.add(new Data("resources","Calcite", R.drawable.calcite));
            data.add(new Data("resources","Jade", R.drawable.jade));
            data.add(new Data("resources","Fire Quartz", R.drawable.fire_quartz));
            data.add(new Data("resources","Diamond", R.drawable.diamond));
            data.add(new Data("resources","Geminite", R.drawable.geminite));
            data.add(new Data("resources","Nekoite", R.drawable.nekoite));
            data.add(new Data("resources","Earth Crystal", R.drawable.earth_crystal));
            data.add(new Data("resources","Mudstone", R.drawable.mudstone));
            data.add(new Data("resources","Celestine", R.drawable.celestine));
            data.add(new Data("resources","Opal", R.drawable.opal));
            data.add(new Data("resources","Emerald", R.drawable.emerald));
            data.add(new Data("resources","Orpiment", R.drawable.orpiment));
            data.add(new Data("resources","Petrified Slime", R.drawable.petrified_slime));
            data.add(new Data("resources","Prismatic Shard", R.drawable.prismatic_shard));
            data.add(new Data("resources","Pyrite", R.drawable.pyrite));
            data.add(new Data("resources","Quartz", R.drawable.quartz));
            data.add(new Data("resources","Ruby", R.drawable.ruby));
            data.add(new Data("resources","Sandstone", R.drawable.sandstone));
            data.add(new Data("resources","Soapstone", R.drawable.soapstone));
            data.add(new Data("resources","Star Shards", R.drawable.star_shards));
            data.add(new Data("resources","Slate", R.drawable.slate));
            data.add(new Data("resources","Thunder Egg", R.drawable.thunder_egg));
            data.add(new Data("resources","Tigerseye", R.drawable.tigerseye));
            data.add(new Data("resources","Topaz", R.drawable.topaz));
        }
        else if(itemType.toLowerCase().equals(ARTIFACTS.getName())){
            Log.d("ARTIFACTS!!!" +itemType.toLowerCase(), LOG_TAG);
            String json = Json.JSONFileToString(getContext(), ARTIFACTS.getJsonFileLocation());
            jsonToDataObject(json);
        }
        else if(itemType.toLowerCase().equals(RINGS.getName())){
            Log.d("RINGS!!!" +itemType.toLowerCase(), LOG_TAG);
            String json = Json.JSONFileToString(getContext(), "json/Rings.json");
            jsonToDataObject(json);
        }
    }

    private void jsonToDataObject(String json) {
        // JSON String to object
        ObjectMapper mapper = new ObjectMapper();
        // delete me and refactor me to function
        try {
            data = mapper.readValue(json, new TypeReference<List<Data>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Data item: data) {
            item.setPhotoId(getContext().getResources().getIdentifier(item.getImage().toLowerCase(), "drawable", getContext().getPackageName()));
        }
    }

    private void jsonToDataObjectNpc(String json) {
        // JSON String to object
        ObjectMapper mapper = new ObjectMapper();
        // delete me and refactor me to function
        try {
           List<Data> tempData = mapper.readValue(json, new TypeReference<List<Data>>(){});
            data.addAll(tempData);
        } catch (IOException e) {
            e.printStackTrace();
        }

}
    @Override
    public void itemClicked(View view, int position) {
        Log.d("itemClicked" + view.toString(),LOG_TAG);
        String itemTypeClickedOn = adapter2.getData().get(position).getItemType();
        String title = adapter2.getData().get(position).getTitle();
        Amplitude.getInstance().logEvent("itemClicked" + itemTypeClickedOn);

        switch (adapter2.getData().get(position).getItemType().toLowerCase()) {
            case "artifacts":
                Intent intent = new Intent(getActivity(), DataDetailsActivity.class);
                intent.putExtra("data", adapter2.getData().get(position));
                startActivity(intent);
                break;
            case "rings":
                Intent ringsIntent = new Intent(getActivity(), DataDetailsActivity.class);
                ringsIntent.putExtra("data", adapter2.getData().get(position));
                startActivity(ringsIntent);
                break;
        /**    case "npc":
                Intent npcintent = new Intent(getActivity(), DataDetailsActivity.class);
                npcintent.putExtra("data", adapter2.getData().get(position));
                startActivity(npcintent);
                break; **/
            default:
                Log.d(LOG_TAG, "ITEM WAS: " + adapter2.getData().get(position).toString().toLowerCase());
                Intent webActivity = new Intent(getActivity(), WebActivity.class);
                webActivity.putExtra("itemType", itemTypeClickedOn);
                webActivity.putExtra("title", title);
                startActivity(webActivity);
                break;
        }
    }

    public void setTitle(String title) {
        getActivity().setTitle(title);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("onQueryTextChange " + newText, LOG_TAG);
        final List<Data> filteredModelList = filter(data, newText);
        adapter2.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
        return true;
    }

    private List<Data> filter(List<Data> models, String query) {
        query = query.toLowerCase();

        final List<Data> filteredModelList = new ArrayList<>();
        for (Data model : models) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}


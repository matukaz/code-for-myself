package com.teddydeveloper.stardewvalleywiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amplitude.api.Amplitude;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListActivityFragment extends Fragment implements DetailedAdapter.DetailedAdapterClickListener {

    private final String LOG_TAG = ListActivityFragment.class.getSimpleName();

    public ListActivityFragment() {
    }

    //TODO delete this
    private List<Data> data;
    View view;
    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list,
                container, false);

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
        DetailedAdapter adapter2 = new DetailedAdapter(data);
        adapter2.setmDetailedAdapterClickListener(this);
        recyclerView.setAdapter(adapter2);


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
        if(itemType.toLowerCase().equals("basic")) {


           //data.add(new Data("Tools", "Basic", R.drawable.player_icon));

           // data.add(new Data("Tools", "Hoe", R.drawable.player_icon, "<b>Cost</b>: starter tool<br><b>Ingredients:</b> Copper bar (5) <br>Increases maximum area of effect to 3 tiles, in a line in front of you."));
            data.add(new Data("player", "The player",R.drawable.player_icon,"The Player is the character you create and play as.\n" +
                    "\n" +
                    "Upon starting the game, you will be prompted to create your character, being able to choose hair color, eye color, shirt color, name, pet preference, favorite thing and their gender.\n" +
                    "\n" +
                    "The appearance and gender selected at the beginning of the game has no bearing on gameplay. Gender selection doesn't limit who you can romance or any other gameplay content.\n" +
                    "\n" +
                    "Character appearance is also not permanent. Players can unlock the ability to change their appearance in later game however you cannot change your gender.\n" +
                    "\n" +
                    "There are a wide variety of Status Effects the player can become effected by during play."));

            data.add(new Data("health", "Health",R.drawable.health_icon,"Health is measured by a bar next to the players energy bar in the bottom right corner of the UI. The Health bar is only visible while inside of The Mines or when the player has been hurt. Outside of the Mines the only way to lose Health is by getting hit by the train on the Railroad or by the slime in the Secret Woods. Health can be replenished by consuming food and by sleeping. Once the players health reaches zero, he or she will pass out after which he or she will be woken up by Linus in the Entry room of the mine or in their bedroom."));

            data.add(new Data("energy", "Energy",R.drawable.energy_icon,"Energy is consumed by using tools, fishing, and combat. It can be replenished by a variety of consumables and by visiting the Spa. Eating a Stardrop fruit will permanently increase your maximum Energy.\n" +
                    "Sleeping at the end of the day also restores player energy. If the player is exhausted when they go to bed they'll wake up in the morning with their energy level only partially restored."));

            data.add(new Data("skills", "Skills",R.drawable.skills_icon,"Skills are player attributes which are leveled up through the use of specific tools or actions. Increasing skill will increase the proficiency of certain tools, and unlock helpful and unique crafting recipes. Each skill has ten levels, and at level 5 and level 10 players will select from one of two specific buffs to specialize into. These specializations take effect immediately; for example, the Level 5 Fisher specialization will increase the selling price of fish put into the shipping box the previous night.\n" +
                    "\n" +
                    "A variety of skills can be temporarily improved by eating specific foods. These skills will appear as a buff icon next to the player clock in the user interface. Skills can be viewed in the player skill tab in the game pause menu."));

            data.add(new Data("day cycle", "Day Cycle",R.drawable.time_icon,"The day cycle is a period of 18 hours in game (or 13.5 minutes real time; 1 in-game hour is 45 seconds) from 6:00AM to 12:00AM (Midnight). Past midnight, the player's character becomes tired and in need of sleep. However, a player can stay awake until 2:00AM, after which their character will pass out. If a player passes out they will wake up in bed, having been dragged there by, first, the town's doctor, and then after that a Joja member, both of which take some of the player's money as a call out fee (the details of this transaction will appear as a letter in the player's letter box).\n" +
                    "\n" +
                    "It is considered nighttime after 6:00PM, which makes it possible to catch night time fish. This being said, it doesn't become dark until 8:00PM.\n" +
                    "\n" +
                    "The game saves only after the player has gone to bed, commencing the end of the day.\n" +
                    "\n" +
                    "Sleep will restore the player's Energy and Health to full if they go to bed before 2:00AM and are not exhausted, and to half if they pass out or are exhausted."));

            data.add(new Data("shipping", "Shipping box",R.drawable.shippingbox, "Shipping allows the player to put various goods into the shipping box located near their mailbox. Gold is received the following morning.\n" +
                    "\n" +
                    "Gold star quality items have the highest value, followed by items with silver star quality, followed by items with no stars. The amount of money received from shipping is the same as selling to a merchant. The only difference is that merchants make payment immediately.\n" +
                    "\n" +
                    "With the exception of Artifacts, items can only be added to the Collections tab through the use of the shipping box.\n" +
                    "\n" +
                    "In order to ship an item, either walk up to the box and drag the item from your inventory to the shipping box, click on the box and select what to ship from the menu, or select it from the inventory bar, and right click the box. If a mistake is made and the wrong item is put in, the player can right-click the box and the last item added will be displayed, which the player can then drag back to their inventory."));




        }
        else if(itemType.toLowerCase().equals("items")){


             data.add(new Data("Tools", "Hoe", R.drawable.tool_hoe, "<b>Cost</b>: Starter tool<br>Used to dig and till soil. Hold down left click to increase area of effect (Only applies after first upgrade). Hoes can also be used to dig up the worms seen around the world, to reveal hidden items.\n" +
                     "\n" +
                     "Can be used on iron ore to collect the ore tile itself for later placement."));

            data.add(new Data("Tools", "Copper Hoe", R.drawable.copper_hoe, "<b>Cost</b>: 2000g<br><b>Ingredients:</b> Copper bar (5) <br>Increases maximum area of effect to 3 tiles, in a line in front of you."));

            data.add(new Data("Tools", "Steel Hoe", R.drawable.steel_hoe, "<b>Cost</b>: 5000g<br><b>Ingredients:</b> Iron bar (5) <br>Increases maximum area of effect to 5 tiles, in a line in front of you."));

            data.add(new Data("Tools", "Gold Hoe", R.drawable.gold_hoe, "<b>Cost</b>: 10000g<br><b>Ingredients:</b> Gold bar (5) <br>Increases maximum area of effect to 3x3 tiles."));

            data.add(new Data("Tools", "Iridium Hoe", R.drawable.iridium_hoe, "<b>Cost</b>: 25000g<br><b>Ingredients:</b> Iridium bar (5) <br>Increases maximum area of effect to 6x3 tiles."));

            // Pickaxes

            data.add(new Data("Tools", "Pickaxe", R.drawable.tool_pickaxe, "<b>Cost</b>: Starter tool<br>Used to break stones."));
            data.add(new Data("Tools", "Copper Pickaxe", R.drawable.copper_pickaxe, "<b>Cost</b>: 2000g<br><b>Ingredients:</b> Copper bar (5)<br>Can mine bigger stones in the mines. If you do some mistake with hoe. Pickaxe can fix your hoe marks by hitting at the mark with pickaxe. "));

            data.add(new Data("Tools", "Steel Pickaxe", R.drawable.steel_pickaxe, "<b>Cost</b>: 5000g<br><b>Ingredients:</b> Iron bar (5)<br>Can mine bigger stones on the farm and access the dwarf."));
            data.add(new Data("Tools", "Gold Pickaxe", R.drawable.gold_pickaxe, "<b>Cost</b>: 10000g<br><b>Ingredients:</b> Gold bar (5)<br>Can break meteorite."));
            data.add(new Data("Tools", "Iridium Pickaxe", R.drawable.iridium_pickaxe, "<b>Cost</b>: 25000g<br><b>Ingredients:</b> Iridium bar (5)."));

            // Axes
            data.add(new Data("Tools", "Axe", R.drawable.tool_axe, "<b>Cost</b>: Starter tool<br>10 hits to chop down trees, 5 hits to chop down small stumps.<br> Used to chop down Trees for wood and sap and to gather Hardwood from a Large Stump or a Large Log.\n" +
                    "Can be used on iron ore to collect the ore tile itself for later placement. (Unintended behaviour, will be fixed in v1.05).\n" +
                    "Can also be used to harvest Giant Crops."));
            data.add(new Data("Tools", "Copper Axe", R.drawable.copper_axe, "<b>Cost</b>: 2000g<br><b>Ingredients:</b> Copper bar (5)<br>Able to chop large stumps. 2 less hits to chop down trees (8 hits), and 1 less hit to chop down small stumps (4 hits)."));

            data.add(new Data("Tools", "Steel Axe", R.drawable.steel_axe, "<b>Cost</b>: 5000g<br><b>Ingredients:</b> Iron bar (5)<br>Able to chop large logs. 2 less hits to chop down trees (6 hits), and 1 less hit to chop down small stumps (3 hits)."));
            data.add(new Data("Tools", "Gold Axe", R.drawable.gold_axe, "<b>Cost</b>: 10000g<br><b>Ingredients:</b> Gold bar (5)<br>2 less hits to chop down trees (4 hits), and 1 less hit to chop down small stumps (2 hits)."));
            data.add(new Data("Tools", "Iridium Axe", R.drawable.iridium_axe, "<b>Cost</b>: 25000g<br><b>Ingredients:</b> Iridium bar (5)."));

            // Watering
            data.add(new Data("Tools", "Watering Can", R.drawable.watering_can, "<b>Cost</b>: Starter tool<br> Used to water crops. It can be refilled at any water source. Hold down left click to increase area of effect (Only applies after first upgrade).\n" +
                    "Spending a day without watering your plants while upgrading will delay and harm your crops. To avoid this, always check the weather report for the next day and upgrade your watering can before a rainy day, or simply upgrade during the winter.\n" +
                    "Alternatively, using Retaining Soil can keep your crops watered for another day."));
            data.add(new Data("Tools", "Copper Watering Can", R.drawable.copper_watering_can, "Increases maximum water capacity. Increases maximum area of effect to 3 tiles, in a line in front of you."));
            data.add(new Data("Tools", "Steel Watering Can", R.drawable.steel_watering_can, "<b>Cost</b>: 5000g<br><b>Ingredients:</b> Iron bar (5)<br>Increases maximum water capacity. Increases maximum area of effect to 5 tiles, in a line in front of you."));
            data.add(new Data("Tools", "Gold Watering Can", R.drawable.gold_watering_can, "<b>Cost</b>: 10000g<br><b>Ingredients:</b> Gold bar (5)<br>Increases maximum area of effect to 3x3 tiles."));
            data.add(new Data("Tools", "Iridium Watering Can", R.drawable.iridium_watering_can, "<b>Cost</b>: 25000g<br><b>Ingredients:</b> Iridium bar (5).<br>Increases maximum area of effect to 6x3 tiles."));

            //Fishing poles
            data.add(new Data("Tools", "Bamboo Pole", R.drawable.bamboo_pole, "<b>Cost</b>: 500g<br><b>Location:</b> Given to You by Willy or can also buy from Willy's Fish Shop<br>Used to catch fish. Hold down left click to charge the cast, use WASD or the arrow keys to slightly adjust the placement of the hook."));
            data.add(new Data("Tools", "Fiberglass Rod", R.drawable.fiberglass_rod, "<b>Cost</b>: 1800g<br><b>Location:</b> Buy from Willy's Fish Shop<br><b>Improvements:</b> Able to use bait.<br><b>Requirements:</b> Fishing skill lvl 2"));
            data.add(new Data("Tools", "Iridium Rod", R.drawable.iridium_rod, "<b>Cost</b>: 7500g<br><b>Location:</b> Buy from Willy's Fish Shop<br><b>Improvements:</b> Able to use bait & tackle.<br><b>Requirements:</b> Fishing skill lvl 6"));

            //Other tools
            data.add(new Data("Tools", "Shears", R.drawable.shears, "<b>Cost</b>: 1000<br><b>Location:</b> Purchased from Marnie's Ranch.<br>Used for collecting wool from sheep."));
            data.add(new Data("Tools", "Scythe", R.drawable.scythe, "<b>Cost</b>: Starter Tool<br><b>Location:</b> You start with one.<br>Used to cut grass into hay."));
            data.add(new Data("Tools", "Milk Pail", R.drawable.milk_pail, "<b>Cost</b>: 1000<br><b>Location:</b> Purchased from Marnie's Ranch.<br>Gather milk from your animals."));
            data.add(new Data("Tools", "Copper Pan", R.drawable.copper_pan, "<b>Cost</b>: - <br><b>Location:</b> Unlocked via Community Center Bundles or Joja upgrades.<br>Use to gather ore from streams."));
            data.add(new Data("Tools", "24 Size backpack", R.drawable.small_backpack, "<b>Cost</b>: 2,000g<br><b>Location:</b> Purchased from Pierre's General Store after full inventory space upon receiving mail.<br>Increases Inventory Space"));
            data.add(new Data("Tools", "32 Size backpack", R.drawable.big_backpack, "<b>Cost</b>: 10,000g<br><b>Location:</b> Purchased from Pierre's General Store after full inventory space upon receiving mail.<br>Increases Inventory Space"));


        }  else if(itemType.toLowerCase().equals("map")){

            Intent intent = new Intent(getActivity(), MapActivity.class);
            startActivity(intent);

        } else if(itemType.toLowerCase().equals("links")){
            data.add(new Data("links","Wiki", R.drawable.url,"<br><a href=\"http://stardewvalleywiki.com/Stardew_Valley_Wiki//\">http://stardewvalleywiki.com/Stardew_Valley_Wiki/</a>"));

            data.add(new Data("links","Online Farm Planner!", R.drawable.url,"<br><a href=\"https://stardew.info/planner/\">https://stardew.info/planner</a>"));

            data.add(new Data("links","Reddit community", R.drawable.url,"<br><a href=\"https://www.reddit.com/r/StardewValley/\">https://www.reddit.com/r/StardewValley/</a>"));

            data.add(new Data("links","Tips for beginners - Reddit", R.drawable.url,"<br><a href=\"https://www.reddit.com/r/StardewValley/comments/47ttyx/stardew_valley_tips_megathread/\">https://www.reddit.com/r/StardewValley/comments/47ttyx/stardew_valley_tips_megathread/</a>"));

            data.add(new Data("links","Tips For Playing Stardew Valley", R.drawable.url,"<br><a href=\"http://kotaku.com/tips-for-playing-stardew-valley-1762894608/\">http://kotaku.com/tips-for-playing-stardew-valley-1762894608/</a>"));

        } else if(itemType.toLowerCase().equals("fishes") || itemType.toLowerCase().equals("animals") ){

            Log.d("itemClicked",LOG_TAG);
            String itemTypeClickedOn = itemType.toLowerCase();
            Amplitude.getInstance().logEvent("itemClicked" + itemTypeClickedOn);

                Intent webActivity = new Intent(getActivity(), WebActivity.class);
                webActivity.putExtra("itemType", itemTypeClickedOn);
                startActivity(webActivity);


/**
            data.add(new Data("fishes","Fishes",R.drawable.fish,"<h2><u>Beach</u></h2><h3>Spring</h3><ul><strong>Anchovies – </strong>24/7<br><strong>Eel</strong> - Night, when raining<br><strong>Halibut</strong> – 6.00 a.m to 11.00 a.m & 7.00 p.m to 2.00 a.m<br><strong>Herring</strong> - 24/7<br></ul><h3>Summer</h3><ul><strong>Crimsonfish (Legendary) </strong>- 24/7<br><strong>Halibut</strong> – 6.00 a.m to 11.00 a.m & 7.00 p.m to 2.00 a.m<br><strong>Octopus</strong> - 6.00 a.m to 1.00 p.m<br><strong>Pufferfish</strong> –12.00 p.m to 4.00 p.m<br><strong>Red Mullet</strong> – 6.00 a.m to 7.00 p.m<br><strong>Red Snapper</strong> – 6.00 a.m to 7.00 p.m, when raining<br><strong>Super Cucumber</strong> – 6.00 p.m to 2.00 a.m<br><strong>Tilapia</strong> – 6.00 a.m to 2.00 p.m<br><strong>Tuna</strong> – 6.00 p.m to 7.00 p.m<br></ul><h3>Fall</h3><ul><strong>Albacore</strong> - 6.00 a.m to 11.00 a.m & 6.00 p.m to 2.00 a.m<br><strong>Anchovies – </strong>24/7<br><strong>Eel</strong> - Night, when raining<br><strong>Tilapia</strong> – 6.00 a.m to 2.00 p.m<br><strong>Red Snapper</strong> – 6.00 a.m to 7.00 p.m, when snowing<br><strong>Sea Cucumber</strong> – 6.00 p.m to 2.00 a.m<br><strong>Super Cucumber</strong> – 6.00 p.m to 2.00 a.m<br></ul><h3>Winter</h3><ul><strong>Albacore</strong> - 6.00 a.m to 11.00 a.m & 6.00 p.m to 2.00 a.m<br><strong>Halibut</strong> – 24/7<br><strong>Herring</strong> - 24/7<br><strong>Red Mullet</strong> – 6.00 a.m to 7.00 p.m<br><strong>Sea Cucumber</strong> – 24/7<br><strong>Squid</strong> - 6.00 p.m to 2.00 a.m<br><strong>Tuna</strong> – 6.00 p.m to 7.00 p.m<br></ul><h3>All Year</h3><ul><strong>Green Algae</strong> – 24/7<br><strong>Sardines</strong> – 6.00 a.m to 7.00 p.m<br><strong>Seaweed</strong> – 24/7<br><strong>Trash</strong> – 24/7<br></ul><h2><u>Ponds</u></h2><h3>Spring</h3><ul><strong>Catfish – </strong>24/7, when raining<br><strong>Legend </strong>- 6:00 a.m. to 10:00 p.m., in the lake near Marnie's Ranch<br></ul><h3>Summer</h3><ul><strong>Pike</strong> – 24/7<br></ul><h3>Fall</h3><ul><strong>Catfish – </strong>24/7, when raining<br><strong>Angler </strong>(Legendary) - 24/7, in pond north of Joja Mart<br></ul><h3>Winter</h3><ul><strong>Catfish – </strong>24/7, when snowing<br><strong>Perch </strong>– 24/7<br><strong>Pike</strong> – 24/7<br></ul><h3>All Year</h3><ul><strong>Carp</strong> – 24/7<br><strong>Chub </strong>– 24/7<br><strong>Bullhead </strong>– 24/7<br><strong>Periwinkle</strong> – 24/7 (Crab Pot)<br><strong>Snail</strong> – 24/7 (Crab Pot)<br></ul><h2><u>River</u></h2><h3>Spring</h3><ul><strong>Catfish</strong>  – 6.00 a.m to 12.00 a.m, when raining<br><strong>Shad</strong> - 9.00 a.m to 2.00 a.m, when raining<br><strong>Smallmouth Bass</strong> – 24/7<br><strong>Sunfish</strong> – 6.00 a.m to 7.00 p.m, when sunny<br></ul><h3>Summer</h3><ul><strong>Dorado</strong> - 6.00 a.m to 7.00 p.m<br><strong>Rainbow Trout</strong> – 6.00 a.m to 7.00 p.m, when sunny<br><strong>Shad</strong> - 9.00 a.m to 2.00 a.m, when raining<br><strong>Sunfish</strong> – 6.00 a.m to 7.00 p.m, when sunny<br></ul><h3>Fall</h3><ul><strong>Catfish</strong> – 6.00 a.m to 12.00 a.m, when raining<br><strong>Lingcod</strong> – 24/7<br><strong>Salmon</strong> – 6.00 a.m to 7.00 p.m<br><strong>Shad</strong> – All Day, when raining<br><strong>Smallmouth Bass</strong> – 24/7<br><strong>Tiger Trout</strong> – 6.00 a.m to 7.00 p.m<br><strong>Walleye</strong> – 10.00 p.m to 2.00 a.m when raining<br></ul><h3>Winter</h3><ul><strong>Chub</strong> - Day<br><strong>Lingcod</strong> – 24/7<br><strong>Perch</strong> – Day or Night<br><strong>Pike</strong> – Day or Night<br><strong>Tiger Trout</strong> – 6.00 a.m to 7.00 p.m<br><strong>Walleye</strong> – 10.00 p.m to 2.00 a.m, when snowing<br></ul><h3>All Year</h3><ul><strong>Bream</strong> – 6.00 p.m to 2.00 a.m<br></ul><h2><u>Other Spots</u></h2><h3>Arrowhead Island</h3><ul><strong>Glacierfish (Legendary)</strong> - 6:00 a.m to 10:00 p.m, only during Winter<br></ul><h3>Desert</h3><ul><strong>Sandfish </strong>- Between 6:00 a.m. and 10:00 p.m; year-round<br><strong>Scorpion Carp </strong>- Between 6:00 a.m. and 10:00 p.m; year-round<br></ul><h3>Forest Pond - Secret Woods Pond</h3><ul><strong>Carp </strong>- 24/7, year-round<br><strong>Catfish </strong>- Mornings during Spring and Fall<br><strong>Walleye </strong>- Afternoon and Night during Fall<br><strong>Woodskip</strong> - 24/7, year-round<br></ul><h3>The Mines</h3><ul><strong>Ghostfish</strong> – Floors 20, 60 and 100; 24/7, year-round<br><strong>Ice Pip</strong> – Floor 60; 24/7, year-round<br><strong>Lava Eel</strong> – Floor 100; 24/7, year-round<br><strong>StoneFish</strong> – Floor 20; 24/7, year-round<br><strong>Sturgeon</strong> – Lake outside the mines; All Day, Summer and Winter only<br></ul><h3>Sewers</h3><ul><strong>Mutant Carp (Legendary)</strong> - 24/7, year-round<br></ul><h3>Lake outside of mines</h3><ul><strong>Largemouth bass</strong> - 6.00 a.m to 7.00 p.m, year-round<br></ul><br><header><h2>Crab Pot Fish</h2>Some fish are only catchable using a baited crab pot. All of these creatures can be used as components in the Crab Pot Bundle</header><h2>Ocean</h2><ul><strong>Lobster</strong><br><strong>Clam</strong><br><strong>Crab</strong><br><strong>Cockle</strong><br><strong>Mussel</strong><br><strong>Shrimp</strong><br><strong>Oyster</strong><br></ul><h2>Freshwater</h2><ul><strong>Crayfish</strong><br><strong>Snail</strong><br><strong>Periwinkle</strong><br></ul>"));
       */ }
        else if(itemType.toLowerCase().equals("seasons")){


            data.add(new Data("weather","Weather",R.drawable.sun,"<h2><u>Weather</u></h2>Weather changes day by day and dependent on the season the player is experiencing. The weather for the next day can be checked by viewing the TV screen and selecting 'Weather Report'.\n" +
                    "<h2>Seasons</h2> Each Season lasts 28 day cycles, with specific crops, forageables, and fish that can only be found during that season. Crops still standing out of season will wither and die, and fertilizer used on soil will expire. Seeds available in Pierre's General Store change each season. A villager's daily routines may change with a new season.\n" +
                    "After every seasons ends all of the crops that are planted, that do not follow into the season that is starting, will die.\n" +
                    "\nClick on any of the seasons below to find out more about specific season"));
            data.add(new Data("spring","Spring", R.drawable.spring,"click here to find more info"));

            data.add(new Data("summer","Summer", R.drawable.summer,"click here to find more info"));
            data.add(new Data("fall","Fall", R.drawable.fall,"click here to find more info"));
            data.add(new Data("winter","Winter", R.drawable.winter,"click here to find more info"));

        }

    }

    @Override
    public void itemClicked(View view, int position) {


        Log.d("itemClicked",LOG_TAG);
        String itemTypeClickedOn = data.get(position).getItemType();
        Amplitude.getInstance().logEvent("itemClicked" + itemTypeClickedOn);

        if(itemTypeClickedOn == "spring" || itemTypeClickedOn == "summer" || itemTypeClickedOn == "fall"|| itemTypeClickedOn == "winter" || itemTypeClickedOn == "fishes"|| itemTypeClickedOn == "animals"){

            Intent webActivity = new Intent(getActivity(), WebActivity.class);
            webActivity.putExtra("itemType", itemTypeClickedOn);
            startActivity(webActivity);


        }

    }



    public void setTitle(String title) {
        getActivity().setTitle(title);
    }
}


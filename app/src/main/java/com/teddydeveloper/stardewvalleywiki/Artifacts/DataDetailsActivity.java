package com.teddydeveloper.stardewvalleywiki.Artifacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teddydeveloper.stardewvalleywiki.Data;
import com.teddydeveloper.stardewvalleywiki.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DataDetailsActivity extends AppCompatActivity {

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.image)
    ImageView image;

    @Bind(R.id.description)
    TextView description;

    @Bind(R.id.location)
    TextView location;

    @Bind(R.id.price_layout)
    RelativeLayout priceLayout;

    @Bind(R.id.price_text)
    TextView priceText;

    @Bind(R.id.effectText)
    TextView effectText;

    @Bind(R.id.ingredientsText)
    TextView ingredientsText;

    private Data data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_artifact);
        ButterKnife.bind(this);

        data = (Data) getIntent().getSerializableExtra("data");
        if(!data.getItemType().toLowerCase().equals("npc")) {
            initalizeDetailView();
        }
    }

    private void initalizeDetailView() {



        // Probably don't need this.
    //   int imageId = getResources().getIdentifier(data.getImage().toLowerCase(), "drawable", this.getPackageName());

        title.setText(data.getTitle());
        description.setText(data.getDescription());
        image.setImageResource(data.getPhotoId());

        if(data.getItemLocation() != null){
            location.setVisibility(View.VISIBLE);
            location.setText("Location:\n" + data.getItemLocation());
        }


        if(data.getEffect()!= null){
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.effectLayout);
            linearLayout.setVisibility(View.VISIBLE);
            effectText.setText("Effect:\n" + data.getEffect());
        }

        if(data.getPrice() != null || data.getBuyingPrice() != null || data.getSellingPrice() != null){
            priceLayout.setVisibility(View.VISIBLE);
            if(data.getBuyingPrice() != null || data.getSellingPrice() != null){
                String buyingPrice = (data.getBuyingPrice() != null && !data.getBuyingPrice().isEmpty() ? "Buy: " + data.getBuyingPrice() :"");
                String sellingPrice = (data.getBuyingPrice() != null && !data.getBuyingPrice().isEmpty() ?  "Sell: " + data.getSellingPrice() : "");
                String price = buyingPrice +" "+ sellingPrice;
                priceText.setText(price);
            } else {
                priceText.setText(data.getPrice());
            }
        }

        if(data.getIngredients() != null){
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ingredientsLayout);
            linearLayout.setVisibility(View.VISIBLE);
            ingredientsText.setText("Ingredients:\n" + data.getIngredients());
        }
    }
}

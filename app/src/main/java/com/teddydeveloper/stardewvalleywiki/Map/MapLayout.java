package com.teddydeveloper.stardewvalleywiki.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teddydeveloper.stardewvalleywiki.R;


/**
 * Custom view that uses layout_map.xml
 */

public class MapLayout extends LinearLayout {

    private Context context;

    private ImageView mapIcon;
    private TextView mapTitle;
    private TextView mapDescription;

    public MapLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MapLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MapLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_map, this, true);




    }
}

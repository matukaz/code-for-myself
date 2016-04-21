package com.teddydeveloper.stardewvalleywiki;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class MapActivity extends Activity {



    String itemType;
    PhotoViewAttacher mAttacher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        itemType = this.getIntent().getStringExtra("itemType");
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(itemType);
        viewPager.setAdapter(adapter);
    }

    private class ImagePagerAdapter extends PagerAdapter {
        private int[] mImages;

        public ImagePagerAdapter(String itemType) {


            if(itemType.toLowerCase().equals("map")){
                mImages = new int[] {R.drawable.full_map
                };
            }

           else if(itemType.toLowerCase().equals("calendar")){
                mImages = new int[] {R.drawable.calendar_spring,
                        R.drawable.calendar_summer,
                        R.drawable.calendar_fall,
                        R.drawable.calendar_winter
                };
            }
        }

        @Override
        public int getCount() {
            return mImages.length;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = getBaseContext();
            ImageView imageView = new ImageView(context);
            int padding = context.getResources().getDimensionPixelSize(
                    R.dimen.padding_medium);
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageResource(mImages[position]);

            // it crashes
        //    mAttacher = new PhotoViewAttacher(imageView);
            //  mAttacher.update();


            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}

package com.padc.goldenmyanmartour.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WT on 9/5/2016.
 */
public class ImagesPagerAdapter extends PagerAdapter {
    private List<String> mImages;
    private LayoutInflater mInflater;

    public ImagesPagerAdapter(String[] images) {
        if (images == null) {
            mImages = new ArrayList<>();
        } else {
            mImages = new ArrayList<>(Arrays.asList(images));
        }
        mInflater = LayoutInflater.from(GMTApp.getContext());
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView ivAttraction = (ImageView) mInflater.inflate(R.layout.view_item_destination_image, container, false);

        String imageUrl = mImages.get(position);
        Glide.with(ivAttraction.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.gmtiicon)
                .error(R.drawable.gmtiicon)
                .into(ivAttraction);

        ((ViewPager) container).addView(ivAttraction);

        return ivAttraction;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}

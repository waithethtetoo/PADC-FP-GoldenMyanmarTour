package com.padc.goldenmyanmartour.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;

import java.util.List;

/**
 * Created by WT on 9/17/2016.
 */
public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<DestinationVO> destinationList;
    private ImageView ivDestOne, ivDestTwo;
    private TextView tvDestOne, tvDestTwo;
    Context context;

    public ListViewAdapter(List<DestinationVO> destinationList, Context context) {
        this.destinationList = destinationList;
        this.context = context;
        inflater = LayoutInflater.from(GMTApp.getContext());
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.list_item_design, parent, false);
        ivDestOne = (ImageView) convertView.findViewById(R.id.iv_dest_one);
        ivDestTwo = (ImageView) convertView.findViewById(R.id.iv_dest_two);
        tvDestOne = (TextView) convertView.findViewById(R.id.tv_dest_one);
        tvDestTwo = (TextView) convertView.findViewById(R.id.tv_dest_two);

        ivDestOne.setImageResource(R.drawable.bagan);
        ivDestTwo.setImageResource(R.drawable.mandalay);
        tvDestOne.setText("Bagan Bagan Bagna");
        tvDestTwo.setText("Mandalay Mandalay Mandalay");

        return convertView;
    }
}

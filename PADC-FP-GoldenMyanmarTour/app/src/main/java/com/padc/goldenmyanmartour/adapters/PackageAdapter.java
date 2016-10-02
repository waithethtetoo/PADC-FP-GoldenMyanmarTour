package com.padc.goldenmyanmartour.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.PackageVO;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;
import com.padc.goldenmyanmartour.views.holders.PackageViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WT on 9/8/2016.
 */
public class PackageAdapter extends BaseAdapter {
    private List<PackageVO> packageVOList;
    private LayoutInflater inflater;
    private PackageViewHolder.ControllerItem mController;

    public PackageAdapter(List<PackageVO> packageVOList, PackageViewHolder.ControllerItem mController) {
        if (packageVOList != null) {
            this.packageVOList = packageVOList;
        } else {
            packageVOList = new ArrayList<>();
        }
        inflater = LayoutInflater.from(GMTApp.getContext());
        this.mController = mController;
    }

    @Override
    public int getCount() {
        return 6;
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
        PackageViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_item_package, parent, false);
            viewHolder = new PackageViewHolder(convertView, mController);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PackageViewHolder) convertView.getTag();
        }
        viewHolder.bindData();
        return convertView;
    }

    public void setNewData(List<PackageVO> newPackage) {
        this.packageVOList = newPackage;
    }

}

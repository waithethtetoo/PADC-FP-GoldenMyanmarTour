package com.padc.goldenmyanmartour.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
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
            this.packageVOList = new ArrayList<>();
        }

        inflater = LayoutInflater.from(GMTApp.getContext());
        this.mController = mController;
    }

    // Search Action
    public List<PackageVO> setNewDataBySearchText(List<PackageVO> searchList, String searchText) {
        //Filter here
        List<PackageVO> filterList = new ArrayList<>();
        for (PackageVO searchPackage : searchList) {
            if (searchPackage.getPackageName().equalsIgnoreCase(searchText)) {
                filterList.add(searchPackage);
                Log.d(GMTApp.TAG, searchPackage.getPackageName());
            }
        }

        packageVOList = filterList;
        notifyDataSetChanged();//framework method
        return filterList;
    }

    @Override
    public int getCount() {
        return packageVOList.size();
    }

    @Override
    public PackageVO getItem(int position) {
        return packageVOList.get(position);
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
        viewHolder.bindData(getItem(position));
        return convertView;
    }

    public void setNewData(List<PackageVO> newPackage) {
        packageVOList = newPackage;
        notifyDataSetChanged();
    }

}

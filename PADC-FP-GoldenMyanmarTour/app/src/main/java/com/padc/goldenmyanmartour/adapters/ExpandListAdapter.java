package com.padc.goldenmyanmartour.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.ExpandGroup;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 * Created by WT on 9/15/2016.
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ExpandGroup> expandGroups;

    public ExpandListAdapter(Context context, ArrayList<ExpandGroup> expandGroups) {
        this.context = context;
        this.expandGroups = expandGroups;
    }

    @Override
    public int getGroupCount() {
        return expandGroups.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return expandGroups.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ExpandGroup group = (ExpandGroup) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.expand_group_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tv_saved_place_title);
        tv.setText(group.getName());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<DestinationVO> destList = expandGroups.get(groupPosition).getDestinationVOs();
        return destList.size();
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<DestinationVO> destList = expandGroups.get(groupPosition).getDestinationVOs();
        return destList.get(childPosition);

    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        DestinationVO destination = (DestinationVO) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expand_child_item, null);
        }
        TextView selection = (TextView) convertView.findViewById(R.id.tv_saved_place);
        selection.setText(destination.getName().toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return false;
    }
}

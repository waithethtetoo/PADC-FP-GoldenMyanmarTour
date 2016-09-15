package com.padc.goldenmyanmartour.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;


import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ExpandListAdapter;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.ExpandGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WT on 9/14/2016.
 */
public class PLanOwnRouteFragment extends Fragment {

    private ExpandListAdapter expandListAdapter;
    private ArrayList<ExpandGroup> ExpListItems;

    @BindView(R.id.expand_saved_places)
    ExpandableListView ExpandList;

    public static PLanOwnRouteFragment newInstance() {
        PLanOwnRouteFragment fragment = new PLanOwnRouteFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planownroute, container, false);
        ButterKnife.bind(this, view);
        ExpListItems = setGroup();
        expandListAdapter = new ExpandListAdapter(GMTApp.getContext(), ExpListItems);
        ExpandList.setAdapter(expandListAdapter);
        return view;
    }

    public ArrayList<ExpandGroup> setGroup() {
        String[] groupName = {"Saved Places"};
        String[] destName = {"Yangon", "Bagan", "Inle", "Pyin-Oo-Lwin"}; // book mark list 
        ArrayList<ExpandGroup> list = new ArrayList<ExpandGroup>();

        ArrayList<DestinationVO> ch_list;
        int size = 4;
        int j = 0;

        for (String group_name : groupName) {
            ExpandGroup gru = new ExpandGroup();
            gru.setName(group_name);

            ch_list = new ArrayList<DestinationVO>();
            for (; j < size; j++) {
                DestinationVO ch = new DestinationVO();
                ch.setName(destName[j]);
                ch_list.add(ch);
            }
            gru.setDestinationVOs(ch_list);
            list.add(gru);

            size = size + 4;
        }

        return list;
    }

}

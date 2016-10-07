package com.padc.goldenmyanmartour.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.BookMarkAdapter;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;
import com.padc.goldenmyanmartour.data.vo.BookmarkVO;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WT on 9/14/2016.
 */
public class PLanOwnRouteFragment extends Fragment {

    @BindView(R.id.sp_interested_places)
    Spinner spInterestedPlaces;

    @BindView(R.id.image_one)
    ImageView ivOne;
    @BindView(R.id.image_two)
    ImageView ivTwo;
    @BindView(R.id.image_three)
    ImageView ivThree;
    @BindView(R.id.image_four)
    ImageView ivFour;
    @BindView(R.id.image_fifth)
    ImageView ivFifth;

    @BindView(R.id.v_one)
    View vOne;
    @BindView(R.id.v_two)
    View vTwo;
    @BindView(R.id.v_three)
    View vThree;
    @BindView(R.id.v_four)
    View vFour;


    BookMarkAdapter adapter;
    List<String> names;

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.interested_places,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spInterestedPlaces.setAdapter(adapter);
        spInterestedPlaces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        ivOne.setVisibility(View.VISIBLE);
                        ivTwo.setVisibility(View.VISIBLE);
                        vOne.setVisibility(View.VISIBLE);


                        ivOne.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });
                        ivTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });

                        break;

                    case 2:
                        ivOne.setVisibility(View.VISIBLE);
                        ivTwo.setVisibility(View.VISIBLE);
                        ivThree.setVisibility(View.VISIBLE);

                        vOne.setVisibility(View.VISIBLE);
                        vTwo.setVisibility(View.VISIBLE);

                        ivOne.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });
                        ivTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });

                        ivThree.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });
                        break;

                    case 4:
                        ivOne.setVisibility(View.VISIBLE);
                        ivTwo.setVisibility(View.VISIBLE);
                        ivThree.setVisibility(View.VISIBLE);
                        ivFour.setVisibility(View.VISIBLE);

                        vOne.setVisibility(View.VISIBLE);
                        vTwo.setVisibility(View.VISIBLE);
                        vThree.setVisibility(View.VISIBLE);

                        ivOne.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });
                        ivTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });

                        ivThree.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });
                        ivFour.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });

                        break;
                    case 5:
                        ivOne.setVisibility(View.VISIBLE);
                        ivTwo.setVisibility(View.VISIBLE);
                        ivThree.setVisibility(View.VISIBLE);
                        ivFour.setVisibility(View.VISIBLE);
                        ivFifth.setVisibility(View.VISIBLE);

                        vOne.setVisibility(View.VISIBLE);
                        vTwo.setVisibility(View.VISIBLE);
                        vThree.setVisibility(View.VISIBLE);
                        vFour.setVisibility(View.VISIBLE);

                        ivOne.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });
                        ivTwo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });

                        ivThree.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });
                        ivFour.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });
                        ivFifth.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showBookMarkList();
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.image_one:
//                showBookMarkList();
//                break;
//            case R.id.image_two:
//                showBookMarkList();
//                break;
//            case R.id.image_three:
//                showBookMarkList();
//                break;
//        }
//    }


    public void showBookMarkList() {

        final Dialog dialog = new Dialog(GMTApp.getContext());
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.setContentView(R.layout.custom_popup_window);
        dialog.setTitle("Your Bookmark");

        GridView gvResult = (GridView) dialog.findViewById(R.id.gv_result);
        names = new ArrayList<>(); //bookmark list
        names.add("Pyin_Oo_Lwin");
        names.add("Bagan");
        names.add("Taunggyi");
        names.add("Inle Lake");
        adapter = new BookMarkAdapter(names);
        gvResult.setAdapter(adapter);
        gvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected place image will show here
                ivOne.setImageResource(R.drawable.mandalay);
                dialog.dismiss();

                ivTwo.setImageResource(R.drawable.inle);
                dialog.dismiss();
                ivThree.setImageResource(R.drawable.bagan);
                dialog.dismiss();

                ivFour.setImageResource(R.drawable.bagan_museum1);
                dialog.dismiss();

                ivFifth.setImageResource(R.drawable.ananda_pagoda_festival);
                dialog.dismiss();


            }
        });
        dialog.show();
    }

    @OnClick(R.id.btn_calculate)
    // calculate price by user choose places
    public void onCalculatePrice() {
        Toast.makeText(GMTApp.getContext(), "Price Calculate", Toast.LENGTH_SHORT).show();
    }
}

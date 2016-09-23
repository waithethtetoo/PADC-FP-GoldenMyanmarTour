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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
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
public class PLanOwnRouteFragment extends Fragment
        implements View.OnClickListener {

    @BindView(R.id.image_one)
    ImageView ivOne;
    @BindView(R.id.image_two)
    ImageView ivTwo;
    @BindView(R.id.image_three)
    ImageView ivThree;

    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;

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

        ivOne.setOnClickListener(this);
        ivTwo.setOnClickListener(this);
        ivThree.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_one:
                showBookMarkList();
                break;
            case R.id.image_two:
                showBookMarkList();
                break;
            case R.id.image_three:
                showBookMarkList();
                break;
        }
    }


    public void showBookMarkList() {

        Dialog dialog = new Dialog(GMTApp.getContext());
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
                tvOne.setText("Mandalay");



       /*         ivTwo.setImageResource(R.drawable.inle);
=======
                ivTwo.setImageResource(R.drawable.inle);
>>>>>>> 1. change fab setVisbility from INVISIBLE to VISIBLE at HomeActivity.java;
=======
       /*         ivTwo.setImageResource(R.drawable.inle);
>>>>>>> add comment at line number 123 to 128 at PlanOwnRoute.java;
=======
                ivTwo.setImageResource(R.drawable.inle);
>>>>>>> 1. change fab setVisbility from INVISIBLE to VISIBLE at HomeActivity.java;
                tvTwo.setText("Inle");

                ivThree.setImageResource(R.drawable.bagan);
                tvThree.setText("Bagan");
<<<<<<< 037983194aa024d9ec03f3af74bcb29336806d6f
<<<<<<< 4cabdce795aa1e810dfd294a830927b18b1fa21d
<<<<<<< afd2ca82146820f0ed191ecd63a02bfa826cf80a
*/

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

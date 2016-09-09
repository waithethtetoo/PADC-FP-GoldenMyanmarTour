package com.padc.goldenmyanmartour.views.holders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.activity.DestinationDetailActivity;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WT on 9/5/2016.
 */
public class DestinationViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_destination_name)
    TextView tvDestinationName;

    @BindView(R.id.tv_destination_desc)
    TextView tvDestinationDesc;

    @BindView(R.id.iv_destination)
    ImageView ivDestination;

    private DestinationVO mDestination;
    private ControllerDestinationItem mController;

    public DestinationViewHolder(View itemView, ControllerDestinationItem controller) {
        super();
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData() {
//        mDestination = destination;
//        tv_destination_name.setText(destination.getName());
//        tv_destination_desc.setText(destination.getDesc());
        tvDestinationName.setText("Bagan");
        tvDestinationDesc.setText("It is situated on the eastern bank of Ayeyarwaddy River and 688 km from Yangon. Bagan is one of the most remarkable archaeological sites in Asia with over 40000 ancient monuments built during 11- 13 century. It is also known as the centre of Myanmar Lacquer ware industry. Bagan was the capital of the first unified Empire of Anawrahta founded in 849 AD, and flourished from 1044 to 13th century.");

        Glide.with(ivDestination.getContext())
                .load(R.drawable.bagan)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivDestination);
    }

    @Override
    public void onClick(View v) {
//        mController.onTapDestination(mDestination, iv_destination);
        Intent intentToDestDetail = DestinationDetailActivity.newIntent("Bagan");
        intentToDestDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        GMTApp.getContext().startActivity(intentToDestDetail);
    }

    public interface ControllerDestinationItem {
        void onTapDestination(DestinationVO destinationVO, ImageView iv_destination);
    }
}

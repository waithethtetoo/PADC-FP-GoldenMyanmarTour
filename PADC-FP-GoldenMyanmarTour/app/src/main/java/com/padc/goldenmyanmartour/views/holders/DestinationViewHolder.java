package com.padc.goldenmyanmartour.views.holders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.activity.DestinationDetailActivity;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

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

    public void bindData(DestinationVO destinationVO) {
        mDestination = destinationVO;
        tvDestinationName.setText(destinationVO.getTitle());
        tvDestinationDesc.setText(destinationVO.getNoteToVisitor());

        String imageUrl = destinationVO.getDestination_photos()[0];
//        if (!imageUrl.contains(DestinationConstants.IMAGE_ROOT_DIR)) {
//            imageUrl = DestinationConstants.IMAGE_ROOT_DIR + imageUrl;
//        }
        Log.d("DestViewHolder", "imageUrl : " +  imageUrl);

        Glide.with(ivDestination.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivDestination);
    }

    @Override
    public void onClick(View v) {
        mController.onTapDestination(mDestination, ivDestination);
//        Intent intentToDestDetail = DestinationDetailActivity.newIntent("Bagan");
//        intentToDestDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        GMTApp.getContext().startActivity(intentToDestDetail);
    }

    public interface ControllerDestinationItem {
        void onTapDestination(DestinationVO destinationVO, ImageView iv_destination);
    }
}

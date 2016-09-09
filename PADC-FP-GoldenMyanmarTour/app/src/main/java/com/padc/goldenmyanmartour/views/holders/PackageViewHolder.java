package com.padc.goldenmyanmartour.views.holders;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.activity.PackageDetailActivity;
import com.padc.goldenmyanmartour.data.vo.PackageVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WT on 9/8/2016.
 */
public class PackageViewHolder implements View.OnClickListener {
    @BindView(R.id.iv_package)
    ImageView ivPackage;
    @BindView(R.id.tv_package_name)
    TextView tvPackageName;
//    @BindView(R.id.tv_package_price)
//    TextView tvPackagePrice;

    private PackageVO packageVO;
    private ControllerItem mController;

    public PackageViewHolder(View view, ControllerItem mController) {
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
        this.mController = mController;
    }

    public void bindData() {
//        mDestination = destination;
//        tv_destination_name.setText(destination.getName());
//        tv_destination_desc.setText(destination.getDesc());
        tvPackageName.setText("2 Days/1 Night From Kalaw to Inle Lake");
//        tvPackagePrice.setText("Price : 20,000 Ks");

        Glide.with(ivPackage.getContext())
                .load(R.drawable.inle)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivPackage);
    }

    @Override
    public void onClick(View v) {
        Intent intentToPackageDetail = PackageDetailActivity.newIntent("2 Days/1 Night From Kalaw to Inle Lake");
        intentToPackageDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        GMTApp.getContext().startActivity(intentToPackageDetail);
    }

    public interface ControllerItem {
        void onTapPackage(PackageVO packageVO, ImageView iv_package);
    }

}

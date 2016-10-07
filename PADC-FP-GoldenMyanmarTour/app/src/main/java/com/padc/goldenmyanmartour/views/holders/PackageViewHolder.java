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


    private PackageVO mPackageVO;
    private ControllerItem mController;

    public PackageViewHolder(View view, ControllerItem mController) {
        super();
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
        this.mController = mController;
    }

    public void bindData(PackageVO packageVO) {

        mPackageVO = packageVO;

        tvPackageName.setText(packageVO.getPackageName());

        String imageUrl = packageVO.getPhotos()[0];

        Glide.with(ivPackage.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.gmtiicon)
                .error(R.drawable.gmtiicon)
                .into(ivPackage);
    }

    @Override
    public void onClick(View v) {
        mController.onTapPackage(mPackageVO, ivPackage);
//        Intent intentToPackageDetail = PackageDetailActivity.newIntent("Yangon-Bago-Thanlyin-Yangon");
//        intentToPackageDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        GMTApp.getContext().startActivity(intentToPackageDetail);
    }

    public interface ControllerItem {
        void onTapPackage(PackageVO packageVO, ImageView iv_package);
    }

}

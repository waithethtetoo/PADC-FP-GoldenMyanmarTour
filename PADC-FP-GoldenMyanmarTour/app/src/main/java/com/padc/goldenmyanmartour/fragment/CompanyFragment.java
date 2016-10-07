package com.padc.goldenmyanmartour.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.PackageVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WT on 10/7/2016.
 */
public class CompanyFragment extends BaseFragment {

    @BindView(R.id.iv_company)
    ImageView ivCompany;

    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_company_address)
    TextView tvCompanyAddress;
    @BindView(R.id.tv_company_phone)
    TextView tvCompanyPhone;
    @BindView(R.id.tv_company_desc)
    TextView tvCompanyDesc;

    public static CompanyFragment newInstance() {
        CompanyFragment fragment = new CompanyFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        ButterKnife.bind(this, view);

        tvCompanyName.setText("GOLDEN MYANMAR TRAVELS & TOURS");
        tvCompanyAddress.setText("Room # 2-C,, Bld No. 393-395, City Shine Tower, Bo Aung Kyaw Street (Upper Block), Kyauk Tada Township, Yangon. The Republic of Union of Myanmar.");
        tvCompanyPhone.setText("+95-1 377 332" + "," + "+95-9 430 977 90");
        tvCompanyDesc.setText("Golden Myanmar Travels & Tours stands for five years and grows to learn from experiences. And we are keeping our exceptional service.  As our clients recognized that our services is positioned very carefully: they will be of extremely high quality, comfortable, informative and tailored to the client?s needs such that they will enable individuals to have a greater appreciation of the natural environment. And we are delighted to thank you for introducing us to so many of your friends. We would like to say our valued clients that the only one thing you should do after visiting our pages, is just drop a line to us and we would give you some ideas when planning your trip to Myanmar and assist every single need of your holidays.");
        return view;
    }

    @Override
    protected void onSendScreenHit() {

    }
}

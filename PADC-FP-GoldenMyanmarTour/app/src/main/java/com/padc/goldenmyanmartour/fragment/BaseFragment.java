package com.padc.goldenmyanmartour.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by WT on 9/22/2016.
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public void onStart() {
        super.onStart();
        onSendScreenHit();
    }

    protected abstract void onSendScreenHit();
}

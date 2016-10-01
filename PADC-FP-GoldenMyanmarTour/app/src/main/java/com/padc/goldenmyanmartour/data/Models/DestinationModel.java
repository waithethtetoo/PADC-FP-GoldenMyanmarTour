package com.padc.goldenmyanmartour.data.Models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.events.DataEvent;
import com.padc.goldenmyanmartour.utils.DestinationConstants;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import de.greenrobot.event.EventBus;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class DestinationModel extends BaseModel {

    private static DestinationModel obj;
    private List<DestinationVO> mDestList;

    public DestinationModel() {
        super();
        mDestList = new ArrayList<>();
    }

    public static DestinationModel getInstance() {
        if (obj == null) {
            obj = new DestinationModel();
        }
        return obj;
    }

    public void loadDestinations() {
        dataAgent.loadDestinations();
    }

    public List<DestinationVO> getmDestList() {
        return mDestList;
    }

    public DestinationVO getDestinationByName(String destName) {
        for (DestinationVO destinationVO : mDestList) {
            if (destinationVO.getTitle().equals(destName)) ;
            return destinationVO;
        }
        return null;
    }

    public void notifyDestinationLoaded(List<DestinationVO> destinationVOList) {
        mDestList = destinationVOList;
        /* save to persistence layer */
        DestinationVO.saveDestinations(mDestList);
//        broadcastDestinationLoadedWithEventBus();
    }

    private void broadcastDestinationLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.DestinationDataLoaded("extra-in-broadcast", mDestList));
    }

    public void notifyErrorInLoadingDestinations(String message) {
    }

    public void setStoredData(List<DestinationVO> destList) {
        mDestList = destList;
    }
}

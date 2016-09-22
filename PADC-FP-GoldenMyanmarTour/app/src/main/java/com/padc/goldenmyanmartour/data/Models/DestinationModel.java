package com.padc.goldenmyanmartour.data.Models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class DestinationModel extends BaseModel {
    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";
    private static DestinationModel model;
    private List<DestinationVO> mDestinationList;

    private DestinationModel() {
        super();
        mDestinationList = new ArrayList<>();
    }

    public static DestinationModel getInstance() {
        if (model == null) {
            model = new DestinationModel();
        }
        return model;
    }

    public void loadDestinations() {
        dataAgent.loadDestinations();
    }

    public List<DestinationVO> getDestinationList() {
        return mDestinationList;
    }

    public DestinationVO getDestinationByName(String destinationName) {
        for (DestinationVO destination : mDestinationList) {
            if (destination.getTitle().equals(destinationName)) {
                return destination;
            }
        }
        return null;
    }

    public void notifyDestinationsLoaded(List<DestinationVO> destinationVOList) {
        mDestinationList = destinationVOList;
        DestinationVO.saveDestinations(mDestinationList);

        broadcastDestinationLoadedWithLocalBroadcastManager();
    }

    public void notifyErrorInLoadingDestinations(String message) {

    }

    public String getRandomDestinationImage() {
        if (mDestinationList == null || mDestinationList.size() == 0) {
            return null;
        }
        Random random = new Random();
        int randomInt = random.nextInt(mDestinationList.size());

        DestinationVO destinationVO = mDestinationList.get(randomInt);
        return DestinationConstants.IMAGE_ROOT_DIR + destinationVO.getDestination_photos()[destinationVO.getDestination_photos().length - 1];
    }

    private void broadcastDestinationLoadedWithLocalBroadcastManager() {
        Intent intent = new Intent(BROADCAST_DATA_LOADED);
        intent.putExtra("key-for-extra", "extra-in-broadcast");
        LocalBroadcastManager.getInstance(GMTApp.getContext()).sendBroadcast(intent);
    }
}

package com.padc.goldenmyanmartour.data.vo.Models;


import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by WT on 9/23/2016.
 */
public class FestivalModel extends BaseModel {
    private static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";
    private static FestivalModel model;
    private List<FestivalVO> mFestivalList;

    public FestivalModel() {
        super();
        mFestivalList = new ArrayList<>();
    }

    public static FestivalModel getInstance() {
        if (model == null) {
            model = new FestivalModel();
        }
        return model;
    }

    public void loadFestivals() {
        dataAgent.loadFestivals();
    }

    public List<FestivalVO> getFestivalList() {
        return mFestivalList;
    }

    public FestivalVO getFestivalByName(String festivalName) {
        for (FestivalVO festival : mFestivalList) {
            if (festival.getFestivalName().equals(festivalName)) {
                return festival;
            }
        }
        return null;
    }

    public void notifyFestivalsLoaded(List<FestivalVO> festivalList) {
        mFestivalList = festivalList;
        FestivalVO.saveFestivals(mFestivalList);
    }

    public void notifyErrorInLoadingFestivals(String message) {
    }

    public String getRandomFestivalImage() {
        if (mFestivalList == null || mFestivalList.size() == 0) {
            return null;
        }
        Random random = new Random();
        int randomInt = random.nextInt(mFestivalList.size());

        FestivalVO festival = mFestivalList.get(randomInt);
        return DestinationConstants.IMAGE_ROOT_DIR + festival.getPhotos()[festival.getPhotos().length - 1];
    }

    private void broadcastFrestivalLoadedWithLocalBroadcastManager() {
        Intent intent = new Intent(BROADCAST_DATA_LOADED);
        intent.putExtra("key-for-extra", "extra-in-broadcast");
        LocalBroadcastManager.getInstance(GMTApp.getContext()).sendBroadcast(intent);
    }

    public void setStoredData(List<FestivalVO> festivalList) {
        mFestivalList = festivalList;
    }
}
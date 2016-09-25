package com.padc.goldenmyanmartour.data.Models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by WT on 9/24/2016.
 */
public class FestivalModel extends BaseModel {
    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";
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

    public FestivalVO getFestivalByName(String name) {
        for (FestivalVO festivalVO : mFestivalList) {
            if (festivalVO.getFestivalName().equals(name)) {
                return festivalVO;
            }
        }
        return null;
    }

    public void notifyFestivalsLoaded(List<FestivalVO> festivalVOList) {
        mFestivalList = festivalVOList;
        FestivalVO.saveFestivals(mFestivalList);

        broadcastFestivalsLoadedWithLocalBroadcastManager();
    }



    public void notifyErrorInLoadingFestivals(String message) {

    }
    public String getRandomFestivalImage() {
        if (mFestivalList == null || mFestivalList.size() == 0) {
            return null;
        }
        Random random = new Random();
        int randomInt = random.nextInt(mFestivalList.size());

        FestivalVO festivalVO = mFestivalList.get(randomInt);
        return DestinationConstants.IMAGE_ROOT_DIR + festivalVO.getPhotos()[festivalVO.getPhotos().length - 1];
    }
    private void broadcastFestivalsLoadedWithLocalBroadcastManager() {
        Intent intent = new Intent(BROADCAST_DATA_LOADED);
        intent.putExtra("key-for-extra", "extra-in-broadcast");
        LocalBroadcastManager.getInstance(GMTApp.getContext()).sendBroadcast(intent);
    }
}

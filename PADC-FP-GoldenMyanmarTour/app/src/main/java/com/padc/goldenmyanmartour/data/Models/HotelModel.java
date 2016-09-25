package com.padc.goldenmyanmartour.data.Models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.HotelVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by WT on 9/24/2016.
 */
public class HotelModel extends BaseModel {

    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";
    private static HotelModel model;
    private List<HotelVO> mHotelList;

    public HotelModel() {
        super();
        mHotelList = new ArrayList<>();
    }

    public static HotelModel getInstance() {
        if (model == null) {
            model = new HotelModel();
        }
        return model;
    }

    public void loadHotels() {
        dataAgent.loadHotels();
    }

    public List<HotelVO> getHotelList() {
        return mHotelList;
    }

    public HotelVO getHotelByName(String name) {
        for (HotelVO hotelVO : mHotelList) {
            if (hotelVO.getHotelName().equals(name)) {
                return hotelVO;
            }
        }
        return null;
    }

    public void notifyHotelsLoaded(List<HotelVO> hotelVOList) {
        mHotelList = hotelVOList;
        //HotelVO.saveHotels(mHotelList);
        broadcastHotelsLoadedWithLocalBroadcastManager();
    }


    public void notifyErrorInLoadingHotels(String message) {

    }

    public String getRandomHotelImage() {
        if (mHotelList == null || mHotelList.size() == 0) {
            return null;
        }
        Random random = new Random();
        int randomInt = random.nextInt(mHotelList.size());

        HotelVO hotelVO = mHotelList.get(randomInt);
        return DestinationConstants.IMAGE_ROOT_DIR + hotelVO.getPhotos()[hotelVO.getPhotos().length - 1];
    }

    private void broadcastHotelsLoadedWithLocalBroadcastManager() {
        Intent intent = new Intent(BROADCAST_DATA_LOADED);
        intent.putExtra("key-for-extra", "extra-in-broadcast");
        LocalBroadcastManager.getInstance(GMTApp.getContext()).sendBroadcast(intent);
    }
}

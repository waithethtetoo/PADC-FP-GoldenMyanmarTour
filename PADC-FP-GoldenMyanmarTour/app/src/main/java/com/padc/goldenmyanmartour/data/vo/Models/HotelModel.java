package com.padc.goldenmyanmartour.data.vo.Models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.HotelVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by WT on 9/23/2016.
 */
public class HotelModel extends BaseModel {

    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";
    private static HotelModel model;
    private List<HotelVO> hotelList;

    public HotelModel() {
        super();
        hotelList = new ArrayList<>();
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
        return hotelList;
    }

    public HotelVO getHotelsByName(String name) {
        for (HotelVO hotelVO : hotelList) {
            if (hotelVO.getHotelName().equals(name)) {
                return hotelVO;
            }
        }
        return null;
    }

    public void notifyHotelsLoaded(List<HotelVO> hotelVOList) {
        hotelList = hotelVOList;
        HotelVO.saveHotels(hotelList);
    }

    public void notifyErrorInLoadingHotels(String message) {
    }

    public String getRandomHotelImage() {
        if (hotelList == null || hotelList.size() == 0) {
            return null;
        }
        Random random = new Random();
        int randomInt = random.nextInt(hotelList.size());

        HotelVO hotel = hotelList.get(randomInt);
        return DestinationConstants.IMAGE_ROOT_DIR + hotel.getPhotos()[hotel.getPhotos().length - 1];
    }

    private void broadcastHotelsLoadedWithLocalBroadcastManager() {
        Intent intent = new Intent(BROADCAST_DATA_LOADED);
        intent.putExtra("key-for-extra", "extra-in-broadcast");
        LocalBroadcastManager.getInstance(GMTApp.getContext()).sendBroadcast(intent);
    }

    public void setStoredData(List<HotelVO> hotelVOList) {
        hotelList = hotelVOList;
    }
}

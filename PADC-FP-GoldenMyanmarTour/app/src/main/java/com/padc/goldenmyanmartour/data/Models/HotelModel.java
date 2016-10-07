package com.padc.goldenmyanmartour.data.Models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.reflect.TypeToken;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.HotelVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;
import com.padc.goldenmyanmartour.utils.CommonInstances;
import com.padc.goldenmyanmartour.utils.DestinationConstants;
import com.padc.goldenmyanmartour.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by WT on 9/24/2016.
 */
public class HotelModel extends BaseModel {
    private static final String DUMMY_HOTEL_LIST = "hotels.json";

    private static HotelModel model;
    private List<HotelVO> mHotelList;

    public HotelModel() {
        super();
        mHotelList = initializeHotelsList();
    }

    public static HotelModel getInstance() {
        if (model == null) {
            model = new HotelModel();
        }
        return model;
    }

    private List<HotelVO> initializeHotelsList() {

        List<HotelVO> hotelsList = new ArrayList<>();

        try {
            String dummyEventList = JsonUtils.getInstance().loadDummyData(DUMMY_HOTEL_LIST);
            Type listType = new TypeToken<List<HotelVO>>() {
            }.getType();
            hotelsList = CommonInstances.getInstance().fromJson(dummyEventList, listType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hotelsList;
    }

    public List<HotelVO> getHotelList() {
        return mHotelList;
    }


    public HotelVO getHotelsByName(String name) {
        for (HotelVO hotelVO : mHotelList) {
            if (hotelVO.getHotelName().equals(name)) {
                return hotelVO;
            }
        }
        return null;
    }
}

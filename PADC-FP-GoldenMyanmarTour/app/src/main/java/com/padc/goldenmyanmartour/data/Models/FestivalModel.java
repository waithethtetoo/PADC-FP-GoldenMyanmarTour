package com.padc.goldenmyanmartour.data.Models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.reflect.TypeToken;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;
import com.padc.goldenmyanmartour.events.DataEvent;
import com.padc.goldenmyanmartour.utils.CommonInstances;
import com.padc.goldenmyanmartour.utils.DestinationConstants;
import com.padc.goldenmyanmartour.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.greenrobot.event.EventBus;

/**
 * Created by WT on 9/24/2016.
 */
public class FestivalModel extends BaseModel {

    private static final String DUMMY_FESTIVALS_LIST = "festivals.json";

    private static FestivalModel model;
    private List<FestivalVO> mFestivalList;

    public FestivalModel() {
        super();
        mFestivalList = initializeFestivalList();
    }

    public static FestivalModel getInstance() {
        if (model == null) {
            model = new FestivalModel();
        }
        return model;
    }

    private List<FestivalVO> initializeFestivalList() {
        List<FestivalVO> festivalList = new ArrayList<>();

        try {
            String dummyEventList = JsonUtils.getInstance().loadDummyData(DUMMY_FESTIVALS_LIST);
            Type listType = new TypeToken<List<FestivalVO>>() {
            }.getType();
            festivalList = CommonInstances.getInstance().fromJson(dummyEventList, listType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return festivalList;
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
}

package com.padc.goldenmyanmartour.data.Models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.reflect.TypeToken;
import com.padc.goldenmyanmartour.GMTApp;
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
public class PackageModel extends BaseModel {

    private static final String DUMMY_PACKAGE_LIST = "package.json";

    private static PackageModel model;
    private List<PackageVO> mPackageList;

    public PackageModel() {
        mPackageList = initializePackageList();
    }

    public static PackageModel getInstance() {
        if (model == null) {
            model = new PackageModel();
        }
        return model;
    }

    private List<PackageVO> initializePackageList() {
        List<PackageVO> packageList = new ArrayList<>();

        try {
            String dummyEventList = JsonUtils.getInstance().loadDummyData(DUMMY_PACKAGE_LIST);
            Type listType = new TypeToken<List<PackageVO>>() {
            }.getType();
            packageList = CommonInstances.getInstance().fromJson(dummyEventList, listType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return packageList;
    }
//    public void loadPackages() {
//        dataAgent.loadPackages();
//    }

    public List<PackageVO> getPackageList() {
        return mPackageList;
    }

    public PackageVO getPackageByName(String name) {
        for (PackageVO packageVO : mPackageList) {
            if (packageVO.getPackageName().equals(name)) {
                return packageVO;
            }
        }
        return null;
    }

    /*
    public void notifyPackagesLoaded(List<PackageVO> packageVOList) {
        mPackageList = packageVOList;
        PackageVO.savePackages(mPackageList);
        broadcastDestinationLoadedWithEventBus();
    }

    public void notifyErrorInLoadingPackages(String message) {

    }

    public String getRandomPackageImage() {
        if (mPackageList == null || mPackageList.size() == 0) {
            return null;
        }
        Random random = new Random();
        int randomInt = random.nextInt(mPackageList.size());

        PackageVO packageVO = mPackageList.get(randomInt);
        return DestinationConstants.IMAGE_ROOT_DIR + packageVO.getPhotos()[packageVO.getPhotos().length - 1];
    }

    private void broadcastDestinationLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.PackageDataLoaded("extra-in-broadcast", mPackageList));
    }


    public void setStoredData(List<PackageVO> storedData) {
        mPackageList = storedData;
    }
    */

}

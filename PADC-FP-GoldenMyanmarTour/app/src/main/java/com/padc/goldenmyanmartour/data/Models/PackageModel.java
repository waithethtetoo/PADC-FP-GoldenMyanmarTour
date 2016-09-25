package com.padc.goldenmyanmartour.data.Models;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.PackageVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by WT on 9/24/2016.
 */
public class PackageModel extends BaseModel {

    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";
    private static PackageModel model;
    private List<PackageVO> mPackageList;

    public PackageModel() {
        super();
        mPackageList = new ArrayList<>();
    }

    public static PackageModel getInstance() {
        if (model == null) {
            model = new PackageModel();
        }
        return model;
    }

    public void loadPackages() {
        dataAgent.loadPackages();
    }

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

    public void notifyPackagesLoaded(List<PackageVO> packageVOList) {
        mPackageList = packageVOList;
        PackageVO.savePackages(mPackageList);

        broadcastPackagesLoadedWithLocalBroadcastManager();
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

    private void broadcastPackagesLoadedWithLocalBroadcastManager() {
        Intent intent = new Intent(BROADCAST_DATA_LOADED);
        intent.putExtra("key-for-extra", "extra-in-broadcast");
        LocalBroadcastManager.getInstance(GMTApp.getContext()).sendBroadcast(intent);
    }
}

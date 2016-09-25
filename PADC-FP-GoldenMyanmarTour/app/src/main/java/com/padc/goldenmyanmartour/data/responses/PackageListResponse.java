package com.padc.goldenmyanmartour.data.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;

import java.util.ArrayList;

/**
 * Created by WT on 9/24/2016.
 */
public class PackageListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("tour-packages")
    private ArrayList<PackageVO> packagesList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<PackageVO> getPackagesList() {
        return packagesList;
    }
}

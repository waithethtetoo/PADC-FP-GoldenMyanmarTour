package com.padc.goldenmyanmartour.data.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;

import java.util.ArrayList;

/**
 * Created by WT on 9/24/2016.
 */
public class FestivalListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("festivals")
    private ArrayList<FestivalVO> festivalList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<FestivalVO> getFestivalList() {
        return festivalList;
    }
}

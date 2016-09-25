package com.padc.goldenmyanmartour.data.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.data.vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by WT on 9/24/2016.
 */
public class HotelListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("hotels")
    private ArrayList<HotelVO> hotelsList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<HotelVO> getHotelsList() {
        return hotelsList;
    }
}

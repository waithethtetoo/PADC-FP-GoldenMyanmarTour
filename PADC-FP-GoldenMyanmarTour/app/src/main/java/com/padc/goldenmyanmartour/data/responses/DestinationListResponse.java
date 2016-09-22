package com.padc.goldenmyanmartour.data.responses;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;

import java.util.ArrayList;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class DestinationListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("destinations")
    private ArrayList<DestinationVO> destinationList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<DestinationVO> getDestinationList() {
        return destinationList;
    }
}

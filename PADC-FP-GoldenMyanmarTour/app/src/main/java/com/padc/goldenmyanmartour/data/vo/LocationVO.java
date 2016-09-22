package com.padc.goldenmyanmartour.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class LocationVO {
    @SerializedName("lat")
    private float lat;
    @SerializedName("lng")
    private float lng;

    @SerializedName("city")
    private CityVO cityVO;

    @SerializedName("state")
    private StateVO stateVO;

    public StateVO getStateVO() {
        return stateVO;
    }

    public void setStateVO(StateVO stateVO) {
        this.stateVO = stateVO;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public CityVO getCityVO() {
        return cityVO;
    }

    public void setCityVO(CityVO cityVO) {
        this.cityVO = cityVO;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}

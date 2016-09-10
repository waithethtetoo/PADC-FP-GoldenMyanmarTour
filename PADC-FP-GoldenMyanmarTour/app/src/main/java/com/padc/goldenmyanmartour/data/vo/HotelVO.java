package com.padc.goldenmyanmartour.data.vo;

/**
 * Created by hp user on 9/9/2016.
 */
public class HotelVO {
    private String[] hotelImage;
    private String hotelName;
    private String hotelAddress;
    private String hotelDesc;

    public HotelVO(String[] hotelImage, String hotelName, String hotelAddress, String hotelDesc) {
        this.hotelImage = hotelImage;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelDesc = hotelDesc;
    }

    public String[] getHotelImage() {
        return hotelImage;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public String getHotelDesc() {
        return hotelDesc;
    }
}

package com.padc.goldenmyanmartour.data.agent;


import com.padc.goldenmyanmartour.data.responses.DestinationListResponse;
import com.padc.goldenmyanmartour.data.responses.FestivalListResponse;
import com.padc.goldenmyanmartour.data.responses.HotelListResponse;
import com.padc.goldenmyanmartour.data.responses.PackageListResponse;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Lenovo on 9/21/2016.
 */
public interface DestinationApi {

    // destination api
    @FormUrlEncoded
    @POST(DestinationConstants.API_GET_DESTINATION_LIST)
    Call<DestinationListResponse> loadDestinations(
            @Field(DestinationConstants.PARAM_ACCESS_TOKEN) String param);

 /*
    // package api
    @FormUrlEncoded
    @POST(DestinationConstants.API_GET_PACKAGE_LIST)
    Call<PackageListResponse> loadPackages(
            @Field(DestinationConstants.PARAM_ACCESS_TOKEN) String param);


    // hotel api
    @FormUrlEncoded
    @POST(DestinationConstants.API_GET_HOTEL_LIST)
    Call<HotelListResponse> loadHotels(
            @Field(DestinationConstants.PARAM_ACCESS_TOKEN) String param);

    // festival api
    @FormUrlEncoded
    @POST(DestinationConstants.API_GET_FESTIVAL_LIST)
    Call<FestivalListResponse> loadFestivals(
            @Field(DestinationConstants.PARAM_ACCESS_TOKEN) String param);
*/
}

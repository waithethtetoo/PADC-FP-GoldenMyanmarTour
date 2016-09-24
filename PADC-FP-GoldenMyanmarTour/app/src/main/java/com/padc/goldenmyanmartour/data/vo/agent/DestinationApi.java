package com.padc.goldenmyanmartour.data.vo.agent;

import android.telecom.Call;

import com.padc.goldenmyanmartour.data.vo.responses.DestinationListResponse;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class DestinationApi {
    @FormUrlEncoded
    @POST(DestinationConstants.API_GET_DESTINATION_LIST)
    Call<DestinationListResponse> loadDestinations(
            @Field(DestinationConstants.PARAM_ACCESS_TOKEN) String param);

}

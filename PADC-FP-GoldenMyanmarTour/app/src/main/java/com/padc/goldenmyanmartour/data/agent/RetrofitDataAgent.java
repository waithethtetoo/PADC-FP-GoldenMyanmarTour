package com.padc.goldenmyanmartour.data.agent;

import android.util.Log;

import com.padc.goldenmyanmartour.data.Models.DestinationModel;
import com.padc.goldenmyanmartour.data.Models.FestivalModel;
import com.padc.goldenmyanmartour.data.Models.HotelModel;
import com.padc.goldenmyanmartour.data.Models.PackageModel;
import com.padc.goldenmyanmartour.data.responses.DestinationListResponse;
import com.padc.goldenmyanmartour.data.responses.FestivalListResponse;
import com.padc.goldenmyanmartour.data.responses.HotelListResponse;
import com.padc.goldenmyanmartour.data.responses.PackageListResponse;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.events.DataEvent;
import com.padc.goldenmyanmartour.utils.CommonInstances;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class RetrofitDataAgent implements DestinationDataAgent {

    private static RetrofitDataAgent retrofitDataAgent;
    private DestinationApi theApi;

    public RetrofitDataAgent() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DestinationConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(DestinationApi.class);

    }


    public static RetrofitDataAgent getInstance() {
        if (retrofitDataAgent == null) {
            retrofitDataAgent = new RetrofitDataAgent();
        }
        return retrofitDataAgent;
    }

    @Override
    public void loadDestinations() {
        Call<DestinationListResponse> loadDestCall = theApi.loadDestinations(DestinationConstants.ACCESS_TOKEN);
        loadDestCall.enqueue(new Callback<DestinationListResponse>() {
            @Override
            public void onResponse(Call<DestinationListResponse> call, Response<DestinationListResponse> response) {
                if (response.isSuccessful()) {
                    DestinationListResponse destinationListResponse = response.body();
                    if (destinationListResponse == null) {
                        DestinationModel.getInstance().notifyErrorInLoadingDestinations(response.message());
                    } else {
                        DestinationModel.getInstance().notifyDestinationLoaded(destinationListResponse.getDestinationList());
                    }
                } else {
                    Log.d("Data", "Unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<DestinationListResponse> call, Throwable t) {
                t.printStackTrace();
                DestinationModel.getInstance().notifyErrorInLoadingDestinations(t.getMessage());
            }
        });

    }
}
package com.padc.goldenmyanmartour.data.vo.agent;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.Models.DestinationModel;
import com.padc.goldenmyanmartour.data.vo.responses.DestinationListResponse;
import com.padc.goldenmyanmartour.utils.CommonInstances;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class RetrofitDataAgent implements DestinationDataAgent {
    private static RetrofitDataAgent retrofitDataAgent;
    private final DestinationApi theApi;

    public RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GMTApp.DESTINATION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getInstance())) // convert JSON with response
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
        Call<DestinationListResponse> loadDestinationCall = theApi.loadDestinations(DestinationConstants.ACCESS_TOKEN);
        loadDestinationCall.enqueue(new Callback<DestinationListResponse>() {

            @Override
            public void onResponse(Call<DestinationListResponse> call, retrofit2.Response<DestinationListResponse> response) {
                DestinationListResponse destinationListResponse = response.body();
                if (destinationListResponse == null) {
                    DestinationModel.getInstance().notifyErrorInLoadingDestinations(response.message());
                } else {
                    DestinationModel.getInstance().notifyDestinationsLoaded(destinationListResponse.getDestinationList());
                }
            }

            public void onFailure(Call<DestinationListResponse> call, Throwable throwable) {
                DestinationModel.getInstance().notifyErrorInLoadingDestinations(throwable.getMessage());
            }
        });
    }

    @Override
    public void loadFestivals() {

    }

    @Override
    public void loadHotels() {

    }

    @Override
    public void loadPackage() {

    }
}

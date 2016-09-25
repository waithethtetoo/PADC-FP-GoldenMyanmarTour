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
import com.padc.goldenmyanmartour.events.DataEvent;
import com.padc.goldenmyanmartour.utils.CommonInstances;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    private final DestinationApi theApi;

    public RetrofitDataAgent() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DestinationConstants.DESTINATION_BASE_URL)
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

    // destinations
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


    // festivals
    @Override
    public void loadFestivals() {
        Call<FestivalListResponse> loadFestivalCall = theApi.loadFestivals(DestinationConstants.ACCESS_TOKEN);
        loadFestivalCall.enqueue(new Callback<FestivalListResponse>() {
            @Override
            public void onResponse(Call<FestivalListResponse> call, retrofit2.Response<FestivalListResponse> response) {
                FestivalListResponse festivalListResponse = response.body();
                if (festivalListResponse == null) {
                    FestivalModel.getInstance().notifyErrorInLoadingFestivals(response.message());
                } else {
                    FestivalModel.getInstance().notifyFestivalsLoaded(festivalListResponse.getFestivalList());
                }
            }

            @Override
            public void onFailure(Call<FestivalListResponse> call, Throwable t) {
                FestivalModel.getInstance().notifyErrorInLoadingFestivals(t.getMessage());
            }
        });
    }

    // hotels
    @Override
    public void loadHotels() {
        Call<HotelListResponse> loadHotelCall = theApi.loadHotels(DestinationConstants.ACCESS_TOKEN);
        loadHotelCall.enqueue(new Callback<HotelListResponse>() {
            @Override
            public void onResponse(Call<HotelListResponse> call, retrofit2.Response<HotelListResponse> response) {
                HotelListResponse hotelListResponse = response.body();
                if (hotelListResponse == null) {
                    HotelModel.getInstance().notifyErrorInLoadingHotels(response.message());
                } else {
                    HotelModel.getInstance().notifyHotelsLoaded(hotelListResponse.getHotelsList());
                }
            }

            @Override
            public void onFailure(Call<HotelListResponse> call, Throwable t) {
                HotelModel.getInstance().notifyErrorInLoadingHotels(t.getMessage());
            }
        });
    }

    // packages
    @Override
    public void loadPackages() {
        Call<PackageListResponse> loadPackageCall = theApi.loadPackages(DestinationConstants.ACCESS_TOKEN);
        loadPackageCall.enqueue(new Callback<PackageListResponse>() {
            @Override
            public void onResponse(Call<PackageListResponse> call, retrofit2.Response<PackageListResponse> response) {
                PackageListResponse packageListResponse = response.body();
                if (packageListResponse == null) {
                    PackageModel.getInstance().notifyErrorInLoadingPackages(response.message());
                } else {
                    PackageModel.getInstance().notifyPackagesLoaded(packageListResponse.getPackagesList());
                }
            }

            @Override
            public void onFailure(Call<PackageListResponse> call, Throwable t) {
                PackageModel.getInstance().notifyErrorInLoadingPackages(t.getMessage());
            }
        });
    }

}
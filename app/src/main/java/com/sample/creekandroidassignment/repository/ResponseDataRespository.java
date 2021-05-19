package com.sample.creekandroidassignment.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sample.creekandroidassignment.api.ApiClient;
import com.sample.creekandroidassignment.api.ApiService;
import com.sample.creekandroidassignment.model.ResponseData;
import com.sample.creekandroidassignment.model.SearchModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseDataRespository {

    ApiService apiService;
    String total_count;
    String total_count_search;

    public ResponseDataRespository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public LiveData<ResponseData> getResponseItems(int count, int offset) {
        MutableLiveData<ResponseData> mutableLiveData = new MutableLiveData<>();
        apiService.getdata(count,offset).enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(@NotNull Call<ResponseData> call, @NotNull Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    total_count = response.body().getTotal_count();
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseData> call, @NotNull Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    public LiveData<SearchModel> getResponseSearchItems(String query, int count, int offset) {
        MutableLiveData<SearchModel> mutableLiveDataSearch = new MutableLiveData<>();
        apiService.getSearchdata(query, count, offset).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(@NotNull Call<SearchModel> call, @NotNull Response<SearchModel> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    total_count_search = response.body().getTotal_count();
                    mutableLiveDataSearch.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<SearchModel> call, @NotNull Throwable t) {
                mutableLiveDataSearch.setValue(null);
            }
        });
        return mutableLiveDataSearch;
    }

}

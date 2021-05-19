package com.sample.creekandroidassignment.api;


import com.sample.creekandroidassignment.model.ResponseData;
import com.sample.creekandroidassignment.model.SearchModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v4/iconsets/236811/icons")
    Call<ResponseData> getdata(@Query("count") int count, @Query("offset") int offset);

    @GET("v4/icons/search")
    Call<SearchModel> getSearchdata(@Query("query") String query, @Query("count") int count, @Query("offset") int offset);
}
package com.sample.creekandroidassignment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sample.creekandroidassignment.model.ResponseData;
import com.sample.creekandroidassignment.model.SearchModel;
import com.sample.creekandroidassignment.repository.ResponseDataRespository;

import java.util.List;

public class ResponseDataViewModel extends ViewModel {
    ResponseDataRespository responseDataRespository;

    private LiveData<ResponseData> users;
    ResponseData responseData;
    LiveData<SearchModel> searchModel;

    public ResponseDataViewModel() {
        responseDataRespository = new ResponseDataRespository();

    }

    public LiveData<ResponseData> getItems(int count, int offset){
        if(users==null){
            users = responseDataRespository.getResponseItems(count, offset);
        }
        return users;
    }

    public LiveData<SearchModel> getSearchItems(String query, int count, int offset) {
        if (searchModel == null) {
            searchModel = responseDataRespository.getResponseSearchItems(query, count, offset);
        }
        return searchModel;
    }


    public ResponseData getDataResponse() {
        return responseData;
    }

    public void setNewsResponse(ResponseData dataResponse) {
        this.responseData = dataResponse;
    }
}

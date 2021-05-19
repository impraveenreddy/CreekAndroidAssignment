package com.sample.creekandroidassignment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.creekandroidassignment.adapter.SearchDataAdapter;
import com.sample.creekandroidassignment.databinding.SearchActBinding;
import com.sample.creekandroidassignment.model.SearchModel;
import com.sample.creekandroidassignment.viewmodel.ResponseDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActvitiy extends AppCompatActivity {

    SearchActBinding searchActBinding;
    ResponseDataViewModel responseDataViewModel;
    int total_count;
    List<SearchModel.Icons> responseDataIs = new ArrayList<>();
    SearchDataAdapter adapter;
    int offset1 = 10;
    String query;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchActBinding = DataBindingUtil.setContentView(this, R.layout.search_act);

        query = getIntent().getStringExtra("query");

        responseDataViewModel = new ViewModelProvider(this).get(ResponseDataViewModel.class);
        searchActBinding.mainRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new SearchDataAdapter(responseDataIs, this);
        searchActBinding.mainRecycler.setHasFixedSize(true);
        searchActBinding.mainRecycler.setAdapter(adapter);

        searchActBinding.mainRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!searchActBinding.mainRecycler.canScrollVertically(1)) {
                    if (offset1 <= total_count) {
                        offset1 += 10;
                        getItemsDataSearch(query);
                    }
                }
            }
        });

        getItemsDataSearch(query);
    }

    private void getItemsDataSearch(String query) {
        toggleLoading();
        responseDataViewModel.getSearchItems(query, 10, offset1).observe(this, responseData -> {
            toggleLoading();
            if (responseData != null) {
                total_count = Integer.parseInt(responseData.getTotal_count());
                int oldCount = responseData.getIcons().size();
                responseDataIs.addAll(responseData.getIcons());
                offset1 = responseDataIs.size();
                adapter.notifyItemRangeInserted(oldCount, responseData.getIcons().size());
            }
        });
    }

    private void toggleLoading() {
        if (offset1 == 10) {
            searchActBinding.setIsLoading(searchActBinding.getIsLoading() == null || !searchActBinding.getIsLoading());
        } else {
            searchActBinding.setIsLoadingMore(searchActBinding.getIsLoadingMore() == null || !searchActBinding.getIsLoadingMore());
        }
    }
}

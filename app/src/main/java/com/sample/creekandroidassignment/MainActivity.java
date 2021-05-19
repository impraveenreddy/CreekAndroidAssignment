package com.sample.creekandroidassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ferfalk.simplesearchview.SimpleSearchView;
import com.sample.creekandroidassignment.adapter.ResponseDataAdapter;
import com.sample.creekandroidassignment.databinding.ActivityMainBinding;
import com.sample.creekandroidassignment.model.ResponseData;
import com.sample.creekandroidassignment.viewmodel.ResponseDataViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    ResponseDataViewModel responseDataViewModel;
    int total_count;
    List<ResponseData.Icons> responseDataIs = new ArrayList<>();
    ResponseDataAdapter adapter;
    ActivityMainBinding activityMainBinding;
    //    int offset = 10;
    int offset1 = 10;
    //    int total_count1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(activityMainBinding.toolbar);

        responseDataViewModel = new ViewModelProvider(this).get(ResponseDataViewModel.class);
        activityMainBinding.mainRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ResponseDataAdapter(responseDataIs, this);
        activityMainBinding.mainRecycler.setHasFixedSize(true);
        activityMainBinding.mainRecycler.setAdapter(adapter);

        activityMainBinding.mainRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.mainRecycler.canScrollVertically(1)) {
                    if (offset1 <= total_count) {
                        offset1 += 10;
                        getItemsData();
                    }
                }
            }
        });

        getItemsData();
    }

    private void getItemsData() {
        toggleLoading();
        responseDataViewModel.getItems(10, offset1).observe(this, responseData -> {
            toggleLoading();
            if (responseData != null) {
                Log.d("showres", responseData.getTotal_count());
                total_count = Integer.parseInt(responseData.getTotal_count());
                int oldCount = responseData.getIcons().size();
//                total_count1 = total_count / 10;
//                Log.d("totalcountis", "" + total_count1);
//                responseDataIs = responseData.getIcons();
                responseDataIs.addAll(responseData.getIcons());
//                offset = responseData.getIcons().size() + 10;
                offset1 = responseDataIs.size();
                Log.d("totalcountis1", "" + offset1);
                adapter.notifyItemRangeInserted(oldCount, responseData.getIcons().size());
//                adapter.notifyDataSetChanged();
//                Log.d("totalcountis0", "" + offset);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MANAGE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                }
            }
        });


    }

    private void toggleLoading() {
        if (offset1 == 10) {
            activityMainBinding.setIsLoading(activityMainBinding.getIsLoading() == null || !activityMainBinding.getIsLoading());
        } else {
            activityMainBinding.setIsLoadingMore(activityMainBinding.getIsLoadingMore() == null || !activityMainBinding.getIsLoadingMore());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        setupSearchView(menu);
        activityMainBinding.searchView.setMenuItem(item);
        return true;
    }

    private void setupSearchView(Menu menu) {
        activityMainBinding.searchView.setOnQueryTextListener(new SimpleSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(@NotNull String query) {
                Log.d("SimpleSearchView", "Submit:" + query);
                Intent i = new Intent(MainActivity.this, SearchActvitiy.class);
                i.putExtra("query", query.trim());
                startActivity(i);
                return false;
            }

            @Override
            public boolean onQueryTextChange(@NotNull String newText) {
                Log.d("SimpleSearchView", "Text changed:" + newText);
                return false;
            }

            @Override
            public boolean onQueryTextCleared() {
                Log.d("SimpleSearchView", "Text cleared");
                return false;
            }
        });
    }
}
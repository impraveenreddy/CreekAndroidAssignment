package com.sample.creekandroidassignment.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sample.creekandroidassignment.R;
import com.sample.creekandroidassignment.SearchActvitiy;
import com.sample.creekandroidassignment.databinding.MainListBinding;
import com.sample.creekandroidassignment.databinding.SearchListBinding;
import com.sample.creekandroidassignment.model.ResponseData;
import com.sample.creekandroidassignment.model.SearchModel;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class SearchDataAdapter extends RecyclerView.Adapter<SearchDataAdapter.ResponseViewHolder> {

    List<SearchModel.Icons> searchData;
    Activity activity;
    LayoutInflater layoutInflater;
    SearchListBinding searchListBinding;

    public SearchDataAdapter(List<SearchModel.Icons> responseDataIs, SearchActvitiy searchActvitiy) {
        searchData = responseDataIs;
        activity = searchActvitiy;
    }

    @NotNull
    @Override
    public ResponseViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        searchListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.search_list, parent, false);
        return new ResponseViewHolder(searchListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ResponseViewHolder holder, int position) {
        holder.bindData(searchData.get(position));
    }

    @Override
    public int getItemCount() {
        return searchData.size();
    }

    class ResponseViewHolder extends RecyclerView.ViewHolder {

        public ResponseViewHolder(@NonNull @NotNull SearchListBinding itemView) {
            super(itemView.getRoot());
            searchListBinding = itemView;
        }

        @SuppressLint("SetTextI18n")
        public void bindData(SearchModel.Icons responseData) {
            searchListBinding.executePendingBindings();
            searchListBinding.setResponseDataX(responseData);

            if (responseData.getIs_premium().equals("true")) {
                searchListBinding.imageTag.setVisibility(View.VISIBLE);
                searchListBinding.costText.setVisibility(View.VISIBLE);
                searchListBinding.downloadIcon.setVisibility(View.GONE);
                searchListBinding.costText.setText("$ " + responseData.getPrices().get(0).getPrice().replace(".0", ""));
            }

            Glide.with(activity)
                    .load(responseData.getRaster_sizes().get(7).getFormats().get(0).getPreview_url())
                    .into(searchListBinding.imageIcon);

            searchListBinding.textTitle.setText(responseData.getTags()[0]);
            Log.d("tagnameis", responseData.getTags()[0]);

            searchListBinding.imageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadImageNew(responseData.getTags()[0], responseData.getRaster_sizes().get(7).getFormats().get(0).getDownload_url());
                }
            });

        }
        private void downloadImageNew(String name, String downloadUrlOfImage) {
            try {
                DownloadManager dm = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
                Uri downloadUri = Uri.parse(downloadUrlOfImage);
                DownloadManager.Request request = new DownloadManager.Request(downloadUri);
                request.addRequestHeader("Accept", "application/json");
                request.addRequestHeader("Authorization", "Bearer xpsyZqi3QTx3PJ8DHaf32nMZcRds1nwivFYu4RniCO7TmHqMpW68pSVCvITzx1b6");
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle(name)
                        .setMimeType("image/jpg")
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + name + "png");
                dm.enqueue(request);
                Toast.makeText(activity, "Image downloaded.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(activity, "Image download failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

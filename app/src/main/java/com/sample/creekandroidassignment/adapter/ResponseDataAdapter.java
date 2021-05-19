package com.sample.creekandroidassignment.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sample.creekandroidassignment.Headers;
import com.sample.creekandroidassignment.MainActivity;
import com.sample.creekandroidassignment.R;
import com.sample.creekandroidassignment.SearchActvitiy;
import com.sample.creekandroidassignment.databinding.MainListBinding;
import com.sample.creekandroidassignment.model.ResponseData;
import com.sample.creekandroidassignment.model.SearchModel;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ResponseDataAdapter extends RecyclerView.Adapter<ResponseDataAdapter.ResponseViewHolder> {

    List<ResponseData.Icons> responseData;
    Activity activity;
    LayoutInflater layoutInflater;
    MainListBinding mainListBinding;
    String main;
    String fileUri;

    public ResponseDataAdapter(List<ResponseData.Icons> responseData1, MainActivity mainActivity) {
        responseData = responseData1;
        activity = mainActivity;
        this.main = main;
    }


    @NotNull
    @Override
    public ResponseViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        mainListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.main_list, parent, false);
        return new ResponseViewHolder(mainListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ResponseViewHolder holder, int position) {
        holder.bindData(responseData.get(position));
    }

    @Override
    public int getItemCount() {
        return responseData.size();
    }

    class ResponseViewHolder extends RecyclerView.ViewHolder {

        public ResponseViewHolder(@NonNull @NotNull MainListBinding itemView) {
            super(itemView.getRoot());
            mainListBinding = itemView;
        }

        @SuppressLint("SetTextI18n")
        public void bindData(ResponseData.Icons responseData) {
            mainListBinding.executePendingBindings();
            mainListBinding.setResponseDataX(responseData);

            if (responseData.getIs_premium().equals("true")) {
                mainListBinding.imageTag.setVisibility(View.VISIBLE);
                mainListBinding.costText.setVisibility(View.VISIBLE);
                mainListBinding.downloadIcon.setVisibility(View.GONE);
//                mainListBinding.costText.setText(responseData.getPrices().get(0).getCurrency() + " " + responseData.getPrices().get(0).getPrice());
//                Log.d("priceis", responseData.getPrices().get(0).getPrice());
                mainListBinding.costText.setText("$ " + responseData.getPrices().get(0).getPrice().replace(".0", ""));
            }

            Glide.with(activity)
                    .load(responseData.getRaster_sizes().get(7).getFormats().get(0).getPreview_url())
                    .into(mainListBinding.imageIcon);

            mainListBinding.textTitle.setText(responseData.getTags()[0]);
            Log.d("tagnameis", responseData.getTags()[0]);

            mainListBinding.imageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    SaveImage(responseData.getRaster_sizes().get(7).getFormats().get(0).getDownload_url());
                    downloadImageNew(responseData.getTags()[0], responseData.getRaster_sizes().get(7).getFormats().get(0).getDownload_url());
//                    downloadImageNew(responseData.getTags()[0], responseData.getRaster_sizes().get(7).getFormats().get(0).getDownload_url());
                }
            });
        }

        public void SaveImage(String url) {

            Glide.with(activity).asBitmap().load(Headers.getUrlWithHeaders(url))
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NotNull Bitmap bitmap, Transition<? super Bitmap> transition) {
                            try {
                                File mydir = new File(Environment.getExternalStorageDirectory() + "/11zon");
                                if (!mydir.exists()) {
                                    mydir.mkdirs();
                                }

                                fileUri = mydir.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
                                FileOutputStream outputStream = new FileOutputStream(fileUri);

                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                                outputStream.flush();
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(activity, "Image Saved", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onLoadCleared(Drawable placeholder) {
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

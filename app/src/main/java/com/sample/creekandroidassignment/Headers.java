package com.sample.creekandroidassignment;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

public class Headers {

    private static final String AUTHORIZATION = "Bearer xpsyZqi3QTx3PJ8DHaf32nMZcRds1nwivFYu4RniCO7TmHqMpW68pSVCvITzx1b6";

    public static GlideUrl getUrlWithHeaders(String url){
        return new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Authorization", AUTHORIZATION)
                .build());
    }
}

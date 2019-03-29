package com.example.videotray.services;

import com.example.videotray.model.Rss;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/feed_firetv.xml")
    Call<Rss> getRss();
}

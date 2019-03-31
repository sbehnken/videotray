package com.example.videotray.services;


import com.example.videotray.model.Rss;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ApiService {

     private ApiInterface apiInterface;

     public ApiService() {

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://sample-firetv-web-app.s3-website-us-west-2.amazonaws.com")
                 .client(new OkHttpClient())
                 .addConverterFactory(SimpleXmlConverterFactory.create()) //SimpleXML is deprecated by retrofit
                 .build();

         apiInterface = retrofit.create(ApiInterface.class);
     }

    public Call<Rss> getResponse() {
        return apiInterface.getRss();
    }
}



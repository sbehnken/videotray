package com.example.videotray.services;


import com.example.videotray.model.Rss;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
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

         SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy()));
     }

    public Call<Rss> getResponse() {
        return apiInterface.getRss();
    }
}



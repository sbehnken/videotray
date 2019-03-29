package com.example.videotray;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.videotray.model.Rss;
import com.example.videotray.services.ApiInterface;
import com.example.videotray.services.ApiService;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.core.ValueRequiredException;

import java.io.IOException;
import java.io.StringReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt);

//        Serializer serializer = new Persister();

        ApiService apiService = new ApiService();
        apiService.getResponse().enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                Log.e("Response success", response.message());
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.e("Response fail", t.getMessage());
            }
        });
    }

    public class LineNumberTest {

        @Root
        public class Type {
            @Attribute
            String name;
        }
    }

//     try {
////        apiInterface = retrofit.create(ApiInterface.class);
//        Call<Rss> call = apiInterface.getRss();
//        Response<Rss> response = call.execute();
//        //response.code() == 200
//        Rss rss = response.body();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
}

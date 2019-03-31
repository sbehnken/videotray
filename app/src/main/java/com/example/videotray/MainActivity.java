package com.example.videotray;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.videotray.model.Rss;
import com.example.videotray.services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    VideoListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        mAdapter = new VideoListAdapter();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

//        Picasso.with(context) .load(url) .placeholder(R.drawable.user_placeholder) .error(R.drawable.user_placeholder_error) .into(imageView);
//        Picasso.with(view.getContext()).load(mRestaurant.getImageUrl()).into(mImageLabel);

        ApiService apiService = new ApiService();
        apiService.getResponse().enqueue(new Callback<Rss>() {

            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                if(response.body() != null) {
                    mAdapter.setChannelItems(response.body().getChannel().getItemList());
                }
                Log.e("Response success", response.message());
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.e("Response fail", t.getMessage());
            }
        });
    }
}

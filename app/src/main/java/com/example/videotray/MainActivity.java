package com.example.videotray;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

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
        mAdapter.setContext(this);

        mAdapter.setVideoClickListener(new VideoListAdapter.VideoClickListener() {
            @Override
            public void onVideoClicked(Rss.Channel.Item item) {
                //handle playing video
                Toast.makeText(getApplicationContext(), "video clicked", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

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

package com.example.videotray;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.videotray.model.Rss;
import com.example.videotray.services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {
    private VideoListAdapter mAdapter;
    private FrameLayout  frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton mCloseButton = findViewById(R.id.close_button);

        final VideoView videoView = findViewById(R.id.video_view);

        final android.widget.MediaController mediaController = new MediaController(this);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);

        frameLayout = findViewById(R.id.video_view_layout);

        mAdapter = new VideoListAdapter();
        mAdapter.setContext(this);

        mAdapter.setVideoClickListener(new VideoListAdapter.VideoClickListener() {


            @Override
            public void onVideoClicked(Rss.Channel.Item item) {
                videoView.setMediaController(mediaController);
                videoView.setVideoPath(item.getMediaContent().getUrl());
                videoView.requestFocus();
                frameLayout.setVisibility(View.VISIBLE);
                videoView.start();
            }
        });

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        ApiService apiService = new ApiService();
        apiService.getResponse().enqueue(new Callback<Rss>() {

            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                if (response.body() != null) {
                    mAdapter.setChannelItems(response.body().getChannel().getItemList());
                }

                Log.e("Response success", response.message());
            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                Log.e("Response fail", t.getMessage());
            }

        });

         mCloseButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(mCloseButton != null) {
                     videoView.stopPlayback();
                     videoView.setMediaController(null);
                     frameLayout.setVisibility(View.INVISIBLE);
                 }
             }
         });
    }

    //understand and remove before sending it in
    public void durationFilter(List<Rss.Channel.Item> list) {
        List<Rss.Channel.Item> durationItems = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {

            if(list.get(i).getMediaContent().getDuration() == 120.0) {
                durationItems.add(list.get(i));
            }
        }
        mAdapter.setChannelItems(durationItems);
    }
}
package com.example.videotray;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaSession;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.videotray.model.Rss;
import com.example.videotray.services.ApiService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity  {
    VideoListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final VideoView videoView = findViewById(R.id.video_view);

        final android.widget.MediaController mediaController = new MediaController(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        mAdapter = new VideoListAdapter();
        mAdapter.setContext(this);

        mAdapter.setVideoClickListener(new VideoListAdapter.VideoClickListener() {
            FrameLayout frameLayout = findViewById(R.id.video_view_layout);
            @Override
            public void onVideoClicked(Rss.Channel.Item item) {
                //handle playing video
                Toast.makeText(getApplicationContext(), "video clicked", Toast.LENGTH_SHORT).show();

                Uri uri = Uri.parse("http://d1nixf144dcz0j.cloudfront.net/shade.mp4");

                videoView.setMediaController(mediaController);
                videoView.setVideoPath(item.getMediaContent().getUrl());
                videoView.requestFocus();
                frameLayout.setVisibility(View.VISIBLE);
                videoView.start();
            }

                    });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
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


    }
}
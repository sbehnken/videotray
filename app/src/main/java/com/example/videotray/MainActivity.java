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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private VideoListAdapter mAdapter;
    private FrameLayout mFrameLayout;
    private VideoView mVideoView;
    private RecyclerView mRecyclerView;
    private MediaController mMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton mCloseButton = findViewById(R.id.close_button);
        mMediaController = new MediaController(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mVideoView = findViewById(R.id.video_view);
        mFrameLayout = findViewById(R.id.video_view_layout);

        mAdapter = new VideoListAdapter();
        mAdapter.setContext(this);
        mAdapter.setVideoClickListener(new VideoListAdapter.VideoClickListener() {

            @Override
            public void onVideoClicked(Rss.Channel.Item item) {
                mVideoView.setMediaController(mMediaController);
                mVideoView.setVideoPath(item.getMediaContent().getUrl());
                mVideoView.requestFocus();
                mFrameLayout.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);
                mVideoView.start();
            }
        });

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

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
                mVideoView.stopPlayback();
                mVideoView.setMediaController(null);
                mFrameLayout.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        mVideoView.stopPlayback();
        mVideoView.setMediaController(null);
        mFrameLayout.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}

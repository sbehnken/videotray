package com.example.videotray;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.videotray.model.Rss;

import java.util.ArrayList;
import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoViewHolder> {
    private List<Rss.Channel.Item> channelItems = new ArrayList<>();
    private Context adapterContext;
    private VideoClickListener videoClickListener;

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.channel_row, viewGroup, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder videoViewHolder, int i) {
        videoViewHolder.bindData(channelItems.get(i), adapterContext, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoClickListener.onVideoClicked(videoViewHolder.getItem());
            }
        });
    }

    @Override
    public int getItemCount() {
        return channelItems.size();
    }

    void setChannelItems(List<Rss.Channel.Item> list) {
        channelItems = list;
        notifyDataSetChanged();
    }

    void setContext(Context context) {
        adapterContext = context;
    }

    void setVideoClickListener(VideoClickListener clickListener) {
        videoClickListener = clickListener;
    }

    public interface VideoClickListener {
        void onVideoClicked(Rss.Channel.Item item);
    }
}

package com.example.videotray;

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

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.channel_row, viewGroup, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int i) {
        videoViewHolder.bindData(channelItems.get(i));
    }

    @Override
    public int getItemCount() {
        return channelItems.size();
    }

    public List<Rss.Channel.Item> getChannelItems() {
        return channelItems;
    }

    public void setChannelItems(List<Rss.Channel.Item> list) {
        //call from onResponse in Main Activity with the channel list from the Rss object
        channelItems = list;
        notifyDataSetChanged();
    }

}

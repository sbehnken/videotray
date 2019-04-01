package com.example.videotray;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

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

    public List<Rss.Channel.Item> getChannelItems() {
        return channelItems;
    }

    public void setChannelItems(List<Rss.Channel.Item> list) {
        //call from onResponse in Main Activity with the channel list from the Rss object
        channelItems = list;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        adapterContext = context;
    }

    public void setVideoClickListener(VideoClickListener clickListener) {
        videoClickListener = clickListener;
    }

    public interface VideoClickListener {
        void onVideoClicked(Rss.Channel.Item item);
    }

//    public void filter(Double durationItem) {
//        List<Double> durationItems = new ArrayList<>();
//
//        for(int i = 0; i < channelItems.size(); i++) {
//            durationItems.add(channelItems.get(i).getMediaContent().getDuration());
//        }
//
//    }

//    private Filter mFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            return null;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//
//        }
//    };

    //example of android on click
//    public interface OnClickListener {
//        void onClick(View v);
//    }

}

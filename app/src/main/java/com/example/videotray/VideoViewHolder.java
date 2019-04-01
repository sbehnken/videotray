package com.example.videotray;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.videotray.model.Rss;
import com.squareup.picasso.Picasso;


public class VideoViewHolder extends RecyclerView.ViewHolder {
    private TextView mVideoTitle;
    private ImageView mVideoThumbnail;
    private TextView mVideoDuration;
    private View root;
    private Rss.Channel.Item item;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        root = itemView;

        mVideoTitle = itemView.findViewById(R.id.videoTitleTextView);
        mVideoThumbnail = itemView.findViewById(R.id.videoThumbnailImageView);
        mVideoDuration = itemView.findViewById(R.id.videoDurationTextView);
    }

    public void bindData(Rss.Channel.Item video, Context context, View.OnClickListener clickListener) {
        Picasso.with(context).load(video.getMediaContent().getMediaThumbnail().getUrl()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(mVideoThumbnail);


        mVideoTitle.setText(video.getTitle());

        float durationTime = video.getMediaContent().getDuration();
        float timeFormat =  durationTime / 60;
        int toMinute;
        toMinute = (int)timeFormat;
        String lengthOfTime = context.getResources().getString(R.string.duration_name) + toMinute + ":00";
        mVideoDuration.setText(lengthOfTime);

        root.setOnClickListener(clickListener);

        item = video;
}

    public Rss.Channel.Item getItem() {
        return item;
    }

}

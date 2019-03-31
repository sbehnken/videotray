package com.example.videotray;

import android.support.annotation.NonNull;

public class VideoViewModel {
    private String videoTitle;

    public VideoViewModel(@NonNull String videoTitle) {
        setVideoText(videoTitle);
    }

    @NonNull
    public String getVideoText() {
        return videoTitle;
    }

    public void setVideoText(@NonNull String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
package com.bzt.bztview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bzt.bztviewandroid.widget.CustomVideoView;

/**
 * Created by SHIBW-PC on 2015/12/3.
 */
public class VideoWithoutControllerActivity extends AppCompatActivity{

    private CustomVideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //videoView = (CustomVideoView) findViewById(R.id.videoview);

        videoView.setUri("http://v.theonion.com/onionstudios/video/3158/640.mp4");
        videoView.setLoadingInfo("提示", "正在加载...");
        videoView.setControlVideoEnable(false);
        videoView.play();
    }
}

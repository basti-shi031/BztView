package com.bzt.bztviewandroid.widget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * 播放视屏控件
 * Created by SHIBW-PC on 2015/12/3.
 */
public class CustomVideoView extends VideoView{

    private ProgressDialog progressDialog;
    private MediaController mediaController;
    private Button play;
    private String mUrl = "";
    private Boolean enableControl = true;
    public int mPositionWhenPaused = -1;
    public CustomVideoView(Context context) {
        this(context,null);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化一些功能
        initFunc(context);
    }

    private void initFunc(Context context) {
        progressDialog = new ProgressDialog(context);
        mediaController = new MediaController(context);
        ((Activity)context).getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

    public void setUri(String path){
        mUrl = path;
        Uri uri = Uri.parse(mUrl);
        setVideoURI(uri);
    }

    public void setLoadingInfo(String title,String message){
        progressDialog.setMessage(title);
        progressDialog.setMessage(message);
    }

    public void play(){
        progressDialog.show();
        setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(false);
                progressDialog.dismiss();
                start();
            }
        });
        setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                progressDialog.dismiss();
                return true;
            }
        });
    }

    public void setControlVideoEnable(boolean enable){
        enableControl = enable;

        if (enableControl){
            setMediaController(mediaController);
        }else {
            setMediaController(null);
        }
    }

    public void onPause(){
        mPositionWhenPaused = getCurrentPosition();
        pause();
    }

    public void release(){
        stopPlayback();
    }

    public void onResume() {
        // Resume video player
        if (mPositionWhenPaused >= 0) {
            seekTo(mPositionWhenPaused);
            mPositionWhenPaused = -1;
            start();
        }
    }
}

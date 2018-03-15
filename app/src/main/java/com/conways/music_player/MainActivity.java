package com.conways.music_player;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends BasePlayerActivity implements View.OnClickListener, ServiceConnection {
    public static final String TAG="zzzzzzzzzzz";

    private ImageView ivPoster;
    private SeekBar progress;
    private ImageView ivPrevious;
    private ImageView ivPlay;
    private ImageView ivNext;

    private PlayInterface player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 00000000000000");
        setContentView(R.layout.activity_main);
        init();
        bindService();
    }

    private void init() {
        ivPoster = $(R.id.poster);
        ivPoster.setOnClickListener(this);
        progress = $(R.id.seekBar);
        ivPrevious = $(R.id.previous);
        ivPlay = $(R.id.play);
        ivNext = $(R.id.next);
        ivPrevious.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
    }

    @Override
    protected void onPlayerStart() {
        super.onPlayerStart();
    }

    @Override
    protected void onPlayerProgress(int progress, int duration) {
        super.onPlayerProgress(progress, duration);
        Log.d("zzzz", "onPlayerProgress progress: " + progress);
        Log.d("zzzz", "onPlayerProgress duration: " + duration);
        int sekBarProgress = this.progress.getMax() * progress / duration;
        this.progress.setProgress(sekBarProgress);
    }

    private void bindService() {
        this.bindService(new Intent(this, PlayerService.class), this, BIND_AUTO_CREATE);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.poster:
                startActivity(new Intent(this,LyricActivity.class));
                break;
            case R.id.previous:
                final AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(MainActivity.this);
                normalDialog.setIcon(R.mipmap.ic_launcher);
                normalDialog.setTitle("我是一个普通Dialog");
                normalDialog.setMessage("你要点击哪一个按钮呢?");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //...To-do
                            }
                        });
                normalDialog.setNegativeButton("关闭",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //...To-do
                            }
                        });
                // 显示
                normalDialog.show();
                break;
            case R.id.next:
                break;
            case R.id.play:
                try {
                    player.play(MusicManager.getInstance(this).getLocalMusics().get(1));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: 11111111111");
    }


    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        player = PlayInterface.Stub.asInterface(iBinder);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
    }




}

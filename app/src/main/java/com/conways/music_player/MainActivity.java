package com.conways.music_player;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends BaseActivity implements View.OnClickListener, ServiceConnection {


    private ImageView ivPoster;
    private SeekBar progress;
    private ImageView ivPrevious;
    private ImageView ivPlay;
    private ImageView ivNext;

    private PlayInterface player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        bindService();

    }

    private void init() {
        ivPoster = $(R.id.poster);
        progress = $(R.id.seekBar);
        ivPrevious = $(R.id.previous);
        ivPlay = $(R.id.play);
        ivNext = $(R.id.next);
        ivPrevious.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
    }

    private void bindService() {
        this.bindService(new Intent(this, PlayerService.class), this, BIND_AUTO_CREATE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.previous:

                break;
            case R.id.next:
                break;
            case R.id.play:
                try {
                    Music music = new Music();
                    music.setUri("music");
                    player.play(music);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        player = PlayInterface.Stub.asInterface(iBinder);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
}

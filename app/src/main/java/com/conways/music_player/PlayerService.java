package com.conways.music_player;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import java.io.IOException;

import static com.conways.music_player.PlayerConstants.BroadCastActions.PLAYER_PROGRESS;
import static com.conways.music_player.PlayerConstants.BroadCastKeys.DURATION;
import static com.conways.music_player.PlayerConstants.BroadCastKeys.PROGRESS;

public class PlayerService extends Service implements MediaPlayer.OnPreparedListener{

    private MediaPlayer player;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1000&&player.getCurrentPosition()<player.getDuration()){
                Intent intent=new Intent();
                intent.setAction(PLAYER_PROGRESS);
                intent.putExtra(PROGRESS,player.getCurrentPosition());
                intent.putExtra(DURATION,player.getDuration());
                PlayerService.this.sendBroadcast(intent);
                handler.sendEmptyMessageDelayed(1000,100l);
            }

        }
    };

    private PlayInterface.Stub stub=new PlayInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public float getPlayerProgress() throws RemoteException {
            return 0;
        }

        @Override
        public void play(Music music) throws RemoteException {
            Log.d("zzzzzzzzz", "play: "+music.getUri());
            if (null==player){
                return;
            }
            PlayerService.this.play(music);
        }

        @Override
        public void pause() throws RemoteException {
            if (null==player){
                return;
            }


        }

        @Override
        public void playNext() throws RemoteException {

        }

        @Override
        public void playPrevious() throws RemoteException {

        }



    };


    public PlayerService() {
    }

    private void init() {
        player=new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
//        mediaPlayer.start();
    }

    private void play(Music music){
        try {
            player.reset();
            player.setDataSource(music.getUri());
            player.prepare();
            player.start();
            handler.sendEmptyMessageAtTime(1000,100l);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.conways.music_player;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class PlayerService extends Service {

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
        }
    };


    public PlayerService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
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
}

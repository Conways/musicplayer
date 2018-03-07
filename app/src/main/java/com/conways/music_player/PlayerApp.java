package com.conways.music_player;

import android.app.Application;
import android.content.Intent;

/**
 * Created by Conways on 2018/3/7.
 */

public class PlayerApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this,PlayerService.class));
    }
}

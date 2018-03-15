package com.conways.music_player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

import static com.conways.music_player.PlayerConstants.BroadCastKeys.DURATION;
import static com.conways.music_player.PlayerConstants.BroadCastKeys.PROGRESS;

/**
 * Created by Conways on 2018/3/8.
 */

public abstract class BasePlayerActivity extends BaseActivity {

    private PlayerReceiver playerReceiver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(playerReceiver);
    }

    private void init() {
        playerReceiver = new PlayerReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(PlayerConstants.BroadCastActions.PLAYER_PROGRESS);
        filter.addAction(PlayerConstants.BroadCastActions.PLAYER_START);
        filter.addAction(PlayerConstants.BroadCastActions.PLAYER_COMPLETE);
        filter.addAction(PlayerConstants.BroadCastActions.PLAYER_ERROR);
        registerReceiver(playerReceiver, filter);
    }

    protected void onPlayerStart() {

    }

    ;

    protected void onBufferProgress() {

    }

    ;

    protected void onPlayerProgress(int progress,int duration) {

    }

    ;

    protected void onPlayerComplete() {

    }

    ;

    protected void onPlayerError() {

    }
    ;


    class PlayerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String actions = intent.getAction();
            switch (actions) {
                case PlayerConstants.BroadCastActions.PLAYER_PROGRESS:
                    onPlayerProgress(intent.getExtras().getInt(PROGRESS),
                            intent.getExtras().getInt(DURATION));
                    break;
                case PlayerConstants.BroadCastActions.PLAYER_START:
                    onPlayerStart();
                    break;
                case PlayerConstants.BroadCastActions.PLAYER_COMPLETE:
                    onPlayerComplete();
                    break;
                case PlayerConstants.BroadCastActions.PLAYER_ERROR:
                    onPlayerError();
                    break;

                default:
                    break;


            }


        }
    }


}

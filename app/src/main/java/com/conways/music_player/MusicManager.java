package com.conways.music_player;

/**
 * Created by Conways on 2018/3/7.
 */

class MusicManager {
    private static final MusicManager ourInstance = new MusicManager();

    static MusicManager getInstance() {
        return ourInstance;
    }

    private MusicManager() {
    }
}

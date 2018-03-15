package com.conways.music_player;

/**
 * Created by Conways on 2018/3/8.
 */

public class PlayerConstants {

    private PlayerConstants() {
    }

    public static class BroadCastActions {
        public static final String PLAYER_ACTIONS = "player_actions";
        public static final String PLAYER_PROGRESS = "com.conways.music_player.player_progress";
        public static final String PLAYER_START = "com.conways.music_player.player_start";
        public static final String PLAYER_ERROR = "com.conways.music_player.player_error";
        public static final String PLAYER_COMPLETE = "com.conways.music_player.player_complete";
    }
    public static class BroadCastKeys {
        public static final String PROGRESS = "progress";
        public static final String DURATION = "duration";
        public static final String START = "start";
        public static final String ERROR = "error";
        public static final String COMPLETE = "complete";
    }
}

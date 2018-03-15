package com.conways.music_player;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Conways on 2018/3/7.
 */

class MusicManager {
    private static MusicManager ourInstance;
    private Context context;

    public static MusicManager getInstance(Context context) {
        if (null == ourInstance) {
            synchronized (MusicManager.class) {
                if (null == ourInstance) {
                    ourInstance = new MusicManager(context);
                }
            }
        }
        return ourInstance;
    }

    private MusicManager(Context context) {
        this.context = context.getApplicationContext();
    }

    private List<Music> localMusics = new ArrayList<>();

    public List<Music> getLocalMusics() {
        if (localMusics.size() <= 0) {
            Cursor mAudioCursor = context.getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null,
                    null,
                    null,
                    MediaStore.Audio.AudioColumns.DURATION);// 排序方式
            for (int i = 0; i < mAudioCursor.getCount(); i++) {
                mAudioCursor.moveToNext();
                int indexPath = mAudioCursor
                        .getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
                int indexARTIST = mAudioCursor
                        .getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
                int indexALBUM = mAudioCursor
                        .getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
                String path = mAudioCursor.getString(indexPath);
                String artist = mAudioCursor.getString(indexARTIST);
                String album = mAudioCursor.getString(indexALBUM);
                Music music = new Music();
                music.setUri(path);
                localMusics.add(music);
            }
            mAudioCursor.close();
        }
        return localMusics;
    }
}
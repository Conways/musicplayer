// PlayInterface.aidl
package com.conways.music_player;
import com.conways.music_player.Music;

// Declare any non-default types here with import statements

interface PlayInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    float getPlayerProgress();
    void play(in Music music);


}

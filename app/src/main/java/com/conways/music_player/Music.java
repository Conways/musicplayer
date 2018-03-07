package com.conways.music_player;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Conways on 2018/3/7.
 */

public class Music implements Parcelable {
    private String uri;


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uri);
    }

    public Music() {
    }

    protected Music(Parcel in) {
        this.uri = in.readString();
    }

    public static final Parcelable.Creator<Music> CREATOR = new Parcelable.Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel source) {
            return new Music(source);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };
}

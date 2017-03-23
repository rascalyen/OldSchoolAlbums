package com.rascal.oldschoolalbums.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yenhuang on 3/22/17.
 */
public class AlbumTitle implements Parcelable {

    private int userId;
    private int id;
    private String title;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.userId);
        dest.writeValue(this.id);
        dest.writeString(this.title);
    }

    public AlbumTitle() {
    }

    protected AlbumTitle(Parcel in) {
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
    }

    public static final Creator<AlbumTitle> CREATOR = new Creator<AlbumTitle>() {
        @Override
        public AlbumTitle createFromParcel(Parcel source) {
            return new AlbumTitle(source);
        }

        @Override
        public AlbumTitle[] newArray(int size) {
            return new AlbumTitle[size];
        }
    };

}
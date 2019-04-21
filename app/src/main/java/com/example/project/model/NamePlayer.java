package com.example.project.model;

public class NamePlayer {
    private int mID;
    private String mName;

    public NamePlayer(int mID, String mName) {
        this.mID = mID;
        this.mName = mName;
    }

    public NamePlayer(String mName) {
        this.mName = mName;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}

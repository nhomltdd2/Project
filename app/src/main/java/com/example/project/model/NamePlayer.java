package com.example.project.model;

public class NamePlayer {
    private int mID;
    private String mName;
    private String mScore;

    public NamePlayer(int mID, String mName, String mScore) {
        this.mID = mID;
        this.mName = mName;
        this.mScore = mScore;
    }

    public NamePlayer(String mName ,String mScore) {
        this.mName = mName;
        this.mScore = mScore;
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

    public String getmScore() {
        return mScore;
    }

    public void setmScore(String mScore) {
        this.mScore = mScore;
    }
}
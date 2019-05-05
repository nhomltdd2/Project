package com.example.project;


public class DataModel {


    String name;
    String score;
    int id_;

    //thy: thêm hàm khởi tạo mặc định
    public DataModel( ) {
    }
    public DataModel(String name, String score, int id_ ) {
        this.name = name;
        this.score = score;
        this.id_ = id_;

    }


    public String getName() {
        return name;
    }


    public String getScore() {
        return score;
    }


    public int getId() {
        return id_;
    }

    //thy: thêm các hàm set
    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

}
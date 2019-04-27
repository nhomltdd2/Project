package com.example.project;


public class DataModel {


    String name;
    String rank;
    int id_;

    //thy: thêm hàm khởi tạo mặc định
    public DataModel( ) {
    }
    public DataModel(String name, String rank, int id_ ) {
        this.name = name;
        this.rank = rank;
        this.id_ = id_;

    }


    public String getName() {
        return name;
    }


    public String getRank() {
        return rank;
    }


    public int getId() {
        return id_;
    }

    //thy: thêm các hàm set
    public void setName(String name) {
        this.name = name;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

}
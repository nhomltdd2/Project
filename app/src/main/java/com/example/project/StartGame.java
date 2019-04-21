package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartGame extends AppCompatActivity {

    private Button btnBatDau;
    private Button btnHuongDan;
    private Button btnXepHang;
    private Button btnChedo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        btnBatDau = (Button) findViewById(R.id.btnBatDau);
        btnHuongDan = (Button) findViewById(R.id.btnHuongDan);
        btnXepHang = (Button) findViewById(R.id.btnXepHang);
        btnChedo = (Button) findViewById(R.id.btnChedo);

        btnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartGame.this, InputNameActivity.class);
                startActivity(intent);
            }
        });

        btnXepHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartGame.this, RankActivity.class);
                startActivity(intent);
            }
        });

//        btnHuongDan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(StartGame.this, Gui.class);
//                startActivity(intent);
//            }
//        });

//        btnLichSu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(StartGame.this, Mode.class);
//                startActivity(intent);
//            }
//        });


    }

}

package com.example.project;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Locale;

public class StartGame extends AppCompatActivity {

    private Button btnBatDau;
    private Button btnXepHang;
    private ImageView imgMy, imgVN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        btnBatDau = (Button) findViewById(R.id.btnBatDau);
        btnXepHang = (Button) findViewById(R.id.btnXepHang);
        imgMy = (ImageView) findViewById(R.id.imgMy);
        imgVN = (ImageView) findViewById(R.id.imgVN);


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

        imgMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganNgonNgu("en");
            }
        });

        imgVN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganNgonNgu("vi");
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
    public void ganNgonNgu(String language){
        Locale locale = new Locale(language);

        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(
                config,
                getBaseContext().getResources().getDisplayMetrics()
        );
        Intent inten = new Intent(StartGame.this, StartGame.class);
        startActivity(inten);
    }

}

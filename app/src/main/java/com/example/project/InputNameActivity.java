package com.example.project;


import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.project.data.DBManager;
import com.example.project.model.NamePlayer;


public class InputNameActivity extends AppCompatActivity {


    public Button btnStart;
    public EditText edtName;



    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inputname);
        final DBManager dbManager = new DBManager(this);
        edtName = (EditText) findViewById(R.id.edtName);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed_text = edtName.getText().toString().trim();
                NamePlayer namePlayer = createNamePlayer();

                if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null)
                {
                    //EditText is empty
                    Toast.makeText(InputNameActivity.this, "You did not enter a username", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    dbManager.addName(namePlayer);
                    Intent intent = new Intent(InputNameActivity.this, MainActivity.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }
        });


        //set action bar
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_music_note_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }
    private NamePlayer createNamePlayer(){
        String name = edtName.getText().toString();
        NamePlayer namePlayer = new NamePlayer(name);
        return namePlayer;
    }

    //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //menu item selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        switch (item.getItemId()) {
            case android.R.id.home:
                if(mp == null){
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.fantacy);
                    mp.start();
                    Toast.makeText(InputNameActivity.this, "Music is playing now", Toast.LENGTH_SHORT).show();
                }else {
                    mp.stop();
                    mp = null;
                    Toast.makeText(InputNameActivity.this, "Music is stop", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.action_close:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void initView() {
    }
}

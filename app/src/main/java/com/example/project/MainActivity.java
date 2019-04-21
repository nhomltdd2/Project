package com.example.project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import com.example.project.Adapter.GripViewAnswerAdapter;
import com.example.project.Adapter.GripViewSuggestAdapter;
import com.example.project.Common.Common;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView txt_coin;
    private TextView txt_heart;

    private int coin = 0;
    private int heart = 5;

    public static final String POINT = "point";

    public static final String BUNDLE = "bundel";
    static Intent intent;
    public List<String> suggestSource = new ArrayList<>();

    public GripViewAnswerAdapter answerAdapter;
    public GripViewSuggestAdapter suggestAdapter;

    public Button btnSubmit, btnTranfer;

    public TextView counter;

    public GridView gripViewAnswer, gripViewSuggest;

    public ImageView imgViewQuestion;

    private final int[] image_list = {
            R.drawable.android,
            R.drawable.facebook,
            R.drawable.instagram,
            R.drawable.snapchat,
            R.drawable.twitter,
            R.drawable.youtube,
            R.drawable.aimo,
            R.drawable.badong,
            R.drawable.baola,
            R.drawable.baophu,
            R.drawable.dauthu,
            R.drawable.badong,
            R.drawable.gaungua,

    };

    public char[] answer;
    private long timer = 50000;

    private static final Pattern REGEX_PATTERN =
            Pattern.compile("(.)\\1*");

    String correct_answer;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnTranfer = (Button) findViewById(R.id.btnTranfer);

        //set action bar
        ActionBar actionBar = getSupportActionBar();
        Drawable drawable = getResources().getDrawable(R.drawable.ic_music_note_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);

        //Init View
        initView();

        btnTranfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, PointActivity.class);
                //truyền dữ liệu điểm
                Bundle bundle = new Bundle();
                bundle.putString(POINT, txt_coin.getText().toString());
                intent.putExtra(BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }

    //timer
    CountDownTimer time = new CountDownTimer(timer, 1000) {

        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = (TimeUnit.MILLISECONDS.toHours(millis)) + ":" + (TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))) + ":" + (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            counter.setText(hms);
            timer = millis;
        }

        @Override
        public void onFinish() {
            counter.setText("Your time is up!");
            intent = new Intent(MainActivity.this, PointActivity.class);
            //truyền dữ liệu điểm
            Bundle bundle = new Bundle();
            bundle.putString(POINT, txt_coin.getText().toString());
            intent.putExtra(BUNDLE, bundle);
            startActivity(intent);
        }
    };

    //create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        time.start();

        return true;
    }

    //menu item selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mp == null) {
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.fantacy);
                    mp.start();
                    Toast.makeText(MainActivity.this, "Music is playing now", Toast.LENGTH_SHORT).show();
                } else {
                    mp.stop();
                    mp = null;
                    Toast.makeText(MainActivity.this, "Music is stop", Toast.LENGTH_SHORT).show();
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
        counter = (TextView) findViewById(R.id.timer);
        gripViewAnswer = (GridView) findViewById(R.id.gripViewAnswer);
        gripViewSuggest = (GridView) findViewById(R.id.gripViewSuggest);

        imgViewQuestion = (ImageView) findViewById(R.id.imgLogo);

        txt_coin = (TextView) findViewById(R.id.txt_coin);
        txt_heart = (TextView) findViewById(R.id.txt_avatar);


        //Add setup here
        setupList();

        btnSubmit = (Button) findViewById(R.id.btnCheck);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                for (int i = 0; i < Common.user_submit_answer.length; i++) {
                    result += String.valueOf(Common.user_submit_answer[i]);
                }

                if (result.equals(correct_answer)) {
                    coin += 100;
                    txt_coin.setText(coin + "");

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("Correct! This is " + result +
                            "You want go to next question?.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Reset
                                    Common.count = 0;
                                    Common.user_submit_answer = new char[correct_answer.length()];

                                    //set Adapter
                                    GripViewAnswerAdapter answerAdapter = new GripViewAnswerAdapter(setupNullList(), getApplicationContext());
                                    gripViewAnswer.setAdapter(answerAdapter);
                                    answerAdapter.notifyDataSetChanged();

                                    GripViewSuggestAdapter suggestAdapter = new GripViewSuggestAdapter(suggestSource, getApplicationContext(), MainActivity.this);
                                    gripViewSuggest.setAdapter(suggestAdapter);
                                    suggestAdapter.notifyDataSetChanged();

                                    time.cancel();
                                    time.start();

                                    setupList();

                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Toast.makeText(MainActivity.this, "Go to Point now!", Toast.LENGTH_SHORT).show();
                                    intent = new Intent(MainActivity.this, PointActivity.class);
                                    //truyền dữ liệu điểm
                                    Bundle bundle = new Bundle();
                                    bundle.putString(POINT, txt_coin.getText().toString());
                                    intent.putExtra(BUNDLE, bundle);
                                    startActivity(intent);

                                    //save score
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                } else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("Your answer is wrong " +
                            "Do you want go to next question?.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //Reset
                                    Common.count = 0;
                                    Common.user_submit_answer = new char[correct_answer.length()];

                                    //set Adapter
                                    GripViewAnswerAdapter answerAdapter = new GripViewAnswerAdapter(setupNullList(), getApplicationContext());
                                    gripViewAnswer.setAdapter(answerAdapter);
                                    answerAdapter.notifyDataSetChanged();

                                    GripViewSuggestAdapter suggestAdapter = new GripViewSuggestAdapter(suggestSource, getApplicationContext(), MainActivity.this);
                                    gripViewSuggest.setAdapter(suggestAdapter);
                                    suggestAdapter.notifyDataSetChanged();

                                    time.cancel();
                                    time.start();

                                    setupList();

                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Toast.makeText(MainActivity.this, "Go to Point now!", Toast.LENGTH_SHORT).show();
                                    intent = new Intent(MainActivity.this, PointActivity.class);
                                    //truyền dữ liệu điểm
                                    Bundle bundle = new Bundle();
                                    bundle.putString(POINT, txt_coin.getText().toString());
                                    intent.putExtra(BUNDLE, bundle);
                                    startActivity(intent);

                                    //save score
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });

    }

    private void setupList() {
        //random Logo
        Random random = new Random();
        int imageSelected = image_list[random.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageSelected);

        correct_answer = getResources().getResourceName(imageSelected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/") + 1);

        answer = correct_answer.toCharArray();

        Common.user_submit_answer = new char[answer.length];

        //Add answer character to list
        suggestSource.clear();
        for (char item : answer) {
            //Add logo name to list
            suggestSource.add(String.valueOf(item));
        }

        //Random add some character to list
        for (int i = answer.length; i < answer.length * 2; i++) {
            suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);
        }

        //sort random
        Collections.shuffle(suggestSource);

        //detele duplicate
        Set<String> s = new LinkedHashSet<String>(suggestSource);
        suggestSource.clear();
        suggestSource.addAll(s);

        //set for GripView
        answerAdapter = new GripViewAnswerAdapter(setupNullList(), this);
        suggestAdapter = new GripViewSuggestAdapter(suggestSource, this, this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gripViewSuggest.setAdapter(suggestAdapter);
        gripViewAnswer.setAdapter(answerAdapter);

    }

    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for (int i = 0; i < answer.length; i++) {
            result[i] = ' ';
        }
        return result;
    }


}


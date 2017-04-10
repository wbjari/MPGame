package com.example.gebruiker.tictactoe.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.gebruiker.tictactoe.R;
import com.example.gebruiker.tictactoe.model.Player;

import java.util.concurrent.TimeUnit;

/**
 * Created by wbjar on 10-4-2017.
 */

public class EndScreenActivity extends AppCompatActivity {

    private TextView textViewScore;
    private Button buttonMainMenu, buttonCloseGame;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endscreen);

        textViewScore = (TextView) findViewById(R.id.textViewScore);
        buttonMainMenu = (Button) findViewById(R.id.buttonLost);
        buttonCloseGame = (Button) findViewById(R.id.buttonNextGame);

        //Get intent.
        Bundle extras = getIntent().getExtras();

        score = extras.getString("score");

        textViewScore.setText("Uw score: " + score);

    }


}

package com.example.gebruiker.tictactoe.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gebruiker.tictactoe.R;

/**
 * Created by wbjar on 10-4-2017.
 */

public class EndScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewScore;
    private Button buttonMainMenu, buttonRestart;
    private String score, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endscreen);

        textViewScore = (TextView) findViewById(R.id.textViewScore);
        buttonMainMenu = (Button) findViewById(R.id.buttonMainMenu);
        buttonRestart = (Button) findViewById(R.id.buttonRestart);

        //Get intent.
        Bundle extras = getIntent().getExtras();
        score = extras.getString("score");
        name = extras.getString("name");

        textViewScore.setText("Uw score: " + score);

        buttonMainMenu.setOnClickListener(this);
        buttonRestart.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.buttonMainMenu) {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.buttonRestart) {
            Intent intent = new Intent(view.getContext(), GameActivity.class);
            intent.putExtra("playerName", name);
            intent.putExtra("startFigure", "O");
            startActivity(intent);
        }

    }


}

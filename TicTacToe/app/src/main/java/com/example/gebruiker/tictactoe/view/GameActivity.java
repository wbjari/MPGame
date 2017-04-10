package com.example.gebruiker.tictactoe.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gebruiker.tictactoe.model.Player;
import com.example.gebruiker.tictactoe.model.PlayerDBHandler;
import com.example.gebruiker.tictactoe.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static android.R.color.holo_blue_light;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by wbjar on 16-1-2017.
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button a1, a2, a3, b1, b2, b3, c1, c2, c3;
    private Button buttonNextGame, buttonLost;
    private Button[] buttonArray;
    private Boolean turn = true; // If true, turn = X. If false, turn = O.
    private int turnCount = 0;
    private TextView textViewScore;
    Player player;

    // Timer
    CountDownTimer gamecounter;
    // Timer starttijd
    private int startTimer = 120; // in seconden
    // Timer format
    private static final String FORMAT = "%02d:%02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Timer
        final TextView textViewTimer = (TextView) findViewById(R.id.textViewTimer);

        gamecounter = new CountDownTimer(startTimer*1000, 1000) {

            public void onTick(long millisUntilFinished) {

                textViewTimer.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                textViewTimer.setText("Je hebt verloren!");
                afterLose();

            }
        }.start();

        // Get Intent.
        Intent intent = getIntent();

        // Maak nieuwe speler aan
        player = new Player();
        player.name = intent.getStringExtra("playerName");
        player.score = 0;

        TextView textViewPlayerName = (TextView) findViewById(R.id.textViewPlayerName);
        textViewPlayerName.setText(player.name);

        buttonNextGame = (Button) findViewById(R.id.buttonNextGame);
        buttonLost = (Button) findViewById(R.id.buttonLost);
        textViewScore = (TextView) findViewById(R.id.textViewScore);

        // Link buttons to buttons in layout.
        a1 = (Button) findViewById(R.id.buttonA1);
        a2 = (Button) findViewById(R.id.buttonA2);
        a3 = (Button) findViewById(R.id.buttonA3);
        b1 = (Button) findViewById(R.id.buttonB1);
        b2 = (Button) findViewById(R.id.buttonB2);
        b3 = (Button) findViewById(R.id.buttonB3);
        c1 = (Button) findViewById(R.id.buttonC1);
        c2 = (Button) findViewById(R.id.buttonC2);
        c3 = (Button) findViewById(R.id.buttonC3);

        // Insert all buttons into the array.
        buttonArray = new Button[]{a1, a2, a3, b1, b2, b3, c1, c2, c3};

        // Set the click listener for each button.
        for(Button button : buttonArray) {
            button.setOnClickListener(this);
        }

//        b2.performClick();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonNextGame) {
            buttonNextGame.setVisibility(View.INVISIBLE);
            clearPlayField();
            if(!turn) {
                performComputerTurn();
            }
        } else {
            Button button = (Button) view;
            buttonClicked(button);
        }

        if(view.getId() == R.id.buttonLost) {
            Intent intent = new Intent(view.getContext(), EndScreenActivity.class);
            intent.putExtra("score", String.valueOf(player.score));
            intent.putExtra("name", String.valueOf(player.name));
            startActivity(intent);
        }
    }

    private void clearPlayField() {
        for(Button button : buttonArray) {
            button.setText("");
            button.setBackgroundColor(getResources().getColor(holo_blue_light));
            button.setClickable(true);
            turnCount = 0;
        }
    }

    public void buttonClicked(Button b) {
        if(turn) {
            b.setText("X");
            afterButtonClicked(b);
        }
        if(!checkWinner()) {
            performComputerTurn();
        }
    }

    private void performComputerTurn() {
        if(!turn) {
            int i = new Random().nextInt(9);
            Log.d("int", ""+i);
            selectRandomButton(i);
        }
    }

    private void selectRandomButton(int i) {
        if(i == 0) {
            if(a1.isClickable()) {
                a1.setText("O");
                afterButtonClicked(a1);
            } else {
                performComputerTurn();
            }
        } else if(i == 1) {
            if(a2.isClickable()) {
                a2.setText("O");
                afterButtonClicked(a2);
            } else {
                performComputerTurn();
            }
        } else if(i == 2) {
            if(a3.isClickable()) {
                a3.setText("O");
                afterButtonClicked(a3);
            } else {
                performComputerTurn();
            }
        } else if(i == 3) {
            if(b1.isClickable()) {
                b1.setText("O");
                afterButtonClicked(b1);
            } else {
                performComputerTurn();
            }
        } else if(i == 4) {
            if(a2.isClickable()) {
                a2.setText("O");
                afterButtonClicked(a2);
            } else {
                performComputerTurn();
            }
        } else if(i == 5) {
            if(b3.isClickable()) {
                b3.setText("O");
                afterButtonClicked(b3);
            } else {
                performComputerTurn();
            }
        } else if(i == 6) {
            if(c1.isClickable()) {
                c1.setText("O");
                afterButtonClicked(c1);
            } else {
                performComputerTurn();
            }
        } else if(i == 7) {
            if(c2.isClickable()) {
                c2.setText("O");
                afterButtonClicked(c2);
            } else {
                performComputerTurn();
            }
        } else if(i == 8) {
            if(c3.isClickable()) {
                c3.setText("O");
                afterButtonClicked(c3);
            } else {
                performComputerTurn();
            }
        }
        checkWinner();
    }

    private void afterButtonClicked(Button b) {
        b.setClickable(false);
        turn = !turn;
        turnCount++;
    }

    private boolean checkWinner() {
        boolean winner = false;
        String color = "#000000";

        // Check horizontal.
        if (a1.getText() == a2.getText() && a2.getText() == a3.getText() && !a1.isClickable()) {
            a1.setBackgroundColor(Color.parseColor(color));
            a2.setBackgroundColor(Color.parseColor(color));
            a3.setBackgroundColor(Color.parseColor(color));
            winner = true;
        } else if (b1.getText() == b2.getText() && b2.getText() == b3.getText() && !b1.isClickable()) {
            b1.setBackgroundColor(Color.parseColor(color));
            b2.setBackgroundColor(Color.parseColor(color));
            b3.setBackgroundColor(Color.parseColor(color));
            winner = true;
        } else if (c1.getText() == c2.getText() && c2.getText() == c3.getText() && !c1.isClickable()) {
            c1.setBackgroundColor(Color.parseColor(color));
            c2.setBackgroundColor(Color.parseColor(color));
            c3.setBackgroundColor(Color.parseColor(color));
            winner = true;
        }

        // Check vertical
        else if (a1.getText() == b1.getText() && b1.getText() == c1.getText() && !a1.isClickable()) {
            a1.setBackgroundColor(Color.parseColor(color));
            b1.setBackgroundColor(Color.parseColor(color));
            c1.setBackgroundColor(Color.parseColor(color));
            winner = true;
        } else if (a2.getText() == b2.getText() && b2.getText() == c2.getText() && !b2.isClickable()) {
            a2.setBackgroundColor(Color.parseColor(color));
            b2.setBackgroundColor(Color.parseColor(color));
            c2.setBackgroundColor(Color.parseColor(color));
            winner = true;
        } else if (a3.getText() == b3.getText() && b3.getText() == c3.getText() && !c3.isClickable()) {
            a3.setBackgroundColor(Color.parseColor(color));
            b3.setBackgroundColor(Color.parseColor(color));
            c3.setBackgroundColor(Color.parseColor(color));
            winner = true;
        }

        // Check diagonal.
        else if (a1.getText() == b2.getText() && b2.getText() == c3.getText() && !a1.isClickable()) {
            a1.setBackgroundColor(Color.parseColor(color));
            b2.setBackgroundColor(Color.parseColor(color));
            c3.setBackgroundColor(Color.parseColor(color));
            winner = true;
        } else if (a3.getText() == b2.getText() && b2.getText() == c1.getText() && !b2.isClickable()) {
            a3.setBackgroundColor(Color.parseColor(color));
            b2.setBackgroundColor(Color.parseColor(color));
            c1.setBackgroundColor(Color.parseColor(color));
            winner = true;
        }

        if (winner) {
            if (!turn) { // X wins.
                player.score++;
                setScore(player.score);
                notification("Je hebt gewonnen!");
                afterGameEnd();
                return true;
            } else { // O wins.
                notification("Je hebt verloren!");
                afterLose();
                return true;
            }

        } else if (turnCount == 9) {
            notification("Het is gelijkspel!");
            afterGameEnd();
            return true;
        }
        return false;
    }

    private void afterLose() {
        // Stop timer
        gamecounter.cancel();
        buttonNextGame.setVisibility(View.INVISIBLE);

        for (Button button : buttonArray) {
            button.setClickable(false);
        }

        buttonLost.setVisibility(View.VISIBLE);
        buttonLost.setOnClickListener(this);


        // Voeg speler toe aan highscores
        PlayerDBHandler pdb = new PlayerDBHandler(getApplicationContext());
        pdb.addPlayer(player);
    }

    private void notification(String s) {
        Context context = getApplicationContext();
        CharSequence text = s;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void afterGameEnd() {

        buttonNextGame.setVisibility(View.VISIBLE);
        buttonNextGame.setOnClickListener(this);

        for (Button button : buttonArray) {
            button.setClickable(false);
        }

    }

    public void setScore(int score) {
        player.score = score;
        textViewScore.setText("Score: " + player.score);
    }
}

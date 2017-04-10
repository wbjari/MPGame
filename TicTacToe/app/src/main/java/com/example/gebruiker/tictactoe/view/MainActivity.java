package com.example.gebruiker.tictactoe.view;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.gebruiker.tictactoe.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Timer starttijd (voor afwezigheid)
    private int startTimer = 15; // in seconden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Timer start
        final CountDownTimer iddlecounter = new CountDownTimer(startTimer*1000, 1000) {
            @Override
            public void onTick(long l) {

            }

            public void onFinish() {
                Log.i(TAG, "onTimerEnd: redirect -> idle activity");
                Intent intent = new Intent(MainActivity.this, IdleActivity.class);
                startActivity(intent);
            }
        }.start();
        // Timer end

        // Knop die naar de prestartgame pagina gaat
        Button buttonStartGame = (Button) findViewById(R.id.buttonStartGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "onClick: redirect -> prestartgame activity");
                iddlecounter.cancel();
                Intent intent = new Intent(MainActivity.this, PregameActivity.class);
                startActivity(intent);
            }
        });

        // Knop die naar de highscores pagina gaat
        Button buttonHighscores = (Button) findViewById(R.id.buttonHighscores);
        buttonHighscores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "onClick: redirect -> highscores activity");
                iddlecounter.cancel();
                Intent intent = new Intent(getBaseContext(), HighscoreActivity.class);
                startActivity(intent);
            }
        });

        // Knop die naar de over ons pagina gaat
        Button buttonAboutUs = (Button) findViewById(R.id.buttonAboutUs);
        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "onClick: redirect -> aboutus activity");
                iddlecounter.cancel();
                Intent intent = new Intent(MainActivity.this, AboutusActivity.class);
                startActivity(intent);
            }
        });

        // Knop om applicatie af te sluiten
        Button buttonExitApplication = (Button) findViewById(R.id.closeApplication);
        buttonExitApplication.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Sluit de app af
                iddlecounter.cancel();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

//        // Genereer een willekeurige highscores spelerslijst
//        PlayerDBHandler pdb = new PlayerDBHandler(getApplicationContext());
//        Player p;
//
//        int range = (100 - 1) + 1;
//        for (int i = 0; i < 20; i++) {
//            int random = (int)(Math.random() * range) + 1;
//
//            p = new Player();
//            p.name = "Speler" + i;
//            p.score = random;
//            pdb.addPlayer(p);
//
//            Log.i(TAG, "onCreate: Generated player with name: " + p.name);
//        }

        // Reset timer bij interactie
        LinearLayout rlayout = (LinearLayout) findViewById(R.id.activity_main);
        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iddlecounter.cancel();
                iddlecounter.start();
            }
        });
    }
}

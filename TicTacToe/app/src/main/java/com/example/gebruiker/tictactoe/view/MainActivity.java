package com.example.gebruiker.tictactoe.view;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gebruiker.tictactoe.BounceInterpolator;
import com.example.gebruiker.tictactoe.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animateButton();

        // Knop die naar de prestartgame pagina gaat
        Button buttonStartGame = (Button) findViewById(R.id.buttonStartGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "onClick: redirect -> prestartgame activity");
                Intent intent = new Intent(MainActivity.this, PregameActivity.class);
                startActivity(intent);
            }
        });

        // Knop die naar de highscores pagina gaat
        Button buttonHighscores = (Button) findViewById(R.id.buttonHighscores);
        buttonHighscores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "onClick: redirect -> highscores activity");
                Intent intent = new Intent(getBaseContext(), HighscoreActivity.class);
                startActivity(intent);
            }
        });

        // Knop die naar de over ons pagina gaat
        Button buttonAboutUs = (Button) findViewById(R.id.buttonAboutUs);
        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "onClick: redirect -> aboutus activity");
                Intent intent = new Intent(MainActivity.this, AboutusActivity.class);
                startActivity(intent);
            }
        });

        // Knop om applicatie af te sluiten
        Button buttonExitApplication = (Button) findViewById(R.id.closeApplication);
        buttonExitApplication.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Sluit de app af
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

    }

    void animateButton() {
        // Load the animation
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        double animationDuration = 2 * 1000;
        myAnim.setDuration((long)animationDuration);

        // Use custom animation interpolator to achieve the bounce effect
        BounceInterpolator interpolator = new BounceInterpolator(0.2, 15);

        myAnim.setInterpolator(interpolator);

        // Animate the button
        Button button = (Button)findViewById(R.id.buttonStartGame);
        button.startAnimation(myAnim);

        // Run button animation again after it finished
        myAnim.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {}

            @Override
            public void onAnimationRepeat(Animation arg0) {}

            @Override
            public void onAnimationEnd(Animation arg0) {
                animateButton();
            }
        });
    }
}

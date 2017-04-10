package com.example.gebruiker.tictactoe.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.gebruiker.tictactoe.HighscoreAdapter;
import com.example.gebruiker.tictactoe.model.PlayerDBHandler;
import com.example.gebruiker.tictactoe.model.Player;
import com.example.gebruiker.tictactoe.R;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class HighscoreActivity extends AppCompatActivity {

    private static final String TAG = "HighscoreActivity";

    private ListView mHighscoreListView;
    private HighscoreAdapter mHighscoreAdapter;

    private ArrayList<Player> highscoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        // Haal lijst met 10 beste spelers op
        PlayerDBHandler pdb = new PlayerDBHandler(getApplicationContext());
        highscoreList = pdb.getHighscoreList();
        Log.i(TAG, "highscoreList: " + highscoreList );

        // Lijst met 10 spelers in listview weergeven
        mHighscoreListView = (ListView) findViewById(R.id.highscoreListItem);
        mHighscoreAdapter = new HighscoreAdapter(this, getLayoutInflater(), highscoreList);
        mHighscoreListView.setAdapter(mHighscoreAdapter);
        mHighscoreAdapter.notifyDataSetChanged();

        // Knop op highscores te resetten
        Button buttonResetHighscores = (Button) findViewById(R.id.resetHighscores);
        buttonResetHighscores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "onClick: reset highscores");

                // Reset alle highscores
                PlayerDBHandler pdb = new PlayerDBHandler(getApplicationContext());
                pdb.resetHighscores();

                // Herstart de activity
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }
}

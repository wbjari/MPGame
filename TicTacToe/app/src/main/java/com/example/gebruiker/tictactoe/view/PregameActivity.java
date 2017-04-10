package com.example.gebruiker.tictactoe.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gebruiker.tictactoe.model.PlayerDBHandler;
import com.example.gebruiker.tictactoe.R;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class PregameActivity extends AppCompatActivity {

    private static final String TAG = "PregameActivity";

    private String playerName;
    private String startFigure;

    private Button buttonXPregame;
    private Button buttonOPregame;
    private Button buttonRandomPregame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregame);

        // Input voor spelernaam
        final EditText inputPlayerName = (EditText) findViewById(R.id.playerName);

        // Standaard beginspeler
        startFigure = "O";

        Button buttonStartGame = (Button) findViewById(R.id.startGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Controle op errors
                boolean error = false;

                // Naam van speler ophalen uit input
                playerName = inputPlayerName.getText().toString().toUpperCase();

                // Minimaal lengte naam = 4 anders error melding
                if (playerName.length() < 4) {
                    TextView errorMsg = (TextView) findViewById(R.id.errorMsg);
                    errorMsg.setText("Naam moet uit minimaal 4 karakters bestaan.");
                    error = true;
                    Log.i(TAG, "name length: min 4");
                }

                // Controleer op dubbele namen
                // Speler database
                PlayerDBHandler pdb = new PlayerDBHandler(getApplicationContext());

                // true = dubbele naam geef melding
                if (pdb.checkDuplicatePlayerName(playerName)) {
                    TextView errorMsg = (TextView) findViewById(R.id.errorMsg);
                    errorMsg.setText("Deze naam bestaat al.");
                    error = true;
                    Log.i(TAG, "duplicate name: true");
                } else {
                    // error blijft false
                    Log.i(TAG, "duplicate name: false");
                }

                // Als er geen errors zijn gevonden start het spel
                if (error == false) {
                    // Nieuw scherm openen en data meegeven
                    Intent intent = new Intent(v.getContext(), GameActivity.class);
                    intent.putExtra("playerName", playerName);
                    intent.putExtra("startFigure", startFigure);
                    startActivity(intent);
                }
            }
        });
    }

}
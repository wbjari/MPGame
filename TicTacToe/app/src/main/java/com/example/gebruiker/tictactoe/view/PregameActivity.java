package com.example.gebruiker.tictactoe.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gebruiker.tictactoe.model.Player;
import com.example.gebruiker.tictactoe.model.PlayerDBHandler;
import com.example.gebruiker.tictactoe.R;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class PregameActivity extends AppCompatActivity {

    private static final String TAG = "PregameActivity";

    private String playerName;
    private String startFigure;

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
            public void onClick(final View v) {
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

                System.out.println("Score: " + pdb.getPlayerScore("hallo") );

                // true = dubbele naam geef melding
                if (pdb.checkDuplicatePlayerName(playerName)) {
                    TextView errorMsg = (TextView) findViewById(R.id.errorMsg);
                    errorMsg.setText("Deze naam bestaat al.");
                    error = true;

                    String message ="Deze gebruikersnaam bestaat al.";
                    AlertDialog show = new AlertDialog.Builder(PregameActivity.this)
                            .setTitle("Waarschuwing!")
                            .setMessage(message)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Overschrijven", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(v.getContext(), GameActivity.class);
                                    intent.putExtra("playerName", playerName);
                                    intent.putExtra("startFigure", startFigure);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Terug", null).show();

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
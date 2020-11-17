package com.example.colorgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button solo;
    private Button multiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button solo = (Button) findViewById(R.id.soloGameBtn);
        Button multiplayer = (Button) findViewById(R.id.multiplayerGameBtn);

        solo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openSoloActivity();
            }
        });

        multiplayer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openMultiplayerActivity();
            }
        });
    }
    public void openSoloActivity(){
        Intent intent = new Intent(this, SoloActivity.class);
        startActivity(intent);
    }
    public void openMultiplayerActivity(){
        Intent intent = new Intent( this, MultiplayerActivity.class);
        startActivity(intent);
    }
}
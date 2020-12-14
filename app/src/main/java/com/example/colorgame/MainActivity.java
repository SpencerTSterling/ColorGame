package com.example.colorgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // declaring x and y
    float x1, x2, y1, y2;

    private Button solo;
    private Button multiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting the custom toolbar as the actionbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    // onTouch event: swipe left to open Solo Activity &
    //                swipe right to open Multiplayer Activity
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                // swipe left
                if (x1 < x2){
                    openSoloActivity();
                }
                // swipe right
                else if (x1 > x2){
                    openMultiplayerActivity();
                }
                break;
        }
        return false;
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
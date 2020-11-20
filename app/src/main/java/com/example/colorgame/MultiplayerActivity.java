package com.example.colorgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MultiplayerActivity extends AppCompatActivity {

    // players
    private final String playerOne = "Player 1";
    private final String playerTwo = "Player 2";
    public TextView playerOnePoints;
    public TextView playerTwoPoints;
    public int playerOneScore = 0;
    public int playerTwoScore = 0;

    // game turns
    private String currentTurn;
    private TextView playerTurn;

    // game buttons
    private Button TopLeftBtn;
    private Button TopRightBtn;
    private Button BottomLeftBtn;
    private Button BottomRightBtn;

    private Button NewGameBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        // setting the custom toolbar as the actionbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // adding a home button to the tool bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // assigning the game buttons
        TopLeftBtn = findViewById(R.id.button1);
        TopRightBtn = findViewById(R.id.button2);
        BottomLeftBtn = findViewById(R.id.button3);
        BottomRightBtn = findViewById(R.id.button4);

        Button[] buttons = {TopLeftBtn,TopRightBtn,
                BottomLeftBtn,BottomRightBtn};


        // assigning player points
        playerOnePoints = findViewById(R.id.playerOneTxt);
        playerTwoPoints = findViewById(R.id.playerTwoTxt);

        // assigning the new game button
        NewGameBtn = findViewById(R.id.newGameBtn);

       // set up board - color the buttons and set the text
        setUpBoard(buttons);

        // set the current player to player one to start
        currentTurn = playerOne;

    }

    public void onClick(View view) {
        // game buttons
        Button[] buttons = {TopLeftBtn,TopRightBtn,
                BottomLeftBtn,BottomRightBtn};

        // when a button the player clicked
        Button clickedButton = buttonClicked(view, buttons);

        if ( clickedButton != null ){
            // if they clicked the right button...
            if  ( clickedButton.getText().equals("correct") ){
                Toast.makeText(getApplicationContext(), "you clicked the right one",Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(getApplicationContext(), "you clicked the wrong one",Toast.LENGTH_SHORT).show();
            }
            // check for winner

            // swap turns
        }

    }

    private void UpdateScore(TextView text, int score){
        score = score + 10;
        text.setText(score);
    }

    private void setUpBoard(Button[] buttons){
        // assigning the playerTurn and setting the text
        playerTurn = findViewById(R.id.playerTurnTxt);
        currentTurn = playerOne; // player 1 has the first turn
        playerTurn.setText(currentTurn);

        // choose a random button to be "correct"
        generateRandomButton(buttons);

        // color the buttons
        // the "correct" button will be colored slightly different
        generateButtonColor(buttons);
    }

    // buttonClicked method : returns button that was clicked
    private Button buttonClicked(View v, Button[] buttons){
        for ( Button button : buttons ){
            if ( v == button ){
                return button;
            }
        }
        return null;
    }

    // generateButtonColor colors all the buttons with a random color
    private void generateButtonColor(Button[] buttons){
        Random random = new Random();
        int min = 0;
        int max = 235;

        int R = random.nextInt(max-min) + min;
        int G = random.nextInt(max-min) + min;
        int B = random.nextInt(max-min) + min;

        int color = Color.argb(255, R, G, B);
        int correctColor = Color.argb(255, R + 50, G + 50, B + 50);

        for (Button b : buttons){
            // set all buttons to the same color
            b.setBackgroundColor(color);
            // color the correct button and its text to the different color
            if (b.getText().equals("correct")){
                b.setBackgroundColor(correctColor);
                b.setTextColor(correctColor);
            }
        }

    }

    // generateRandomButton marks one button as "correct"
    private void generateRandomButton(Button[] buttons){
        Random random = new Random();
        int min = 1;
        int max = 4;

        int randomButton = random.nextInt(max-min) + min;

        buttons[randomButton].setText("correct");
    }

}
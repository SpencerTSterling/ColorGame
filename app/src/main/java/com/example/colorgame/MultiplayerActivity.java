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
    public TextView playerTurn;

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

        // assigning the currentTurn to playerOne
        currentTurn = playerOne;

        playerTurn = findViewById(R.id.playerTurnTxt);
        playerTurn.setText(playerOne);
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
                // get points to current player
                UpdateScore();
            } else{
                Toast.makeText(getApplicationContext(), "you clicked the wrong one",Toast.LENGTH_SHORT).show();
            }
            // check for winner
            if ( WinDetected() ){
                playerTurn.setText(currentTurn + " won!");
                DisableButtons(buttons);
            }
            else {
                // next turn
                swapPlayers();
                nextTurn(buttons);
            }


        }

    }

    public void newGame(View view) {
        // game buttons
        Button[] buttons = {TopLeftBtn,TopRightBtn,
                BottomLeftBtn,BottomRightBtn};

        // enable the buttons
        EnableButtons(buttons);
        // clear the board
        clearBoard(buttons);
        // set up the board
        setUpBoard(buttons);


        // set current turn back to playerOne
        if(currentTurn == playerTwo){
            swapPlayers();
        }

        // reset the text views
        ResetText();
    }

    private void ResetText(){
        playerTurn.setText(currentTurn);
        playerOneScore = 0;
        playerTwoScore = 0;
        playerOnePoints.setText(Integer.toString(playerOneScore));
        playerTwoPoints.setText(Integer.toString(playerTwoScore));
    }


    // update the score of the current turn player
    private void UpdateScore(){
        if (currentTurn == playerOne){
            playerOneScore = playerOneScore + 10;
            playerOnePoints.setText(Integer.toString(playerOneScore));
        }
        else if (currentTurn == playerTwo){
            playerTwoScore = playerTwoScore + 10;
            playerTwoPoints.setText(Integer.toString(playerTwoScore));
        }
    }


    // picks a random button to be correct &
    // colors all the buttons
    private void setUpBoard(Button[] buttons){
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

    // swap player turns
    private void swapPlayers(){
        if (currentTurn == playerOne){
            currentTurn = playerTwo;
        }
        else if (currentTurn == playerTwo){
            currentTurn = playerOne;
        }

        playerTurn.setText(currentTurn);
    }

    // set all buttons to same color / no text
    private void clearBoard(Button[] buttons){
        for (Button b : buttons){
            b.setBackgroundColor(0);
            b.setText("");
        }
    }

    // set up the board & swap players
    private void nextTurn(Button[] buttons){
        clearBoard(buttons);
        setUpBoard(buttons);
    }

    private boolean WinDetected(){
        if (playerOneScore == 100 || playerTwoScore == 100){
            playerTurn.setText(playerOne + "won!");
            return true;
        }
        else{
            return false;
        }
    }

    private void DisableButtons(Button[] buttons){
        for (Button b: buttons){
            b.setClickable(false);
        }
    }

    private void EnableButtons(Button[] buttons){
        for(Button b: buttons){
            b.setClickable(true);
        }
    }


}
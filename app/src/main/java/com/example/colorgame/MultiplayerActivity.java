package com.example.colorgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        // setting the custom toolbar as the actionbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // adding a home button to the tool bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) throw new AssertionError();
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

       // set up board - color the buttons and set the text
        Game.setUpBoard(buttons);

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
        Button clickedButton = Game.buttonClicked(view, buttons);

        if ( clickedButton != null ){
            // if the player clicks the correct button
            if  ( Game.correctButton(clickedButton) ){
                // get points to current player
                if (currentTurn.equals(playerOne)){
                    playerOneScore = Game.updateScore(playerOneScore, playerOnePoints);
                }
                if (currentTurn.equals(playerTwo)){
                    playerTwoScore = Game.updateScore(playerTwoScore, playerTwoPoints);
                }
            }

            // check for winner
            if ( Multiplayer.winDetected(playerOneScore, playerTwoScore) ){
                playerTurn.setText(String.format("%s won!", currentTurn));
                Game.disableButtons(buttons);
            }
            else {
                // swap players and next turn
                currentTurn = Multiplayer.swapPlayers(currentTurn, playerTurn);
                Game.nextTurn(buttons);
            }

        }

    }

    public void newGame(View view) {
        // game buttons
        Button[] buttons = {TopLeftBtn,TopRightBtn,
                BottomLeftBtn,BottomRightBtn};
        // enable the buttons
        Game.enableButtons(buttons);
        // clear the board
        Game.clearBoard(buttons);
        // set up the board
        Game.setUpBoard(buttons);
        // set current turn back to playerOne
        if(currentTurn.equals(playerTwo)){
            Multiplayer.swapPlayers(currentTurn, playerTurn);
        }
        // reset scores
        playerOneScore = 0;
        playerTwoScore = 0;
        // reset the text views
        Multiplayer.resetText(playerTurn, playerOnePoints, playerTwoPoints);
    }

}
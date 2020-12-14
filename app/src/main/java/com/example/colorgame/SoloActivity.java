package com.example.colorgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SoloActivity extends AppCompatActivity {

    // player stats
    public TextView playerPoints;
    public TextView playerLife;
    public int playerScore = 0;
    public int playerLives = 3;

    // game buttons
    private Button TopLeftBtn;
    private Button TopRightBtn;
    private Button BottomLeftBtn;
    private Button BottomRightBtn;

    private Button NewGameBtn;

    // win/lost declaration
    public TextView winDeclaration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo);

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

        // assigning player points + lives
        playerPoints = findViewById(R.id.pointsTxt);
        playerLife = findViewById(R.id.livesTxt);

        // assigning the new game button
        NewGameBtn = findViewById(R.id.newGameBtn);

        // assigning the win declaration text
        winDeclaration = findViewById(R.id.declareWinTxt);

        // set up board - color the buttons and set the text
        setUpBoard(buttons);

    }

    public void onClick(View view) {
        // game buttons
        Button[] buttons = {TopLeftBtn,TopRightBtn,
                BottomLeftBtn,BottomRightBtn};

        // when a button the player clicked
        Button clickedButton = buttonClicked(view, buttons);

        if (clickedButton != null){

            if ( clickedButton.getText().equals("correct") ){
                //Toast.makeText(getApplicationContext(), "you clicked the right one",Toast.LENGTH_SHORT).show();
                // get points
                UpdateScore();
            }
            else {
                //Toast.makeText(getApplicationContext(), "you clicked the wrong one",Toast.LENGTH_SHORT).show();
                // lose life
                UpdateLives();
            }

            // check for win/lose
            if (WinDetected()){
                winDeclaration.setText("You win!");
                DisableButtons(buttons);
            }
            else if (LoseDetected()){
                winDeclaration.setText("You lose!");
                DisableButtons(buttons);
            }
            else{
                // if no win/lost go to next turn
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

        // reset text fields and score/lives
        ResetText();
    }

    // update the players score
    private void UpdateScore(){
        playerScore = playerScore + 10;
        playerPoints.setText(Integer.toString(playerScore));
    }

    // update the players lives
    private void UpdateLives(){
        playerLives = playerLives - 1;
        playerLife.setText(Integer.toString(playerLives));
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

    // clear the board and set it up again
    private void nextTurn(Button[] buttons){
        clearBoard(buttons);
        setUpBoard(buttons);
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

    // set all buttons to same color / no text
    private void clearBoard(Button[] buttons){
        for (Button b : buttons){
            b.setBackgroundColor(0);
            b.setText("");
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

    // returns true if player has 100 points
    private boolean WinDetected(){
        if(playerScore == 100){
            return true;
        }
        else {
            return false;
        }
    }

    // returns true if player has 0 lives
    private boolean LoseDetected(){
        if (playerLives == 0){
            return true;
        }
        else{
            return false;
        }
    }

    // reset text feilds
    private void ResetText(){
        playerScore = 0;
        playerLives = 3;
        playerPoints.setText(Integer.toString(playerScore));
        playerLife.setText(Integer.toString(playerLives));
        winDeclaration.setText(null);
    }

    // disable game buttons
    private void DisableButtons(Button[] buttons){
        for (Button b: buttons){
            b.setClickable(false);
        }
    }

    // enable game buttons
    private void EnableButtons(Button[] buttons){
        for(Button b: buttons){
            b.setClickable(true);
        }
    }


}
package com.example.colorgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SoloActivity extends AppCompatActivity {


    // declaring x and y
    float x1, x2, y1, y2;


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

    // win/lost declaration
    public TextView winDeclaration;
    public String winMessage = "You win!";
    public String loseMessage = "You lose!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo);

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

        // assigning player points + lives
        playerPoints = findViewById(R.id.pointsTxt);
        playerLife = findViewById(R.id.livesTxt);

        // assigning the win declaration text
        winDeclaration = findViewById(R.id.declareWinTxt);

        // set up board - color the buttons and set the text
        Game.setUpBoard(buttons);

    }

    public void onClick(View view) {
        // game buttons
        Button[] buttons = {TopLeftBtn,TopRightBtn,
                BottomLeftBtn,BottomRightBtn};

        // when a button the player clicked
        Button clickedButton = Game.buttonClicked(view, buttons);

        if (clickedButton != null){

            if ( Game.correctButton(clickedButton) ){
                // get points
                playerScore = Game.updateScore(playerScore, playerPoints);
            }
            else {
                // lose a life
                playerLives = Solo.updateLives(playerLives, playerLife);
            }

            // check for win/lose
            if (Solo.winDetected(playerScore)){
                winDeclaration.setText(winMessage);
                Game.disableButtons(buttons);
            }
            else if (Solo.loseDetected(playerLives)){
                winDeclaration.setText(loseMessage);
                Game.disableButtons(buttons);
            }
            else{
                // if no win/lost go to next turn
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

        // reset points/lives
        playerLives = 3;
        playerScore = 0;
        // reset text fields
        Solo.resetText(winDeclaration, playerPoints, playerLife);
    }

    // onTouch event: swipe right to open Main Activity
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                // swipe right
                if (x1 > x2){
                    openMainActivity();
                }
                break;
        }
        return false;
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}


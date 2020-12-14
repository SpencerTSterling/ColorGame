package com.example.colorgame;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * This Game class contains methods used in the ColorGame application
 * between Solo and Multiplayer mode
 * @author Spencer Sterling
 * @version 1.0
 */

public class Game {

    public static int POINT_GOAL = 100;

    // Core Game Mechanics
    public static String CORRECT_BUTTON = "correct";

    /**
     * Gives points to the current player and displays the new score,
     * then returns the updated score
     * @param score Current player's score
     * @param scoreDisplay TextView for current player's score
     */
    public static int updateScore(int score, TextView scoreDisplay){
        score = score + 10;
        displayScore(score, scoreDisplay);
        return score;
    }

    /**
     * Helper method for updateScore that displays the score updated
     * @param score Current player's score
     * @param scoreDisplay TextView for current player's score
     */
    public static void displayScore(int score, TextView scoreDisplay){
        String text = Integer.toString(score);
        scoreDisplay.setText(text);
    }

    /**
     * Returns the button that the user clicked
     * @param v View
     * @param buttons Game buttons
     * @return the button clicked
     */
    public static Button buttonClicked(View v, Button[] buttons){
        for ( Button button : buttons ){
            if ( v == button ){
                return button;
            }
        }
        return null;
    }

    /**
     * Returns true if the player clicked the correct button
     * @param button The button the player clicked
     * @return true if player clicked the correct button, false if not
     */
    public static boolean correctButton(Button button){
        return button.getText().equals(CORRECT_BUTTON);
    }

    /**
     * Marks one button as "correct"
     * @param buttons The game buttons
     */
    public static void generateRandomButton(Button[] buttons){
        Random random = new Random();
        int min = 0;
        int max = 4;

        int randomButton = random.nextInt(max-min) + min;

        buttons[randomButton].setText(CORRECT_BUTTON);
    }

    /**
     * Colors all the buttons passed in with a random color
     * and gives a slightly different color to the correct button
     * @param buttons The game buttons
     */
    public static void generateButtonColor(Button[] buttons){
        Random random = new Random();
        int min = 0;
        int max = 225;

        int R = random.nextInt(max-min) + min;
        int G = random.nextInt(max-min) + min;
        int B = random.nextInt(max-min) + min;

        int color = Color.argb(255, R, G, B);
        int correctColor = Color.argb(255, R + 30, G + 30, B + 30);

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

    /**
     * Picks a random button to be correct &
     * colors all the buttons accordingly
     * @param buttons The game buttons
     */
    public static void setUpBoard(Button[] buttons){
        // choose a random button to be "correct"
        generateRandomButton(buttons);
        // color the button - the "correct" button will be colored slightly different
        generateButtonColor(buttons);
    }

    /**
     * Sets all buttons pass in with the same color
     * and no button text
     * @param buttons The game buttons
     */
    public static void clearBoard(Button[] buttons){
        for (Button b : buttons){
            b.setBackgroundColor(0);
            b.setText(null);
        }
    }

    /**
     * Clear the game board and set it up for the next turn
     * @param buttons The game buttons
     */
    public static void nextTurn(Button[] buttons){
        clearBoard(buttons);
        setUpBoard(buttons);
    }

    /**
     * Disable game buttons so that they aren't clickable
     * @param buttons The game buttons
     */
    public static void disableButtons(Button[] buttons){
        for (Button b: buttons){
            b.setClickable(false);
        }
    }

    /**
     * Enables game buttons so that they are clickable
     * @param buttons The game buttons
     */
    public static void enableButtons(Button[] buttons){
        for(Button b: buttons){
            b.setClickable(true);
        }
    }

}

/**
 * Methods for Multiplayer mode
 */
class Multiplayer extends Game {
    // for Multiplayer mode
    public static String PLAYER_ONE = "Player 1";
    public static String PLAYER_TWO = "Player 2";

    /**
     * Takes in the current turn and the two players, and then swaps the turns
     * and displays who's turn it is
     * @param currentTurn Whose turn it is
     * @param playerTurn TextView displaying the player turn
     */
    public static String swapPlayers(String currentTurn, TextView playerTurn){
        if (currentTurn.equals(PLAYER_ONE)){
            currentTurn = PLAYER_TWO;
        }
        else if (currentTurn.equals(PLAYER_TWO)){
            currentTurn = PLAYER_ONE;
        }
        playerTurn.setText(currentTurn);
        return currentTurn;
    }

    /**
     * Resets all the text fields to their original states
     * @param playerTurn TextView displaying the current player's turn
     * @param playerOnePoints TextView displaying Player 1's points
     * @param playerTwoPoints TextView displaying Player 2's points
     */
    public static void resetText(TextView playerTurn, TextView playerOnePoints, TextView playerTwoPoints){
        playerTurn.setText(PLAYER_ONE);
        displayScore(0, playerOnePoints);
        displayScore(0, playerTwoPoints);
    }

    /**
     * Returns true if either players have a score of 100
     * @param playerOneScore Player 1's score
     * @param playerTwoScore Player 2's score
     * @return true if either players have a score of 100
     */
    public static boolean winDetected(int playerOneScore, int playerTwoScore){
        return playerOneScore == POINT_GOAL || playerTwoScore == POINT_GOAL;
    }

}

/**
 * Methods for Solo mode
 */
class Solo extends Game {
    public static int OUT_OF_LIVES = 0;

    /**
     * Reset all text fields to their original states
     * @param winDeclaration TextView for win/lost declaration
     * @param playerPoints TextView for player's points
     * @param playerLife TextView for player's lives
     */
    public static void resetText(TextView winDeclaration, TextView playerPoints, TextView playerLife){
        winDeclaration.setText(null);
        displayScore(0,playerPoints);
        displayLives(3, playerLife);
    }

    /**
     * Updates the amount of lives a player has
     * @param lives Amount of lives the player has
     * @param playerLife TextView displaying the player's lives
     * @return Amount of lives the player has
     */
    public static int updateLives(int lives, TextView playerLife){
        lives = lives - 1;
        displayLives(lives, playerLife);
        return lives;
    }

    /**
     * Helper method for updateLives that displays the updated amount
     * of lives a player has
     * @param lives Amount of lives the player has
     * @param livesDisplay TextView of the player's lives
     */
    public static void displayLives(int lives, TextView livesDisplay){
        String text = Integer.toString(lives);
        livesDisplay.setText(text);
    }

    /**
     * Returns true if the player's score is 100
     * @param score The player's score
     * @return true if the player's score is 100
     */
    public static boolean winDetected(int score){
        return score == POINT_GOAL;
    }

    /**
     * Returns true if the player's lives reach 0
     * @param lives Amount of lives the player has
     * @return true if the player is out of lives
     */
    public static boolean loseDetected(int lives){
        return lives == OUT_OF_LIVES;
    }
}
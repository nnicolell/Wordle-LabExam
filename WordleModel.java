/**
 * Nicole Lim
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WordleModel {
    public static final int SIZE = 5;
    public enum Status {WIN, LOSE,UNDECIDED};

    private char[][] grid;
    private boolean won;
    private String wordToGuess;
    private String guessedWord;
    private char[] guessed;
    private char[] winningWord;
    private List<WordleView> views;

    // booleans for if the letters should be green
    private boolean[] greenBoxes;

    // booleans for if the letters should be yellow
    private boolean[] yellowBoxes;
    int numTurns;
    private Status status;

    /**
     * constructor for model
     * @param word word that is the winning word
     */
    public WordleModel(String word){
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j =0; j < SIZE; j++){
                grid[i][j] = ' ';
            }
        }
        status = Status.UNDECIDED;
        wordToGuess = word.toLowerCase();
        winningWord = wordToGuess.toCharArray();
        greenBoxes = new boolean[SIZE];
        yellowBoxes = new boolean[SIZE];
        views = new ArrayList<>();
        numTurns = 5;

    }

    /**
     * Getter to get whether the player has won
     * @return true if the player has guessed the word, false otherwise
     */
    public boolean getWon(){
        return won;
    }

    public void setGreenBoxes() {
        for (int i = 0; i <5; i++){
            greenBoxes[i] = false;
        }
    }

    public void setYellowBoxes() {
        for (int i = 0; i <5; i++){
            yellowBoxes[i] = false;
        }
    }

    /**
     * added view
     * @param v view that is to be added
     */
    public void addWordleView(WordleView v) {
        views.add(v);
    }

    /**
     * returns the boolean array of which boxes should be green
     * @return the array of the green boxes
     */
    public boolean[] getGreenBoxes() {
        return greenBoxes;
    }

    /**
     * returns the boolean array of which boxes should be yellow
     * @return the array of the yellow boxes
     */
    public boolean[] getYellowBoxes() {
        return yellowBoxes;
    }

    /**
     * returns the status of the game
     * @return status of the game
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Getter for the word that is to be guessed
     * @return word that needs to be guessed
     */
    public String getWordToGuess(){
        return wordToGuess;
    }

    /**
     * sets the guessed word
     * @param s guessed word
     */
    public void setGuessedWord(String s){
        guessedWord = s;
    }
    /**
     * returns the guessed word
     * @return the guessed word
     */
    public String getGuessedWord(){
        return guessedWord;
    }

    /**
     * updates the status of the game
     */
    private void updateStatus(){
        int num = 0;
        for (int i = 0; i < greenBoxes.length; i++) {
            if (greenBoxes[i]) {
                num++;
            }
        }
        if (num == 5){
            status = Status.WIN;
        }

    }

    /**
     * plays the game
     * @param gword the word that is guessed.
     */
    public void play(String gword){
        setYellowBoxes();
        setGreenBoxes();
        gword = gword.toLowerCase();

        if (gword.length() > SIZE || gword.length() < 5){
            System.out.println("Error, please input a 5 letter word");
            return;
        }
        guessed = gword.toCharArray();


        for (int i = 0; i < SIZE; i++){
            if ( winningWord[i] == guessed[i]){
                greenBoxes[i] = true;
            }
            else if(wordToGuess.contains(Character.toString(guessed[i]))){
                yellowBoxes[i] = true;
            }
            updateStatus();
        }
        numTurns--;

        for (WordleView v : views){v.update(status);}
        if (numTurns == 0){
            status = Status.LOSE;
        }

    }

}

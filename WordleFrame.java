/**
 * Nicole Lim
 */

import javax.swing.*;
import java.awt.*;

public class WordleFrame extends JFrame implements WordleView{

    private String wordToGuess;
    private WordleModel model;

    private JPanel gridPanel;
    private JPanel inputPanel;
    private JButton submitBtn;
    private JButton[][] grid;

    private JTextField tx;
    int num;
    private WordleController wc;

    /**
     * constructor for the frame
     */
    public WordleFrame() {
        super("Wordle");
        wordToGuess = JOptionPane.showInputDialog("Please enter the 5-character word to be guessed");
        model = new WordleModel(wordToGuess);
        model.addWordleView(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BorderLayout());

        wc = new WordleController(model, this);

        gridPanel = new JPanel();
        gridPanel.setSize(400,400);
        gridPanel.setLayout(new GridLayout(5, 5));
        grid = new JButton[WordleModel.SIZE][WordleModel.SIZE];
        wordButtons();

        tx = new JTextField();
        tx.setPreferredSize(new Dimension(200,100));

        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(wc);
        submitBtn.setActionCommand("");
        inputPanel = new JPanel();
        inputPanel.setSize(400,100);
        inputPanel.add(tx);
        inputPanel.add(submitBtn);


        this.add(gridPanel, BorderLayout.CENTER);
        this.add(inputPanel,BorderLayout.SOUTH);

        this.setSize(400,500);
        this.setVisible(true);


    }

    /**
     * Creates a grid of buttons
     */
    private void wordButtons(){
        for (int i = 0; i < WordleModel.SIZE; i++){
            for (int j = 0; j < WordleModel.SIZE; j++){
                JButton b = new JButton("");
                grid[i][j]=b;
                gridPanel.add(b);
            }
        }

    }

    /**
     * starts the game
     * @param args arguments
     */
    public static void main(String[] args) {
        new WordleFrame();
    }

    /**
     * gets the input word
     * @return input word
     */
    public String getTxWord(){
        return tx.getText();
    }

    /**
     * gets the input word
     * @return input word
     */
    public void setTxWord(String s){
        tx.setText(s);
    }

    /**
     * returns the grid of jbuttons
     * @return grid of jbuttons
     */
    public JButton[][] getGrid() {
        return grid;
    }

    /**
     * override method for update, updates the status of the game
     * @param status status of the game
     */
    @Override
    public void update(WordleModel.Status status) {
        if (model.getStatus() == WordleModel.Status.WIN){
            JOptionPane.showMessageDialog(this,"You won!");
        }
        else if(model.getStatus() == WordleModel.Status.LOSE){
            JOptionPane.showMessageDialog(this,"You lost :(");
        }
    }
}

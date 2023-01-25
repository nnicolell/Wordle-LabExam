/**
 * Nicole Lim
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordleController implements ActionListener {

    private WordleModel model;
    private WordleFrame frame;
    private int num = 0;

    /**
     * Constructor for the controller class
     * @param m Wordle model
     * @param f Wordle Frame
     */
    public WordleController(WordleModel m, WordleFrame f){
        model = m;
        frame = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();

        if (b.getText().equals("Submit")){
            model.play(frame.getTxWord());

            for (int i = 0; i < 5; i ++){
                frame.getGrid()[num][i].setText(String.valueOf(frame.getTxWord().toCharArray()[i]));
                System.out.println("Green " + model.getGreenBoxes()[i]);
                System.out.println("Yellow " + model.getYellowBoxes()[i]);
                if(model.getGreenBoxes()[i]){
                    frame.getGrid()[num][i].setBorderPainted(false);
                    frame.getGrid()[num][i].setOpaque(true);
                    frame.getGrid()[num][i].setBackground(Color.GREEN);
                }
                else if(model.getYellowBoxes()[i]){
                    frame.getGrid()[num][i].setBorderPainted(false);
                    frame.getGrid()[num][i].setOpaque(true);
                    frame.getGrid()[num][i].setBackground(Color.YELLOW);
                }
                else{
                    frame.getGrid()[num][i].setBorderPainted(false);
                    frame.getGrid()[num][i].setOpaque(true);
                    frame.getGrid()[num][i].setBackground(Color.WHITE);
                }
            }

            frame.setTxWord("");

        }
        num++;

    }
}

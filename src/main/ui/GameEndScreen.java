package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//The frame shown when the game is over
public class GameEndScreen extends JFrame implements ActionListener {

    private static final int GES_WIDTH = Main.SCREEN_SIZE.width;
    private static final int GES_HEIGHT = Main.SCREEN_SIZE.height;

    //EFFECTS: sets up screen when the game is over
    // help from https://stackoverflow.com/questions/17110315/jbutton-background-image
    public GameEndScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(GES_WIDTH, GES_HEIGHT));
        setUndecorated(true);
        setVisible(true);
        ImageIcon thanksImage = new ImageIcon("./data/thanks_for_playing.png");
        JButton popUpBtn = new JButton("", thanksImage);
        popUpBtn.setBackground(new Color(180, 0, 10));
        popUpBtn.setPreferredSize(new Dimension(GES_WIDTH, GES_HEIGHT));
        add(popUpBtn);
        popUpBtn.addActionListener(this);
    }

    //EFFECTS: closes frame and game when button pressed
    // inspired by: https://stackoverflow.com/questions/17110315/jbutton-background-image (part that shows Override
    // and method signature)
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        System.exit(0);
    }
}

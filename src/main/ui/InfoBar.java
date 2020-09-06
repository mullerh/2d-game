package ui;

import javax.swing.*;
import java.awt.*;

// A panel that displays user stats
// help from: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git ScorePanel class
public class InfoBar extends JPanel {
    private static final int BAR_WIDTH = 250;
    private static final int BAR_HEIGHT = 300;

    private Game game;
    private JLabel userLabel;

    //EFFECTS: sets up info bar panel to display user stats
    //credit to: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git ScorePanel class' constructor
    public InfoBar(Game game) {
        this.game = game;
        setBackground(new Color(121, 180, 175));
        userLabel = new JLabel(game.getUser().toString());
        userLabel.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
        add(userLabel);
    }

    //MODIFIES: this
    //EFFECTS: refreshes the stats of the user that is displayed
    //credit to: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git ScorePanel class' update method
    public void update() {
        userLabel.setText(game.getUser().toString());
        repaint();
    }
}

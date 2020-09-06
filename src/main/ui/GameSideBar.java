package ui;

import javax.swing.*;
import java.awt.*;

//A panel that displays what numbers to press to equip weapons
//credit to: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git ScorePanel class
public class GameSideBar extends JPanel {
    private static final int BAR_WIDTH = 100;
    private static final int BAR_HEIGHT = 200;
    private static final String LABEL = "<html>Type number to equip:<br/>";
    private Game game;
    JLabel gameBarLbl;

    //EFFECTS: sets up a side bar that tells the user what to press to equip items
    //credit to: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git ScorePanel class' constructor
    public GameSideBar(Game game) {
        this.game = game;
        setBackground(new Color(121, 180, 175));
        gameBarLbl = new JLabel(LABEL + game.getUser().getInventory().getInventoryItemStrings());
        gameBarLbl.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
        add(gameBarLbl);
    }

    //MODIFIES: this
    //EFFECTS: refreshes the numbers the user must press to equip an item
    //credit to: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git ScorePanel class' update method
    public void update() {
        gameBarLbl.setText(LABEL + game.getUser().getInventory().getInventoryItemStrings());
        repaint();
    }
}

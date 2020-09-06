package ui;

import javax.swing.*;
import java.awt.*;

//Banner at top of screen that shows basic button guide
//credit to: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git ScorePanel class
public class GameBanner extends JPanel {

    private static final int BNR_WIDTH = 200;
    private static final int BNR_HEIGHT = 30;

    //EFFECTS: Sets up banner at top of screen that shows basic button guide
    //credit to: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git ScorePanel class' constructor
    public GameBanner() {
        setBackground(new Color(121, 180, 175));
        JLabel gameBannerLbl = new JLabel("S = SAVE             L = LOAD");
        gameBannerLbl.setPreferredSize(new Dimension(BNR_WIDTH, BNR_HEIGHT));
        add(gameBannerLbl);
    }
}

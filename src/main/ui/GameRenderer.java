package ui;

import model.Cell;
import model.Character;

import javax.swing.*;
import java.awt.*;

//A class that handels rendering game play parts of the game
// class helped by: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git GamePanel class
// AND https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git SnakeGameRenderer class
public class GameRenderer extends JPanel {
    private static final Color USER_COLOR = new Color(255, 203,0);
    private static final Color ITEM_COLOR = new Color(6, 0, 255);
    private static final Color ENEMY_COLOR_ALIVE = new Color(0, 255, 255);
    private static final Color ENEMY_COLOR_DEAD = new Color(255, 1,0);
    private static final Color BACKGROUND_COLOR = new Color(0, 143, 5);

    private Game game;

    //EFFECTS: sets up the game screen
    //credit to: //credit to: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git GamePanel class'
    // constructor method
    public GameRenderer(Game game) {
        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        this.game = game;
    }

    //MODIFIES: graphics
    //EFFECTS: sets up and paints graphics of panel
    //credit to: //credit to: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git GamePanel class'
    // paintComponent method
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        draw(graphics);
    }

    //MODIFIES: graphics
    //EFFECTS: draws the game onto graphics
    // credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git SnakeGameRenderer class'
    // draw method
    public void draw(Graphics graphics) {
        drawUser(graphics);
        drawEnemies(graphics);
        drawItems(graphics);
    }

    //MODIFIES: this
    //EFFETCS: draws the user onto graphics
    // credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git SnakeGameRenderer class'
    // drawBody method
    private void drawUser(Graphics graphics) {
        graphics.setColor(USER_COLOR);
        drawCell(graphics, game.getUser().getLocation());
    }

    //MODIFIES: this
    //EFFETCS: draws the enemies onto graphics
    // credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git SnakeGameRenderer class'
    // drawBody method
    private void drawEnemies(Graphics graphics) {
        graphics.setColor(ENEMY_COLOR_ALIVE);
        for (Character enemy : game.getEnemies()) {
            if (enemy.isDead()) {
                graphics.setColor(ENEMY_COLOR_DEAD);
            }
            drawCell(graphics, enemy.getLocation());
        }
    }

    //MODIFIES: this
    //EFFETCS: draws the items onto graphics
    // credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git SnakeGameRenderer class'
    // drawBody method
    private void drawItems(Graphics graphics) {
        graphics.setColor(ITEM_COLOR);
        for (Cell cell : game.getItems().getItemMap().keySet()) {
            drawCell(graphics, cell);
        }
    }

    // MODIFIES: graphics
    // EFFECTS:  draws cell onto graphics
    // credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git SnakeGameRenderer class'
    // drawCell method
    private void drawCell(Graphics graphics, Cell cell) {
        graphics.fillOval(cell.getScreenHorizontalCoord(), cell.getScreenVerticalCoord(),
                Cell.CELL_PIXELS, Cell.CELL_PIXELS);
    }
}

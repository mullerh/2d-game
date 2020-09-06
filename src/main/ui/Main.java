package ui;

import model.Cell;
import model.Direction;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

//Represents the Main frame that the game will be played on
//credit to both: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git SnakeApp class
// AND https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git SpaceInvader class
public class Main extends JFrame {
    // https://alvinalexander.com/index.php/blog/post/jfc-swing/java-swing-faq-how-determine-screen-size-resolution
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = Game.BOARD_COLS * Cell.CELL_PIXELS;
    public static final int HEIGHT = Game.BOARD_ROWS * Cell.CELL_PIXELS;
    private static final int INTERVAL = 15;

    private Game game;
    private GameRenderer renderer;
    private InfoBar ib;
    private GameSideBar gsb;
    private PopUp popUp;
    private GameEndScreen gameEndScreen;

    //EFFECTS: sets up main game window the RPG will be played in
    //MODIFIES: this
    //credit to both: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git SnakeApp class' constructor
    // AND https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git SpaceInvader class' constructor
    public Main() {
        super("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setUndecorated(true);
        game = new Game();
        renderer = new GameRenderer(game);
        ib = new InfoBar(game);
        gsb = new GameSideBar(game);
        add(renderer);
        add(new GameBanner(), BorderLayout.NORTH);
        add(ib, BorderLayout.WEST);
        add(gsb, BorderLayout.EAST);
        addKeyListener(new KeyHandler());
        pack();
        addTimer();
        setVisible(true);
    }

    // EFFECTS: initializes a timer that updates game each
    //          INTERVAL milliseconds
    //credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git SnakeApp class' addTimer method
    private void addTimer() {
        final Timer t = new Timer(INTERVAL, null);
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (game.isGameOver()) {
                    t.stop();
                } else {
                    renderer.repaint();
                    ib.update();
                    gsb.update();
                }
            }
        });

        t.start();
    }

    // credit to: https://github.students.cs.ubc.ca/cpsc210-2019w-t2/lab6_y3n2b.git SnakeApp class' KeyHandler class
    // Represents a key handler that responds to keyboard events
    private class KeyHandler extends KeyAdapter {
        // MODIFIES: this
        // EFFECTS:  updates game in response to a keyboard event
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_L:
                    game.loadGame();
                    break;
                case KeyEvent.VK_S:
                    game.saveGame();
                    break;
                default:
                    //numbers 1 through 10
                    if (e.getKeyCode() >= 48 && e.getKeyCode() <= 57) {
                        game.equipItemNumber(e);
                    //direction inputs (left right up down)
                    } else if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40) {
                        endOfGameScreen();
                        playSound("./data/Alesis-Fusion.wav");
                        game.moveCharacter(e);
                        showDialogue();
                    }
            }
        }
    }

    //EFFECTS: show dialogue after walking into enemy cell
    public void showDialogue() {
        if (game.canEnemyInteract()) {
            popUp = new PopUp(game);
        }
    }

    //MODIFIES: this
    //EFFECTS: closes game and shows end of game screen if enemies are dead
    public void endOfGameScreen() {
        if (game.getEnemies().get(0).isDead()) {
            dispose();
            gameEndScreen = new GameEndScreen();
        }
    }

    //EFFECTS: given the file location of a sound file, play said sound file
    //modified from: http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //sound credit to: https://freewavesamples.com/sample-type/bass
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        // print error and stack trace
        } catch (Exception e) {
            System.out.println("Error with sound");
            e.printStackTrace();
        }
    }

    //Plays the game
    public static void main(String[] args) {
        new Main();
    }
}
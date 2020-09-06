package ui;

import model.Character;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A popup dialogue that lets the user deal damage
public class PopUp extends JFrame implements ActionListener {
    private static final int POP_WIDTH = (Main.WIDTH / 10) * 2;
    private static final int POP_HEIGHT = Main.HEIGHT / 10;

    private Game game;

    //EFFECTS: sets up JButtons that lets the user pick attack types
    //help from: https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
    public PopUp(Game game) {
        this.game = game;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(POP_WIDTH, POP_HEIGHT));
        setUndecorated(true);
        setVisible(true);
        setBackground(new Color(180, 0, 10));
        JButton weakDamageButton = new JButton("Weak Damage");
        weakDamageButton.setActionCommand("1");
        weakDamageButton.setPreferredSize(new Dimension(POP_WIDTH / 2, POP_HEIGHT));
        JButton strongDamageButton = new JButton("Strong Damage");
        strongDamageButton.setPreferredSize(new Dimension(POP_WIDTH / 2, POP_HEIGHT));
        strongDamageButton.setActionCommand("2");
        add(strongDamageButton, BorderLayout.EAST);
        add(weakDamageButton, BorderLayout.WEST);
        strongDamageButton.addActionListener(this);
        weakDamageButton.addActionListener(this);
    }

    @Override
    //MODIFIES: this
    //EFFECTS: if enemy is dead deal weak attack damage and close
    //help from: https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
    public void actionPerformed(ActionEvent e) {
        Character enemy = game.getEnemies().get(0);
        if (!enemy.isDead()) {
            enemy.decreaseHealth(game.getUser().attackDamage(e.getActionCommand(), enemy.getCharacterType()));
        }
        dispose();
    }
}

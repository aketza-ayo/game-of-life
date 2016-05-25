package com.games.gol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ayoa on 18/05/16.
 */
public class Driver {

    public static void main(String[] args) {
        Board board = new Board(500,500);
        JFrame f = new JFrame("Game of life");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(board);
        f.pack();
        f.setVisible(true);
        new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                board.updateGeneration();
                board.repaint();  // this method calls paintComponent()
            }
        }).start();
    }

}

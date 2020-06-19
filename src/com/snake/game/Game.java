package com.snake.game;

import com.snake.board.Board;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public Game() {
        add(new Board());
        setResizable(false);
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ex = new Game();
                ex.setVisible(true);
            }
        });
    }
}

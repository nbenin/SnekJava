package com.snake.board;

import com.snake.direction.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Board extends JPanel implements ActionListener {

    // Board Constants
    private static final int BOARD_WIDTH = 300;
    private static final int BOARD_HEIGHT = 300;
    private static final int SQUARE_SIZE = 10;
    private static final int TOTAL_SQUARES = (BOARD_WIDTH * BOARD_HEIGHT) / (SQUARE_SIZE * SQUARE_SIZE);
    private static final int RANDOM_RANGE = 30;

    // Snake and Apple Variables
    private int snakeLength;
    private int apple_x;
    private int apple_y;
    private int[] snake_x = new int[TOTAL_SQUARES];
    private int[] snake_y = new int[TOTAL_SQUARES];
    private Image snakeHead;
    private Image snakeTail;
    private Image apple;

    // Timer
    private Timer timer;
    private static final int GAME_SPEED = 100;

    // Direction
    private Direction direction;

    // Game flag
    private boolean inGame = true;

    // Board Constructor
    public Board() {
        setBackground(new Color(0, 20, 39));
        setFocusable(true);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        addKeyListener(new SnakeMover());

        loadImages();
        initializeSnake();
        randomApple();
        startTimer();
    }

    private void startTimer() {
        timer = new Timer(GAME_SPEED, this);
        timer.start();
    }

    private void randomApple() {
        Random rand = new Random();
        apple_x = rand.nextInt(RANDOM_RANGE) * SQUARE_SIZE;
        apple_y = rand.nextInt(RANDOM_RANGE) * SQUARE_SIZE;
    }

    private void initializeSnake() {
        snakeLength = 3;
        direction = Direction.EAST;
        for (int i = 0; i < snakeLength; i++) {
            snake_x[i] = 50 - i * SQUARE_SIZE;
            snake_y[i] = 50;
        }
    }

    private void loadImages() {
        ImageIcon snakeHeadIcon = new ImageIcon(getClass().getResource("../icons/snek.png"));
        ImageIcon snakeTailIcon = new ImageIcon(getClass().getResource("../icons/tail.png"));
        ImageIcon appleIcon = new ImageIcon(getClass().getResource("../icons/apple.png"));
        snakeHead = snakeHeadIcon.getImage();
        snakeTail = snakeTailIcon.getImage();
        apple = appleIcon.getImage();
    }

    // Rendering
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {
            paintSnake(g);
            paintApple(g);
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String message = "Game Over, Press spacebar to restart";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metrics = getFontMetrics(small);

        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(message, (BOARD_WIDTH - metrics.stringWidth(message)) / 2, BOARD_HEIGHT / 2);
    }

    private void paintApple(Graphics g) {
        g.drawImage(apple, apple_x, apple_y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    private void paintSnake(Graphics g) {
        for(int i = 0; i < snakeLength; i++) {
            if(i == 0) {
                g.drawImage(snakeHead, snake_x[i], snake_y[i], this);
            } else {
                g.drawImage(snakeTail, snake_x[i], snake_y[i], this);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            moveSnake();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    private void checkCollision() {
        for(int i = 1; i < snakeLength; i++) {
            if(snake_x[0] == snake_x[i] && snake_y[0] == snake_y[i]) {
                inGame = false;
            }
        }

        if(snake_x[0] >= BOARD_WIDTH) {
            inGame = false;
        }
        if(snake_x[0] < 0) {
            inGame = false;
        }
        if(snake_y[0] >= BOARD_HEIGHT) {
            inGame = false;
        }
        if(snake_y[0] < 0) {
            inGame = false;
        }
    }

    private void checkApple() {
        if((snake_x[0] == apple_x) && (snake_y[0] == apple_y)) {
            snakeLength++;
            randomApple();
        }
    }

    private void moveSnake() {
        for (int i = snakeLength; i > 0; i--) {
            snake_x[i] = snake_x[i - 1];
            snake_y[i] = snake_y[i - 1];
        }

        switch (direction){
            case EAST: snake_x[0] += SQUARE_SIZE; break;
            case WEST: snake_x[0] -= SQUARE_SIZE; break;
            case NORTH: snake_y[0] -= SQUARE_SIZE; break;
            case SOUTH: snake_y[0] += SQUARE_SIZE; break;
        }
    }

    private class SnakeMover extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_UP && direction != Direction.SOUTH) {
                direction = Direction.NORTH;
            }
            if (key == KeyEvent.VK_DOWN && direction != Direction.NORTH) {
                direction = Direction.SOUTH;
            }
            if (key == KeyEvent.VK_RIGHT && direction != Direction.WEST) {
                direction = Direction.EAST;
            }
            if (key == KeyEvent.VK_LEFT && direction != Direction.EAST) {
                direction = Direction.WEST;
            }
            if (key == KeyEvent.VK_SPACE && !inGame) {
                initializeSnake();
                inGame = true;
            }
        }
    }
}


















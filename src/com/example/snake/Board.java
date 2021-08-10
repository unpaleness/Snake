package com.example.snake;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Board extends JPanel implements ActionListener {

    private final Point2D cellsAmount = new Point2D(20, 20);
    private final int CELL_SIZE = 32;
    private final int DELAY = 100;

    private Timer timer;
    private Snake snake;
    private Apple apple;
    private GameState gameState;

    public Board() {
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(cellsAmount.x * CELL_SIZE, cellsAmount.y * CELL_SIZE));

        addKeyListener(new TAdapter());

        snake = new Snake(new Point2D(cellsAmount.x / 2, cellsAmount.y / 2), CELL_SIZE);
        apple = new Apple(cellsAmount, CELL_SIZE);

        timer = new Timer(DELAY, this);
        timer.start();

        gameState = GameState.IN_PROGRESS;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        snake.draw(graphics, this);
        apple.draw(graphics, this);

        if (gameState == GameState.POST_BATTLE) {
            graphics.setColor(Color.white);
            graphics.drawString("Game Over", cellsAmount.x * CELL_SIZE / 2, cellsAmount.y * CELL_SIZE / 2);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (gameState) {
            case PRE_BATTLE:
                break;
            case IN_PROGRESS:
                if (!snake.tryMove(cellsAmount, apple.getLocation())) {
                    gameState = GameState.POST_BATTLE;
                }

                if (apple.getLocation().equals(snake.getHeadPos())) {
                    apple.replace();
                }

                repaint();

                break;
            case POST_BATTLE:
                break;
            default:
                break;
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    snake.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    snake.setDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    snake.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.setDirection(Direction.RIGHT);
                    break;
                default:
                    break;
            }
        }
    }
}

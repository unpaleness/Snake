package com.example.snake;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Board extends JPanel implements ActionListener {

    private final Point2D cellsAmount = new Point2D(20, 20);
    private final int CELL_SIZE = 32;
    private final int DELAY = 100;
    private final String APPLE_PATH = "src/resources/apple.png";

    private Point2D applePos = new Point2D(0, 0);

    private Image apple;
    private Timer timer;
    private Snake snake;
    private GameState gameState = GameState.PRE_BATTLE;

    public Board() {
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(cellsAmount.x * CELL_SIZE, cellsAmount.y * CELL_SIZE));

        addKeyListener(new TAdapter());
        loadImages();

        snake = new Snake(new Point2D(cellsAmount.x / 2, cellsAmount.y / 2), CELL_SIZE);
        placeApple();

        timer = new Timer(DELAY, this);
        timer.start();

        gameState = GameState.IN_PROGRESS;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawApple(graphics);

        snake.draw(graphics, this);

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
                if (!snake.tryMove(cellsAmount, applePos)) {
                    gameState = GameState.POST_BATTLE;
                }

                if (applePos.equals(snake.getHeadPos())) {
                    placeApple();
                }

                repaint();

                break;
            case POST_BATTLE:
                break;
            default:
                break;
        }
    }

    private void drawApple(Graphics graphics) {
        graphics.drawImage(apple, applePos.x * CELL_SIZE, applePos.y * CELL_SIZE, this);
    }

    private void placeApple() {
        applePos.x = (int) (Math.random() * cellsAmount.x);
        applePos.y = (int) (Math.random() * cellsAmount.y);
    }

    private void loadImages() {
        ImageIcon imageIconApple = new ImageIcon(APPLE_PATH);
        apple = imageIconApple.getImage().getScaledInstance(CELL_SIZE, CELL_SIZE, Image.SCALE_SMOOTH);
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

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

    private final int CELLS_AMOUNT_X = 20;
    private final int CELLS_AMOUNT_Y = 20;
    private final int CELL_SIZE = 32;
    private final int DELAY = 50;
    private final String APPLE_PATH = "src/resources/apple.png";

    private int appleNextCoordX = 0;
    private int appleNextCoordY = 0;

    private Image apple;
    private Timer timer;
    private Snake snake;

    public Board() {
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(CELLS_AMOUNT_X * CELL_SIZE, CELLS_AMOUNT_Y * CELL_SIZE));

        addKeyListener(new TAdapter());
        loadImages();

        snake = new Snake(new Point2D(CELLS_AMOUNT_X / 2, CELLS_AMOUNT_Y / 2), CELL_SIZE);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        placeApple();
        drawApple(graphics);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void drawApple(Graphics graphics) {
        graphics.drawImage(apple, appleNextCoordX, appleNextCoordY, this);
    }

    private void placeApple() {
        appleNextCoordX = CELL_SIZE * (int) (Math.random() * CELLS_AMOUNT_X);
        appleNextCoordY = CELL_SIZE * (int) (Math.random() * CELLS_AMOUNT_Y);
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
                    appleNextCoordX = 200;
                    appleNextCoordY = 100;
                    break;
                case KeyEvent.VK_DOWN:
                    appleNextCoordX = 100;
                    appleNextCoordY = 200;
                    break;
                default:
                    break;
            }
        }
    }
}

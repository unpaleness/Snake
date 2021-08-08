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

    private final int BOARD_SIZE_X = 400;
    private final int BOARD_SIZE_Y = 400;
    private final int DELAY = 50;

    private int appleNextCoordX = 0;
    private int appleNextCoordY = 40;

    private Image apple;
    private Timer timer;

    public Board() {
        init();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawImage(apple, appleNextCoordX, appleNextCoordY, this);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void init() {
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(BOARD_SIZE_X, BOARD_SIZE_Y));

        addKeyListener(new TAdapter());

        ImageIcon imageIconApple = new ImageIcon("src/resources/apple.png");
        apple = imageIconApple.getImage();

        timer = new Timer(DELAY, this);
        timer.start();
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

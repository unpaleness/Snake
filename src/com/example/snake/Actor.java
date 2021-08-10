package com.example.snake;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

abstract public class Actor {

    protected Image LoadImage(String path, int cellSize) {
        ImageIcon imageIcon = new ImageIcon(path);
        return imageIcon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
    }

    abstract public void draw(Graphics graphics, JPanel parent);
}

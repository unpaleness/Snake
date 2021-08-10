package com.example.snake;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

public class Apple extends Actor {

    private final String APPLE_PATH = "src/resources/apple.png";

    private Point2D location = new Point2D(0, 0);
    private Point2D cellsAmount;
    private Image image;
    private int cellSize;

    public Apple(Point2D newCellsAmount, int newCellSize) {
        cellsAmount = newCellsAmount;
        cellSize = newCellSize;

        image = LoadImage(APPLE_PATH, cellSize);

        replace();
    }

    public void replace() {
        location.x = (int) (Math.random() * cellsAmount.x);
        location.y = (int) (Math.random() * cellsAmount.y);
    }

    public Point2D getLocation() {
        return location;
    }

    public void draw(Graphics graphics, JPanel parent) {
        graphics.drawImage(image, location.x * cellSize, location.y * cellSize, parent);
    }
}

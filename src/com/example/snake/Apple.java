package com.example.snake;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;

public class Apple extends Actor {

    static private final String APPLE_PATH = "src/resources/apple.png";

    private Point2D location = new Point2D(0, 0);
    private final Point2D cellsAmount;
    private final Image image;
    private final int cellSize;

    public Apple(Point2D newCellsAmount, int newCellSize) {
        cellsAmount = newCellsAmount;
        cellSize = newCellSize;

        image = LoadImage(APPLE_PATH, cellSize);
    }

    public boolean replace(Snake snake) {
        if (snake.getLength() >= cellsAmount.x * cellsAmount.y) {
            return false;
        }
        Point2D newLocation = new Point2D();
        do {
            newLocation.x = (int) (Math.random() * cellsAmount.x);
            newLocation.y = (int) (Math.random() * cellsAmount.y);
        } while (snake.isOnPoint(newLocation));
        location = newLocation;
        return true;
    }

    public Point2D getLocation() {
        return location;
    }

    public void draw(Graphics graphics, JPanel parent) {
        graphics.drawImage(image, location.x * cellSize, location.y * cellSize, parent);
    }
}

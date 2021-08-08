package com.example.snake;

public class Point2D {
    public int x = 0;
    public int y = 0;

    public Point2D(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public Point2D clone() {
        return new Point2D(x, y);
    }

    public String toString() {
        return String.format("x: %d, y: %d", x, y);
    }
}

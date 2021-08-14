package com.example.snake;

public enum Direction {

    UP(0), LEFT(1), DOWN(2), RIGHT(3);
    private int value = 0;

    Direction(int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

    public boolean isCollinear(Direction other) {
        return (value + other.getValue()) % 2 == 0;
    }

    public Point2D nextPoint(Point2D point) {
        Point2D nextPoint = point.clone();
        switch (this) {
            case UP:
                --nextPoint.y;
                break;
            case DOWN:
                ++nextPoint.y;
                break;
            case LEFT:
                --nextPoint.x;
                break;
            case RIGHT:
                ++nextPoint.x;
                break;
            default:
                break;
        }
        return nextPoint;
    }
}

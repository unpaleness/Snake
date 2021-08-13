package com.example.snake;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Direction getOpposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        return UP;
    }

    public Point2D nextPoint(Point2D point) {
        Point2D nextPoint = (Point2D) point.clone();
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

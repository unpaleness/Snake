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
        return nextPointInner(point, 1);
    }

    private Point2D prevPoint(Point2D point) {
        return nextPointInner(point, -1);
    }

    private Point2D nextPointInner(Point2D point, int sign) {
        Point2D nextPoint = point.clone();
        switch (this) {
            case UP:
                nextPoint.y -= sign;
                break;
            case DOWN:
                nextPoint.y += sign;
                break;
            case LEFT:
                nextPoint.x -= sign;
                break;
            case RIGHT:
                nextPoint.x += sign;
                break;
            default:
                break;
        }
        return nextPoint;
    }
}

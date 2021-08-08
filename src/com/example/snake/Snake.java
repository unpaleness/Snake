package com.example.snake;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

public class Snake {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;

        public Point2D nextPoint(Point2D point) {
            return nextPointInner(point, 1);
        }

        private Point2D prevPoint(Point2D point) {
            return nextPointInner(point, -1);
        }

        private Point2D nextPointInner(Point2D point, int sign) {
            Point2D nextPoint = point;
            switch (this) {
                case UP:
                    point.y -= sign;
                    break;
                case DOWN:
                    point.y += sign;
                    break;
                case LEFT:
                    point.x -= sign;
                    break;
                case RIGHT:
                    point.x += sign;
                    break;
                default:
                    break;
            }
            return nextPoint;
        }
    }

    private final int INITIAL_LENGTH = 3;
    private final String SNAKE_HEAD_PATH = "src/resources/snake_head.png";
    private final String SNAKE_BODY_PATH = "src/resources/snake_body.png";

    private Vector<Point2D> joints;
    private Direction direction = Direction.UP;
    private Image imageHead;
    private Image imageBodyJoint;
    private int cellSize = 0;

    public Snake(Point2D headPos, int newCellSize) {
        joints.add(headPos);
        for (int i = 1; i < INITIAL_LENGTH; ++i) {
            joints.add(direction.nextPoint(joints.lastElement()));
        }

        cellSize = newCellSize;

        imageHead = LoadImage(SNAKE_HEAD_PATH, cellSize);
        imageBodyJoint = LoadImage(SNAKE_BODY_PATH, cellSize);
    }

    public void draw(Graphics graphics) {

    }

    private Image LoadImage(String path, int cellSize) {
        ImageIcon imageIcon = new ImageIcon(path);
        return imageIcon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
    }
}

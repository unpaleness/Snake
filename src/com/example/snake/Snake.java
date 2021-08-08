package com.example.snake;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

public class Snake {

    private final int INITIAL_LENGTH = 3;
    private final String SNAKE_HEAD_PATH = "src/resources/snake_head.png";
    private final String SNAKE_BODY_PATH = "src/resources/snake_body.png";

    private Vector<Point2D> joints;
    private Direction direction = Direction.UP;
    private Image imageHead;
    private Image imageBodyJoint;
    private int cellSize = 0;

    public Snake(Point2D headPos, int newCellSize) {
        joints = new Vector<Point2D>();
        joints.add(headPos);
        for (int i = 1; i < INITIAL_LENGTH; ++i) {
            joints.add(direction.nextPoint(joints.lastElement()));
        }

        joints.forEach(System.out::println);

        cellSize = newCellSize;

        imageHead = LoadImage(SNAKE_HEAD_PATH, cellSize);
        imageBodyJoint = LoadImage(SNAKE_BODY_PATH, cellSize);
    }

    public void draw(Graphics graphics, JPanel parent) {
        graphics.drawImage(imageHead, joints.lastElement().x * cellSize, joints.lastElement().y * cellSize, parent);
        for (int i = 0; i < joints.size() - 1; ++i) {
            graphics.drawImage(imageBodyJoint, joints.get(i).x * cellSize, joints.get(i).y * cellSize, parent);
        }
    }

    public void setDirection(Direction newDirection) {
        if (direction == newDirection.getOpposite()) {
            return;
        }
        direction = newDirection;
    }

    public boolean tryMove() {
        for (int i = 0; i < joints.size() - 1; ++i) {
            joints.set(i, joints.get(i + 1));
        }
        joints.set(joints.size() - 1, direction.nextPoint(joints.lastElement()));
        return true;
    }

    private Image LoadImage(String path, int cellSize) {
        ImageIcon imageIcon = new ImageIcon(path);
        return imageIcon.getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
    }
}

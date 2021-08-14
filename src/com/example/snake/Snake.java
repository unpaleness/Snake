package com.example.snake;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayDeque;
import java.util.Vector;

public class Snake extends Actor {

    static private final int INITIAL_LENGTH = 3;
    static private final String SNAKE_HEAD_PATH = "src/resources/snake_head.png";
    static private final String SNAKE_BODY_PATH = "src/resources/snake_body.png";

    private final Vector<Point2D> joints;
    private Direction currentDirection = Direction.UP;
    private final ArrayDeque<Direction> directionsQueue = new ArrayDeque<>();
    private final Image imageHead;
    private final Image imageBodyJoint;
    private final int cellSize;

    public Snake(Point2D headPos, int newCellSize) {
        joints = new Vector<>();
        joints.add(headPos);
        for (int i = 1; i < INITIAL_LENGTH; ++i) {
            joints.add(currentDirection.nextPoint(joints.lastElement()));
        }

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

    public void trySetNextDirection(Direction newDirection) {
        Direction lastDirection = directionsQueue.isEmpty() ? currentDirection : directionsQueue.peekLast();
        if (lastDirection.isCollinear(newDirection)) {
            return;
        }
        directionsQueue.offer(newDirection);
    }

    public boolean tryMove(Point2D boardSize, Point2D applePos) {
        if (!directionsQueue.isEmpty()) {
            currentDirection = directionsQueue.poll();
        }
        Point2D nextHeadPos = currentDirection.nextPoint(joints.lastElement());

        if (!isInBoard(nextHeadPos, boardSize) ||
                (joints.contains(nextHeadPos) && !nextHeadPos.equals(joints.firstElement()))) {
            return false;
        }

        if (nextHeadPos.equals(applePos)) {
            joints.add(nextHeadPos);
        } else {
            for (int i = 0; i < joints.size() - 1; ++i) {
                joints.set(i, joints.get(i + 1));
            }
            joints.set(joints.size() - 1, nextHeadPos);
        }
        return true;
    }

    public Point2D getHeadPos() { return joints.lastElement(); }

    public int getLength() { return joints.size(); }

    public boolean isOnPoint(Point2D point) { return joints.contains(point); }

    static private boolean isInBoard(Point2D point, Point2D boardSize) {
        return point.x >= 0 && point.x < boardSize.x && point.y >= 0 && point.y < boardSize.y;
    }
}

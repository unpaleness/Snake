package com.example.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

    private final Board board;

    public KeyHandler(Board newBoard) {
        board = newBoard;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                board.addNextDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                board.addNextDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                board.addNextDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                board.addNextDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_R:
                if (board.getGameState() == GameState.POST_MATCH) {
                    board.prepareForMatch();
                }
                break;
            default:
                break;
        }
    }
}

package com.example.snake;

import java.awt.EventQueue;

public class Snake {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Board ex = new Board();
            ex.setVisible(true);
        });
    }
}

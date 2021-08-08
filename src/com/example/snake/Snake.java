package com.example.snake;

import java.awt.*;
import javax.swing.JFrame;

public class Snake extends JFrame {

    public Snake() {
        setContentPane(new Board());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Snake ex = new Snake();
            ex.setVisible(true);
        });
    }
}

package com.lina.run;

import com.lina.game2048.Game2048;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        jFrame.setSize(450, 470);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(new BoardPanel(new Game2048()));

        jFrame.setVisible(true);
    }

}

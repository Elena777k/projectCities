package org.example;

import java.awt.*;

public class Cities {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GameLogic gameLogic = new GameLogic();
                GameGUI gameGUI = new GameGUI(gameLogic);
                gameGUI.startGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
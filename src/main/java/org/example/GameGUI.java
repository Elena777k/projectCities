package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
    private GameLogic gameLogic;
    private static JTextField playerAnswer= new JTextField();
    private static JTextField answer= new JTextField();



    public static JTextField getPlayerAnswer() {
        return playerAnswer;
    }

    public static void setPlayerAnswer(JTextField playerAnswer) {
        GameGUI.playerAnswer = playerAnswer;
    }

    public static JTextField getAnswer() {
        return answer;
    }

    public static void setAnswer(JTextField answer) {
        GameGUI.answer = answer;
    }

    public GameGUI(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void startGUI() {
        JFrame startFrame = new JFrame("Вітаємо");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(400,100);
        startFrame.setBackground(ModalColors.BLUE.getColor());
        startFrame.setLocationRelativeTo(null);

        JTextArea vitannya = new JTextArea("Вітаємо вас у грі дитинства і всіх розумників!");
        vitannya.setEditable(false);
        vitannya.setFont(new Font("Arial", Font.ITALIC,16));
        vitannya.setForeground(ModalColors.DARK_BLUE.getColor());
        vitannya.setBackground(ModalColors.LIGHT_BLUE.getColor());

        JButton startButton = new JButton("Старт");
        startButton.setBackground(ModalColors.LIGHT_YELLOW.getColor());
        startButton.setFont(new Font("Arial", Font.ITALIC,16));
        startButton.setForeground(ModalColors.PURPLE.getColor());

        startButton.addActionListener(e -> {
            startFrame.dispose();
            startGame();
        });

        JPanel panel = new JPanel();
        panel.add(vitannya, BorderLayout.SOUTH);
        panel.add(startButton, BorderLayout.CENTER);

        startFrame.getContentPane().add(panel).setBackground(new Color(204, 252, 255));
        startFrame.setVisible(true);
    }

    private void startGame() {
        JFrame gameFrame = new JFrame("Вітаємо");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(400,500);
        gameFrame.setLocationRelativeTo(null);

        playerAnswer.setBackground(ModalColors.LIGHT_YELLOW.getColor());
        playerAnswer.setForeground(ModalColors.PURPLE.getColor());

        answer.setEditable(false);
        answer.setBackground(ModalColors.LIGHT_YELLOW.getColor());
        answer.setForeground(ModalColors.PURPLE.getColor());


        JTextArea countAnswer = new JTextArea();
        countAnswer.setBackground(ModalColors.LIGHT_YELLOW.getColor());
        countAnswer.setForeground(ModalColors.PURPLE.getColor());
        countAnswer.setEditable(false);

        JButton nextButton = new JButton("\u0417\u0440\u043E\u0431\u0438\u0442\u0438 \u0445\u0456\u0434");
        nextButton.setSize(50,50);
        nextButton.setFont(new Font("Arial", Font.ITALIC,16));
        nextButton.setForeground(ModalColors.PURPLE.getColor());
        nextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String city = playerAnswer.getText();
                if(GameLogic.makeMove(city)){
                    countAnswer.append("Ви ввели: " + city + "\n");
                    answer.setText(GameLogic.getLastComputerCity());
                    countAnswer.append("Рахунок: " + GameLogic.getPlayerScore()+ " : " + gameLogic.getComputerScore() + "\n");
                }else {
                    JOptionPane.showMessageDialog(null, "Введіть правильне місто!");
                }


                playerAnswer.setText("");


            }
        });
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204, 252, 255));
        panel.setLayout(null);

        panel.add(playerAnswer).setBounds(25, 25, 150, 50);
        panel.add(answer).setBounds(25, 100, 150, 50);
        panel.add(new JScrollPane(countAnswer)).setBounds(200,25,150,125);
        panel.add(nextButton).setBounds(115, 200, 150, 50);

        gameFrame.getContentPane().add(panel);
        gameFrame.setVisible(true);

    }
    public static void endGame() {
        JFrame endFrame = new JFrame("Кінець гри");
        endFrame.setBackground(ModalColors.BLUE.getColor());
        endFrame.setForeground(ModalColors.PURPLE.getColor());
        endFrame.setSize(400, 100);
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JOptionPane.showMessageDialog(endFrame, "Гру завершено з рахунком " + GameLogic.getPlayerScore() + " : " + GameLogic.getComputerScore());

        System.exit(0);
    }

}

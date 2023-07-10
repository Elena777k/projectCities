package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cities {
    private List<String> availableCities = new ArrayList<>();
    private List<String> usedCities = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Cities game = new Cities();
                game.GameGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public Cities() {
        loadCitiesFromFile("C:\\Users\\karav\\Desktop\\homework\\project\\src\\main\\resources\\cit.txt");
        System.out.println(availableCities.get(1));
    }

    private void GameGUI(){
        JFrame startFrame = new JFrame("Вітаємо");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(400,100);
        startFrame.setBackground(Color.getHSBColor(0, 191, 255));
        startFrame.setLocationRelativeTo(null);


        JTextArea vitannya = new JTextArea("Вітаємо вас у грі дитинства і всіх розумників!");
        vitannya.setEditable(false);
        vitannya.setFont(new Font("Arial", Font.ITALIC,16));
        vitannya.setForeground(new Color(21, 7, 46));
        vitannya.setBackground(new Color(204, 252, 255));

        JButton startButton = new JButton("Старт");
        startButton.setBackground(new Color(253, 255, 150));
        startButton.setFont(new Font("Arial", Font.ITALIC,16));
        startButton.setForeground(new Color(154, 17, 217));
//        startButton.setSize(800,800);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.dispose();

                JFrame gameFrame = new JFrame("Міста");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(400,500);
                gameFrame.setLocationRelativeTo(null);
                JTextField playerAnswer = new JTextField();
                //JLabel inputLabel = new JLabel("Введіть назву міста");


                JTextArea answer = new JTextArea("Vidpovid");
                JTextArea countAnswer = new JTextArea();
                countAnswer.setEditable(false);

                JLabel computerOutput = new JLabel("Комп'ютер: ");


                JButton nextButton = new JButton("Зробити хід");
                nextButton.setSize(50,50);
                nextButton.setFont(new Font("Arial", Font.ITALIC,16));
                nextButton.setForeground(new Color(154, 17, 217));
                nextButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String PlayerInput = playerAnswer.getText();
                        if (giveUp(PlayerInput)){
                            //TODO: End game
                        } else {

                            if (isCorrectCity(PlayerInput)){
                                usedCities.add(PlayerInput.toLowerCase());
                                availableCities.remove(PlayerInput);

                                String computerCity = chooseCity(PlayerInput);
                                usedCities.add(computerCity.toLowerCase());
                                availableCities.remove(computerCity);
                                computerOutput.setText("компьютер: " + computerCity);

                            } else {
                                JOptionPane.showMessageDialog(null, "Невірне місто!");
                            }
                            playerAnswer.setText("");
                        }

                    }
                });

                JPanel panel = new JPanel();
                panel.setLayout(null);

                panel.add(playerAnswer).setBounds(25, 25, 150, 50);
                panel.add(answer).setBounds(25, 100, 150, 50);
                panel.add(new JScrollPane(countAnswer)).setBounds(200,25,150,125);
                panel.add(nextButton).setBounds(115, 200, 150, 50);
                panel.add(computerOutput).setBounds(25, 150, 300, 50);
                gameFrame.getContentPane().add(panel);
                gameFrame.setVisible(true);
            }
        });
        JPanel panel = new JPanel();
        panel.add(vitannya, BorderLayout.SOUTH);
        panel.add(startButton, BorderLayout.CENTER);

        startFrame.getContentPane().add(panel).setBackground(new Color(204, 252, 255));
        startFrame.setVisible(true);
    }

    private boolean isCorrectCity(String city) {
        String lowerCaseCity = city.toLowerCase();
        return availableCities.stream()
                .map(String::toLowerCase)
                .anyMatch(c -> c.equals(lowerCaseCity)) && (!usedCities.contains(lowerCaseCity));
    }

    private boolean giveUp(String city){
        return city.equalsIgnoreCase("здаюсь");
    }

    private String chooseCity(String PlayerCity){
        String lastLetter = PlayerCity.substring(PlayerCity.length() - 1);
        return availableCities.stream()
                .filter(c -> c.toLowerCase().startsWith(lastLetter.toLowerCase()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Більше нема міст"));
    }

    private void loadCitiesFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                availableCities.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
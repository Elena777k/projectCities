package src.main.java.org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Cities {

    private static JTextField playerAnswer= new JTextField();
    private static JTextField answer= new JTextField();



    public static JTextField getPlayerAnswer() {
        return playerAnswer;
    }

    public static void setPlayerAnswer(JTextField playerAnswer) {
        Cities.playerAnswer = playerAnswer;
    }

    public static JTextField getAnswer() {
        return answer;
    }

    public static void setAnswer(JTextField answer) {
        Cities.answer = answer;
    }

    public static void main(String[] args) {
        JFrame startFrame = new JFrame("\u0412\u0456\u0442\u0430\u0454\u043C\u043E");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(400,100);
        startFrame.setBackground(ModalColors.BLUE.getColor());
        startFrame.setLocationRelativeTo(null);



        JTextArea vitannya = new JTextArea("\u0412\u0456\u0442\u0430\u0454\u043C\u043E \u0412\u0430\u0441 \u0443 \u0433\u0440\u0456 \u0434\u0438\u0442\u0438\u043D\u0441\u0442\u0432\u0430 \u0456 \u0432\u0441\u0456\u0445 \u0440\u043E\u0437\u0443\u043C\u043D\u0438\u043A\u0456\u0432!");
        vitannya.setEditable(false);
        vitannya.setFont(new Font("Arial", Font.ITALIC,16));
        vitannya.setForeground(ModalColors.DARK_BLUE.getColor());
        vitannya.setBackground(ModalColors.LIGHT_BLUE.getColor());

        JButton startButton = new JButton("\u0421\u0442\u0430\u0440\u0442");
        startButton.setBackground(ModalColors.LIGHT_YELLOW.getColor());
        startButton.setFont(new Font("Arial", Font.ITALIC,16));
        startButton.setForeground(ModalColors.PURPLE.getColor());

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startFrame.dispose();

                JFrame gameFrame = new JFrame("\u041C\u0456\u0441\u0442\u0430");
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
                        countAnswer.append("\u0412\u0438 \u0432\u0432\u0435\u043B\u0438: " + city + "\n");
                        answer.setText(CityGame.makeMove());




                        countAnswer.append("\u0420\u0430\u0445\u0443\u043D\u043E\u043A: " + CityGame.getPlayerScore()+  "\n");
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
        });
        JPanel panel = new JPanel();
        panel.add(vitannya, BorderLayout.SOUTH);
        panel.add(startButton, BorderLayout.CENTER);

        startFrame.getContentPane().add(panel).setBackground(new Color(204, 252, 255));
        startFrame.setVisible(true);
    }


}
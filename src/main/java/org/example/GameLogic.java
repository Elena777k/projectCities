package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    protected static List<String> availableCities = new ArrayList<>();
    protected static List<String> usedCities = new ArrayList<>();
    private static int playerScore = 0;
    private static int computerScore = 0;
    private static String lastComputerCity;

    public static String getLastComputerCity() {
        return lastComputerCity;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static int getComputerScore() {
        return computerScore;
    }

    public GameLogic() {
        loadCitiesFromFile("src\\main\\resources\\cit.txt");
    }

    private static char getLastChar(String city) {
        if (!city.isEmpty()) {
            char lastChar = city.charAt(city.length() - 1);
            if ((lastChar == 'ь') || (lastChar == 'и')) {
                return city.charAt(city.length() - 2);
            } else {
                return lastChar;
            }
        }
        throw new IllegalArgumentException("Введіть місто!");
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


    public static boolean makeMove(String playerInput) {

        if (!playerInput.isEmpty()) {
            if (playerInput.equalsIgnoreCase("здаюсь")) {
                GameGUI.endGame();
            }

            if(usedCities.isEmpty() || (playerInput.toLowerCase().charAt(0) == getLastChar(lastComputerCity))) {
                if  (availableCities.contains(playerInput)) {
                    usedCities.add(playerInput);
                    availableCities.remove(playerInput);
                    playerScore++;
                } else {
                    return false;
                }
            } else {
                return false;
            }

            char lastChar = playerInput.charAt(playerInput.length() - 1);

            if ((lastChar == 'ь') || (lastChar == 'и')) {
                lastChar = playerInput.charAt(playerInput.length() - 2);
            }

            String computerCity = "";
            for (String city : availableCities) {
                if (!city.equals(playerInput) && city.toLowerCase().charAt(0) == lastChar) {
                    computerCity = city;
                    break;
                }
            }

            if (computerCity == null) {
                computerCity = "Здаюсь! Я не знаю відповіді.";
            } else {
                usedCities.add(computerCity);
                availableCities.remove(computerCity);
                computerScore++;
                lastComputerCity = computerCity;
            }

            GameGUI.getAnswer().setText(computerCity);
            GameGUI.getAnswer().repaint();

            return true;
        }
        return false;
    }

}

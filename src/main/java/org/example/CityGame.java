package org.example;

import java.io.*;
import java.util.ArrayList;


public class CityGame {

    private static int playerScore = 0;
    private static int computerScore = 0;
    private static ArrayList<String> cities = new ArrayList<>();
    private static ArrayList<String> usedCities = new ArrayList<>();

    public static void main(String[] args) {
        String fileName = "D:\\HomeWork\\projectCities\\projectCities\\src\\main\\java\\org\\example\\cit.txt";
        CityGame game = new CityGame();
        game.loadCitiesFromFile(fileName);
    }

    public static ArrayList<String> getCities() {
        return cities;
    }

    public static void setCities(ArrayList<String> cities) {
        CityGame.cities = cities;
    }

    public static ArrayList<String> getUsedCities() {
        return usedCities;
    }

    public static void setUsedCities(ArrayList<String> usedCities) {
        CityGame.usedCities = usedCities;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void setPlayerScore(int playerScore) {
        CityGame.playerScore = playerScore;
    }

    public static int getComputerScore() {
        return computerScore;
    }

    public static void setComputerScore(int computerScore) {
        CityGame.computerScore = computerScore;
    }

    private void loadCitiesFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String city;
            while ((city = bufferedReader.readLine()) != null) {
                cities.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String makeMove() {
        String playerCity = Cities.getPlayerAnswer().getText().trim();
        if (!playerCity.isEmpty()) {
            if (playerCity.equalsIgnoreCase("здаюсь")) {
                return "«даюсь! я не знаю в≥дпов≥д≥.";
            }
        }

        char lastChar = playerCity.charAt(playerCity.length() - 1);
        String nextCity = null;
        for (String city : cities) {
            if (!usedCities.contains(city) && city.charAt(0) == lastChar) {
                nextCity = city;
                usedCities.add(city);
                playerScore++;
                break;
            }
        }

        if (nextCity == null) {
            nextCity = "«даюсь! я не знаю в≥дпов≥д≥.";
        } else {
            char nextCityLastChar = nextCity.charAt(nextCity.length() - 1);
            if (nextCityLastChar == 'ь') {
                for (String city : cities) {
                    if (!usedCities.contains(city) && city.charAt(0) == nextCity.charAt(nextCity.length() - 2)) {
                        nextCity = city;
                        usedCities.add(city);
                        computerScore++;
                        break;
                    }
                }
            } else {
                usedCities.add(nextCity);
                computerScore++;
            }
        }

        return nextCity;
    }
}

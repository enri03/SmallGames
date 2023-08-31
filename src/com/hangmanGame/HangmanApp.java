package com.hangmanGame;

public class HangmanApp {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        gameService.start();
    }
}

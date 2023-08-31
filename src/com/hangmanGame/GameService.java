package com.hangmanGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameService {
    private static final String pathname = "src/com/hangmanGame/words.txt";

    public void start() {
        Scanner inputScanner = new Scanner(System.in);
        String randomWord = getRandomWord();
        char[] randomWordArray = randomWord.toCharArray();
        char[] userWordsInput = new char[randomWordArray.length];
        Arrays.fill(userWordsInput, '_');
        int numberOfChances = randomWordArray.length;
        System.out.println("starting the game");
        System.out.println("The length of the  word and number of chances : " + randomWordArray.length);
        System.out.println("The word: " + new String(userWordsInput));
        while (true) {
            System.out.println("------------------------");
            System.out.print("Please type in a letter : ");
            String inputLine = inputScanner.nextLine().toLowerCase();
            char letter = inputLine.charAt(0);
            boolean isGuessingCorrect = false;
            System.out.println("letter: " + letter);
            for (int i = 0; i < randomWordArray.length; i++) {
                if (randomWordArray[i] == letter) {
                    userWordsInput[i] = letter;
                    isGuessingCorrect = true;
                }
            }
            if (isGuessingCorrect) {
                System.out.println("That was a curect guess");
                if (isGameFinished(userWordsInput)) {
                    break;
                }
            } else {
                numberOfChances--;
                if (numberOfChances == 0) {
                    System.out.println("Sorry you run out of chances");
                    System.out.println("The word was : " + randomWord);
                    break;
                }

                System.out.println("Your number of chances is decreased to: " + numberOfChances);
                System.out.println("Please try another letter!");
            }
            System.out.println("The word: " + new String(userWordsInput));
        }
    }

    private boolean isGameFinished(char[] userWordsInput) {
        for (int i = 0; i < userWordsInput.length; i++) {
            if (userWordsInput[i] == '_') {
                return false;
            }
        }
        System.out.println("Congratulations!! You won the game !!");
        System.out.println("The word: " + new String(userWordsInput));
        return true;
    }

    private String getRandomWord() {
        List<String> words = getWords();
        Random randomNumber = new Random();
        int randomIdex = randomNumber.nextInt(words.size());
        return words.get(randomIdex);
    }

    private List<String> getWords() {
        List<String> words = new ArrayList<>();
        File wordsFile = new File(pathname);
        try (Scanner wordScanner = new Scanner(wordsFile)) {
            while (wordScanner.hasNextLine()) {
                words.add(wordScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return words;
    }
}

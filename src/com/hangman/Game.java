package com.hangman;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private final static int MAX_NUMBER_OF_ATTEMPTS = 6;
    private final UserAnswer userAnswer;
    private final PictureHangman pictureHangman;
    private final RandomWord randomWord;
    private final SecretWord secretWord;
    private int roundNumber = 0;

    public Game() {
        Path pathToPicture = Paths.get("./src/resources/hangman.txt");
        Path pathToWords = Paths.get("./src/resources/randomWord.txt");
        this.userAnswer = new UserAnswer();
        this.pictureHangman = new PictureHangman(pathToPicture);
        this.randomWord = new RandomWord(pathToWords);
        this.secretWord = new SecretWord(randomWord);
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void incRoundNumber() {
        roundNumber++;
    }

    public void start() {
        System.out.printf("Вам загадали слово из %d букв\n", secretWord.getSecretText().length());
        System.out.println(secretWord.getSecretText());
        int leftAttempts;
        while (!isOver()) {
            leftAttempts = MAX_NUMBER_OF_ATTEMPTS - roundNumber;
            System.out.printf("Оставшееся число попыток %d\n", leftAttempts);
            System.out.printf("Буквы которые вы ввели %s\n", secretWord.getOpenLetters());
            System.out.println("Введите букву");
            Character letter = userAnswer.getAnswer();
            if (secretWord.isOpenLetter(letter)) {
                System.out.printf("Вы уже угадывали эту букву: %s\n", letter);
                continue;
            }
            boolean isFind = secretWord.hasLetter(letter);
            if (isFind) {
                secretWord.openLettersInWord(letter);
            }
            secretWord.addOpenLetter(letter);
            printResultOfOneTurn(isFind);
            moveOnNextRound(isFind);
        }
        printResultTheGame();
    }

    public boolean isOver() {
        return isWin() || isLose();
    }

    public boolean isWin() {
        return !secretWord.getSecretText().contains("*");
    }

    public boolean isLose() {
        return MAX_NUMBER_OF_ATTEMPTS == (roundNumber);
    }

    public void printResultTheGame() {
        if (isLose()) {
            System.out.printf("Вы проиграли, загаданное слово было %s\n", randomWord.getText());
        } else if (isWin()) {
            System.out.printf("Вы выйграли, загаданное слово было %s\n", randomWord.getText());
        }
    }

    private void printResultOfOneTurn(boolean isFind) {
        if (!isFind) {
            System.out.println("Буква не найдена");
            printHangman();
        } else {
            System.out.println("Откройте букву");
            System.out.printf("Слово: %s\n", secretWord.getSecretText());
        }
    }

    private void moveOnNextRound(boolean isFind) {
        if (!isFind) {
            incRoundNumber();
        }
    }

    public void printHangman() {
        for (int indexRowPicture = 0; indexRowPicture < pictureHangman.getSize(); indexRowPicture++) {
            String linePicture = pictureHangman.getLines().get(indexRowPicture);
            List<String> rowPicture = new ArrayList<>(Arrays.asList(linePicture.split("")));
            printPartOfHangman(rowPicture, indexRowPicture);
            System.out.println();
        }
    }

    public void printPartOfHangman(List<String> rowPicture, int indexRowPicture) {
        for (int indexSymbol = 0; indexSymbol < rowPicture.size(); indexSymbol++) {
            if (indexSymbol <= this.getRoundNumber() || indexRowPicture <= this.getRoundNumber()) {
                System.out.print(rowPicture.get(indexSymbol));
            }
        }
    }
}


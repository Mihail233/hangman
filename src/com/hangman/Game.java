package com.hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private final static int MAX_NUMBER_OF_ATTEMPTS = 6;
    private final UserAnswer userAnswer = new UserAnswer();
    private final PictureHangman pictureHangman = new PictureHangman();
    private final SecretWord secretWord;
    private int madeAttempts = 0;

    public Game(SecretWord secretWord) {
        this.secretWord = secretWord;
    }

    public int getMadeAttempts() {
        return madeAttempts;
    }

    public void incMadeAttempts() {
        madeAttempts++;
    }

    public void start() {
        System.out.printf("Вам загадали слово из %d букв\n", secretWord.getSecretText().length());
        System.out.println(secretWord.getSecretText());
        while (!isOver()) {
            printCurrentStateOfGame();

            Character letter = userAnswer.getLetterFromUser();
            if (secretWord.isOpenLetter(letter)) {
                System.out.printf("Вы уже угадывали эту букву: %s\n", letter);
                continue;
            }
            secretWord.addOpenLetter(letter);

            boolean hasLetter = secretWord.hasLetter(letter);
            finishTurn(letter, hasLetter);
        }
        printResultTheGame();
    }

    private boolean isOver() {
        return isWin() || isLose();
    }

    private boolean isWin() {
        return !secretWord.getSecretText().contains("*");
    }

    private boolean isLose() {
        return MAX_NUMBER_OF_ATTEMPTS == (madeAttempts);
    }

    private void printCurrentStateOfGame() {
        int leftAttempts = MAX_NUMBER_OF_ATTEMPTS - madeAttempts;
        System.out.printf("Оставшееся число попыток %d\n", leftAttempts);
        System.out.printf("Буквы которые вы ввели %s\n", secretWord.getOpenLetters());
    }

    private void finishTurn(Character letter, boolean isFind) {
        if (isFind) {
            secretWord.openLettersInWord(letter);
            System.out.println("Откройте букву");
            System.out.printf("Слово: %s\n", secretWord.getSecretText());
        } else {
            System.out.println("Буква не найдена");
            incMadeAttempts();
            printHangman();
        }
    }

    private void printHangman() {
        for (int indexRowPicture = 0; indexRowPicture < pictureHangman.getSize(); indexRowPicture++) {
            String linePicture = pictureHangman.getLines().get(indexRowPicture);
            List<String> rowPicture = new ArrayList<>(Arrays.asList(linePicture.split("")));
            printPartOfHangman(rowPicture, indexRowPicture);
            System.out.println();
        }
    }

    private void printPartOfHangman(List<String> rowPicture, int indexRowPicture) {
        for (int indexSymbol = 0; indexSymbol < rowPicture.size(); indexSymbol++) {
            if (indexSymbol <= this.getMadeAttempts() || indexRowPicture <= this.getMadeAttempts()) {
                System.out.print(rowPicture.get(indexSymbol));
            }
        }
    }

    private void printResultTheGame() {
        if (isLose()) {
            System.out.printf("Вы проиграли, загаданное слово было %s\n", secretWord.getRandomWord().getText());
        } else if (isWin()) {
            System.out.printf("Вы выйграли, загаданное слово было %s\n", secretWord.getRandomWord().getText());
        }
    }
}


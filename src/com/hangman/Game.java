package com.hangman;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    private PictureHangman pictureHangman;
    private RandomWord randomWord;
    private Scanner scanner;
    private boolean stateGame = false;

    private int MAX_NUMBER_OF_ATTEMPTS = 6;
    private int roundNumber = 0;

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber() {
        roundNumber++;
    }

    public void startGame() throws IOException {
        System.out.println("Желаете начать игру, введите S - начать, E - выйти");
        getStateGame();
        checkStateGame();
    }

    public void getStateGame() {
        scanner = new Scanner(System.in);
        while (true) {
            String answer = getAnswer();
            boolean isRight = checkAnswer(answer);
            if (isRight) {
                return;
            }
        }
    }

    public void checkStateGame() throws IOException {
        if (!stateGame) {
            System.exit(0);
        } else {
            pictureHangman = new PictureHangman();
            randomWord = new RandomWord();
        }
    }

    public String getAnswer() {
        String answer;
        while (true) {
            answer = scanner.nextLine().toLowerCase();
            int SIZE_ONE_LETTER = 1;
            if (answer.length() == SIZE_ONE_LETTER) {
                break;
            }
            System.out.println("Введите букву, а не enter или предложение");
        }
        return answer;
    }

    public boolean checkAnswer(String answer) {
        boolean isRight = true;
        switch (answer) {
            case "s":
                stateGame = true;
                System.out.println("Начало игры");
                break;
            case "e":
                break;
            default:
                isRight = false;
                System.out.println("Введите снова");
        }
        return isRight;
    }

    public void guessWord() throws IOException {
        while (MAX_NUMBER_OF_ATTEMPTS > 0 && randomWord.getHiddenWord().contains("*")) {
            System.out.println("Оставшееся число попыток " + MAX_NUMBER_OF_ATTEMPTS);
            System.out.println("Буквы которые вы ввели " + randomWord.getOpenLetters());
            System.out.println("Введите букву");
            String letter = getAnswer();
            if (randomWord.getOpenLetters().contains(letter)) {
                System.out.println("Вы уже угадывали эту букву: " + letter);
                continue;
            }
            boolean isFind = randomWord.checkLetter(letter);
            randomWord.addOpenLetter(letter);
            showResultOfOneTurn(isFind);
        }
    }

    public void printResultTheGame() {
        if (MAX_NUMBER_OF_ATTEMPTS < 1) {
            System.out.println("Вы проиграли, загаданное слово было " + randomWord.getRandomWord());
        } else {
            System.out.println("Вы выйграли, загаданное слово было " + randomWord.getRandomWord());
        }
    }

    private void showResultOfOneTurn(boolean isFind) {
        if (!isFind) {
            MAX_NUMBER_OF_ATTEMPTS--;
            pictureHangman.writeHangman(this);
            System.out.println("Буква не найдена ");
        } else {
            System.out.println("Откройте букву ");
            System.out.println("Слово: " + randomWord.getHiddenWord());
        }
    }
}


package com.hangman;

import java.util.Scanner;

public class UserAnswer {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Character START = 'Н';
    private final static Character EXIT = 'В';
    private final static int LENGTH_ONE_LETTER = 1;

    public boolean isWantToStartGame() {
        System.out.printf("Желаете начать игру, введите %s - начать, %s - выйти\n", START, EXIT);
        while (true) {
            Character letter = getAnswer();
            if (isExitFromGame(letter)) {
                System.out.println("Игры была завершена");
                System.exit(0);
            }
            if (isStartGame(letter)) {
                System.out.println("Начало игры");
                return true;
            }
            System.out.printf("Введите снова, необходимо ввести %s для старта, %s для выхода\n", START, EXIT);
        }
    }

    public Character getAnswer() {
        String answer;
        while (true) {
            answer = SCANNER.nextLine().toUpperCase();
            if (isLetter(answer)) {
                Character letter = answer.charAt(0);
                if (isLocatedInTheAlphabet(letter)) {
                    return letter;
                }
            }
            System.out.println("Буква не распознана, необходимо использовать кириллицу");
        }
    }

    public boolean isLetter(String answer) {
        return answer.length() == LENGTH_ONE_LETTER;
    }

    public boolean isLocatedInTheAlphabet(Character letter) {
        return letter >= 'А' && letter <= 'Я' || letter.equals('Ё');
    }

    public boolean isExitFromGame(Character letter) {
        return letter.equals(EXIT);
    }

    public boolean isStartGame(Character letter) {
        return letter.equals(START);
    }
}

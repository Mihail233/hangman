package com.hangman;

import java.util.ArrayList;
import java.util.List;

public class SecretWord {
    private final List<Character> openLetters = new ArrayList<>();
    private String secretText;
    private final RandomWord randomWord;

    public SecretWord(RandomWord randomWord) {
        this.randomWord = randomWord;
        setSecretText(randomWord);
    }

    public String getSecretText() {
        return secretText;
    }

    private void setSecretText(RandomWord randomWord) {
        secretText = "*".repeat(randomWord.getText().length());
    }

    public void addOpenLetter(Character letter) {
        openLetters.add(letter);
    }

    public List<Character> getOpenLetters() {
        return openLetters;
    }

    public boolean isOpenLetter(Character letter) {
        return this.getOpenLetters().contains(letter);
    }

    public boolean hasLetter(Character letter) {
        return randomWord.getText().contains(letter.toString());
    }

    public void openLettersInWord(Character letter) {
        StringBuilder newWord = new StringBuilder(secretText);
        for (int index = 0; index < randomWord.getText().length(); index++) {
            if (randomWord.getLetterFromWord(index).equals(letter)) {
                newWord.setCharAt(index, letter);
            }
        }
        secretText = newWord.toString();
    }
}

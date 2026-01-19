package com.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RandomWord {
    private final List<String> openLetters = new ArrayList<>();
    private String randomWord;
    private String hiddenForm;

    public RandomWord() throws IOException {
        this.randomWord = getRandomWordFromFile();
    }

    public String getHiddenWord() {
        return hiddenForm;
    }

    public List<String> getOpenLetters() {
        return openLetters;
    }

    public String getRandomWord() {
        return randomWord;
    }

    public void addOpenLetter(String letter) {
        openLetters.add(letter);
    }

    public String getRandomWordFromFile() throws IOException {
        List<String> listRandomWord = Files.readAllLines(Paths.get("./src/resources/randomWord.txt"));
        randomWord = chooseRandomWord(listRandomWord);
        System.out.println("Вам загадали слово из " + randomWord.length() + " букв");
        setHideForm(randomWord);
        System.out.println(getHiddenWord());
        return randomWord;
    }

    private String chooseRandomWord(List<String> listRandomWord) {
        int randomIndex = (int)(Math.random() * listRandomWord.size());
        return listRandomWord.get(randomIndex).toLowerCase();
    }

    private void setHideForm(String randomWord) {
        hiddenForm = "*".repeat(randomWord.length());
    }

    public boolean checkLetter(String letter) {
        boolean isFind = false;
        if (randomWord.contains(letter)) {
            isFind = true;
            replaceAllLetters(letter);
        }
        return isFind;
    }

    private void replaceAllLetters(String letter) {
        char firstLetter = letter.charAt(0);
        StringBuilder newWord = new StringBuilder(hiddenForm);
        for (int index = 0; index < randomWord.length(); index++) {
            if (randomWord.charAt(index) == firstLetter) {
                newWord.setCharAt(index, firstLetter);
            }
        }
        hiddenForm = newWord.toString();
    }

    @Override
    public String toString() {
        return String.join(", ", getOpenLetters());
    }

}

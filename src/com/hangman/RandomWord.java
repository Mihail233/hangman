package com.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RandomWord {
    private final String text;
    private List<String> words;

    public RandomWord(Path pathToWords) {
        readWordsFromFile(pathToWords);
        this.text = chooseRandomWord();
    }

    public String getText() {
        return text;
    }

    public Character getLetterFromWord(int index) {
        return this.getText().charAt(index);
    }

    public void readWordsFromFile(Path pathToWords) {
        try {
            this.words = Files.readAllLines(pathToWords);
        } catch (IOException e) {
            throw new FileReadingError(String.format("Проблема с файлом %s", pathToWords));
        }
    }

    private String chooseRandomWord() {
        assert words != null;
        int randomIndex = (int) (Math.random() * words.size());
        return words.get(randomIndex).toUpperCase();
    }
}

package com.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RandomWord {
    private final static Path PATH_TO_WORDS = Paths.get("./src/resources/randomWord.txt");
    private String text;
    private List<String> words;

    public RandomWord() {
        readWordsFromFile();
        chooseRandomWord();
    }

    public String getText() {
        return text;
    }

    private void readWordsFromFile() {
        try {
            this.words = Files.readAllLines(RandomWord.PATH_TO_WORDS);
        } catch (IOException e) {
            throw new FileReadingError(String.format("Проблема с файлом %s", RandomWord.PATH_TO_WORDS));
        }
    }

    private void chooseRandomWord() {
        assert words != null;
        int randomIndex = (int) (Math.random() * words.size());
        this.text = words.get(randomIndex).toUpperCase();
    }

    public Character getLetterFromWord(int index) {
        return this.getText().charAt(index);
    }
}

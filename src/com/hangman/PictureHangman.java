package com.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PictureHangman {
    private final static Path PATH_TO_PICTURE = Paths.get("./src/resources/hangman.txt");

    private List<String> lines;

    public PictureHangman() {
        readHangmanPictureFromFile();
    }

    public int getSize() {
        return lines.size();
    }
    public List<String> getLines() {
        return lines;
    }

    private void readHangmanPictureFromFile() {
        try {
            this.lines = Files.readAllLines(PictureHangman.PATH_TO_PICTURE);
        } catch (IOException e) {
            throw new FileReadingError(String.format("Проблема с файлом %s", PictureHangman.PATH_TO_PICTURE));
        }
    }
}

package com.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PictureHangman {
    private List<String> lines;

    public PictureHangman(Path pathToHangman) {
        readHangmanPictureFromFile(pathToHangman);
    }

    public int getSize() {
        return lines.size();
    }
    public List<String> getLines() {
        return lines;
    }

    public void readHangmanPictureFromFile(Path pathToPicture) {
        try {
            this.lines = Files.readAllLines(pathToPicture);
        } catch (IOException e) {
            throw new FileReadingError(String.format("Проблема с файлом %s", pathToPicture));
        }
    }
}

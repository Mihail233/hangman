package com.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PictureHangman {
    private final List<String> pictureHangman;

    public PictureHangman() throws IOException {
        this.pictureHangman = getHangmanPictureFromFile();
    }

    public List<String> getHangmanPictureFromFile() throws IOException {
        return Files.readAllLines(Paths.get("./src/resources/hangman.txt"));
    }

    public void writeHangman(Game game) {
        for (int indexRowPicture = 0; indexRowPicture < pictureHangman.size(); indexRowPicture++) {
            List<String> rowPicture = new ArrayList<>(Arrays.asList(pictureHangman.get(indexRowPicture).split("")));
            for (int indexSymbol = 0; indexSymbol < rowPicture.size(); indexSymbol++) {
                if (indexSymbol <= game.getRoundNumber() || indexRowPicture <= game.getRoundNumber()) {
                    System.out.print(rowPicture.get(indexSymbol));
                }
            }
            System.out.println();
        }
        game.setRoundNumber();
    }
}

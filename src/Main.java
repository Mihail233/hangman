import com.hangman.Game;
import com.hangman.RandomWord;
import com.hangman.SecretWord;
import com.hangman.UserAnswer;

public class Main {
    public static void main(String[] args) {
        UserAnswer userAnswer = new UserAnswer();
        while (true) {
            if (userAnswer.isWantToStartGame()) {
                RandomWord randomWord = new RandomWord();
                SecretWord secretWord = new SecretWord(randomWord);
                Game game = new Game(secretWord);
                game.start();
            }
        }
    }
}
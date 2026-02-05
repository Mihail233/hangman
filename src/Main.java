import com.hangman.Game;
import com.hangman.UserAnswer;

public class Main {
    public static void main(String[] args) {
        UserAnswer userAnswer = new UserAnswer();
        while (true) {
            if (userAnswer.isWantToStartGame()) {
                Game game = new Game();
                game.start();
            }
        }
    }
}
import com.hangman.Game;

void main() {
    try {
        while (true) {
            Game game = new Game();
            game.startGame();
            game.guessWord();
            game.printResultTheGame();
        }
    } catch (IOException err) {
        System.out.println("Возникла ошибка " + err);
    } finally {
        System.out.println("Игра завершилась");
    }
}

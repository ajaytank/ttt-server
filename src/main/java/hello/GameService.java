package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game getGameState(@PathVariable("gameId") String gameId) {
        return gameRepository.findById(gameId).get();
    }

    public Game postGameState(@RequestBody Game game) {
        gameRepository.save(game);
        return game;
    }

    public Game putGameState(String gameId, Game game) {

        Game game1 = gameRepository.findById(gameId).get();
        String state = game.state;

        if (state != null) {
            char[] stateChar = state.toCharArray();


            String verdict = checkGame(stateChar);

            if (verdict.equals("WIN")) {
                game1.status = "COMPLETED";
                game1.result = game1.activePlayer + "WINS";
            } else if (verdict.equals("DRAW")) {
                game1.status = "COMPLETED";
                game1.result = "GAME IS DRAWN";
            }

            game1.state = state;
            game1.activePlayer = 1 + Math.abs(1 - (game1.activePlayer - 1));
        }

        if (game.status != null && game.status.equals("ABANDONED")) {
            game1.status = game.status;
        }
        gameRepository.save(game1);
        return game1;
    }

    private String checkGame(char[] stateChar) {

        int count = 0;
        for (int i = 0; i < stateChar.length; i++) {
            if (stateChar[i] == ' ') {
                count++;
            }
        }

        if (count == 0) {
            return "DRAW";
        }

        char[] c = stateChar;

        if (c[0] != ' ' && c[0] == c[1] && c[1] == c[2]) {
            return "WIN";
        }

        if (c[3] != ' ' && c[3] == c[4] && c[4] == c[5]) {
            return "WIN";
        }

        if (c[6] != ' ' && c[6] == c[7] && c[7] == c[8]) {
            return "WIN";
        }

        if (c[0] != ' ' && c[0] == c[3] && c[3] == c[6]) {
            return "WIN";
        }

        if (c[1] != ' ' && c[1] == c[4] && c[4] == c[7]) {
            return "WIN";
        }

        if (c[2] != ' ' && c[2] == c[5] && c[5] == c[8]) {
            return "WIN";
        }

        if (c[0] != ' ' && c[0] == c[4] && c[4] == c[8]) {
            return "WIN";
        }

        if (c[2] != ' ' && c[2] == c[4] && c[4] == c[6]) {
            return "WIN";
        }

        return "CONTINUE";

    }
}

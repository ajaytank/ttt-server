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

        char[] stateChar = state.toCharArray();

        int count = 0;
        for (int i = 0; i < stateChar.length; i++) {
            if (stateChar[i] == ' ') {
                count++;
            }
        }

        if (count == 0) {
            game1.status = "COMPLETED";
        }

        game1.state = state;
        game1.activePlayer = 1 + Math.abs(1 - (game1.activePlayer - 1));
        gameRepository.save(game1);
        return game1;
    }
}

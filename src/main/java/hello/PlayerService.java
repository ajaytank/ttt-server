package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerService playerService;

    public Player getPlayer(String playerId) {
        return playerRepository.findById(playerId).get();
    }

    public Player createNewPlayerPlayer() {
        Player player = new Player();

        playerRepository.save(player);
        return player;
    }

    public Player playGame(String playerId) {
        Player player = playerService.getPlayer(playerId);
        String currentGameId = player.gameId;
        Game currentGame = null;
        if (currentGameId != null) {
            Optional<Game> gameOptional = gameRepository.findById(currentGameId);
            if (gameOptional.isPresent() && gameOptional.get().status.equals("ACTIVE")) {
                currentGame = gameOptional.get();
            }
        }

        if (currentGame != null) {
            return player;
        } else {
            List<Game> gameList = gameRepository.findByStatus("WAITING");
            if (gameList != null && !gameList.isEmpty()) {
                Game game = gameList.get(0);
                game.playerTwoId = playerId;
                game.status = "ACTIVE";
                gameRepository.save(game);
                player.setGameId(game.id);
                playerRepository.save(player);
            } else {
                Game newGame = new Game();
                newGame.state = "         ";
                newGame.status = "WAITING";
                newGame.playerOneId = playerId;
                newGame.activePlayer = 1;
                gameRepository.save(newGame);
                player.setGameId(newGame.id);
                playerRepository.save(player);
            }
            return player;
        }
    }

    public boolean login(String credentials) {
        try {
            String[] cred = credentials.split(":");
            String username = cred[0];
            String password = cred[1];

            Player player = playerRepository.findByUsername(username);
            if (player.getPassword().equals(password)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}

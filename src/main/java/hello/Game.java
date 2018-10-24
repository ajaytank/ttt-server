package hello;

import org.springframework.data.annotation.Id;

public class Game {

    @Id
    public String id;

    public String playerOneId;
    public String playerTwoId;
    public String state;
    public String status;
    public Integer activePlayer;

    public Game() {
    }

    public Game(String id, String playerOneId, String playerTwoId, String state, String status, Integer activePlayer) {
        this.id = id;
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.state = state;
        this.status = status;
        this.activePlayer = activePlayer;
    }
}

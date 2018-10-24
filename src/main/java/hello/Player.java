package hello;

import org.springframework.data.annotation.Id;

public class Player {

    @Id
    public String id;

    public String gameId;

    public String username;

    public String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Player(String id, String gameId, String username, String password) {
        this.id = id;
        this.gameId = gameId;
        this.username = username;
        this.password = password;
    }

    public Player() {
    }
}
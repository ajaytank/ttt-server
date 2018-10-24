package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/login/{credentials}")
    public Player login(@PathVariable("credentials") String credentials) {
        return playerService.login(credentials);
    }

    @GetMapping("/{playerId}")
    @ResponseBody
    public Player getPlayer(@PathVariable("playerId") String playerId) {
        return playerService.getPlayer(playerId);

    }

    @PostMapping("/")
    @ResponseBody
    public Player createNewPlayerPlayer() {
        return playerService.createNewPlayerPlayer();
    }

    @GetMapping("/playGame/{playerId}")
    @ResponseBody
    public Player playGame(@PathVariable("playerId") String playerId) {
        return playerService.playGame(playerId);
    }
}
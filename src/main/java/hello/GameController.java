package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/{gameId}")
    @ResponseBody
    public Game getGameState(@PathVariable("gameId") String gameId) {
        return gameService.getGameState(gameId);
    }

    @PostMapping(value = "/")
    @ResponseBody
    public Game postGameState(@RequestBody Game game) {
        gameService.postGameState(game);
        return game;
    }

    @PutMapping(value = "/{gameId}")
    @ResponseBody
    public Game putGameState(@PathVariable("gameId") String gameId, @RequestBody Game game) {
        return gameService.putGameState(gameId, game);
    }

    /*@RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    public void options() {
        System.out.println("safsadfs");
    }*/
}


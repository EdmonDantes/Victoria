package ru.teamname.projectname.controller.api.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.teamname.projectname.entity.account.AccountToken;
import ru.teamname.projectname.entity.game.Lobby;
import ru.teamname.projectname.repository.account.AccountTokenRepository;
import ru.teamname.projectname.service.GameService;

@RestController
@RequestMapping(path = "/api/lobby")
public class LobbyController {

    @Autowired
    private GameService gameService;

    @Autowired
    private AccountTokenRepository accountTokenRepository;

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public @ResponseBody Lobby getLobby(@RequestParam Integer id, @RequestParam String token) {
        AccountToken aToken = accountTokenRepository.getByToken(token);
        if (aToken != null && aToken.getId() != null && aToken.getId() > 0)
            return gameService.getLobby(id);
        return new Lobby();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody Lobby createLobby(@RequestBody Lobby lobby, @RequestParam String token) {
        AccountToken aToken = accountTokenRepository.getByToken(token);
        if (aToken != null && aToken.getId() != null && aToken.getId() > 0)
            return gameService.addLobby(lobby, aToken.getAccount());
        return new Lobby();
    }

    @RequestMapping(path = "/entry", method = RequestMethod.GET)
    public @ResponseBody Boolean entryToLobby(@RequestParam Integer id, @RequestParam String token) {
        AccountToken aToken = accountTokenRepository.getByToken(token);
        if (aToken != null && aToken.getId() != null && aToken.getId() > 0)
            return gameService.addPlayer(id, aToken.getAccount());
        return false;
    }

}

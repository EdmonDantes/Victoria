package ru.teamname.projectname.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.teamname.projectname.entity.chat.Chat;
import ru.teamname.projectname.entity.chat.ChatMessage;
import ru.teamname.projectname.entity.game.Lobby;
import ru.teamname.projectname.entity.game.Player;
import ru.teamname.projectname.repository.chat.ChatManager;
import ru.teamname.projectname.repository.game.GameManager;
import ru.teamname.projectname.repository.game.LobbyRepository;
import ru.teamname.projectname.repository.game.PlayerRepository;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private PlayerRepository players;

    @Autowired
    private LobbyRepository lobby0;

    @Autowired
    private ChatManager chatManager;

    @Autowired
    private GameManager gameManager;

    @RequestMapping(path = "/1", method = RequestMethod.POST,  consumes = "application/json")
    public @ResponseBody Player addPlayer(@RequestBody Player player) {
        return players.save(player);
    }

    @RequestMapping(path = "/2", method = RequestMethod.POST,  consumes = "application/json")
    public @ResponseBody Lobby addLobby(@RequestBody Lobby lobby) {
        return gameManager.saveLobby(lobby);
    }

    @RequestMapping(path = "/2get")
    public @ResponseBody Iterable<Lobby> getAllLobby() {
        return lobby0.findAll();
    }

    @RequestMapping(path = "/3", method = RequestMethod.POST,  consumes = "application/json")
    public @ResponseBody Chat addChat(@RequestBody Chat chat1) {
        return null;//chatManager.saveChat(chat1);
    }

    @RequestMapping(path = "/4", method = RequestMethod.POST,  consumes = "application/json")
    public @ResponseBody ChatMessage addMessage(@RequestBody ChatMessage message) {
        return null;//chatManager.saveMessage(message);
    }

}

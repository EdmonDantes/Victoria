package ru.teamname.projectname.repository.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.teamname.projectname.entity.chat.Chat;
import ru.teamname.projectname.entity.chat.ChatMessage;
import ru.teamname.projectname.entity.game.Game;
import ru.teamname.projectname.entity.game.Lobby;
import ru.teamname.projectname.entity.game.Player;
import ru.teamname.projectname.entity.game.PlayerStatus;
import ru.teamname.projectname.repository.chat.ChatManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameManager {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ChatManager chatManager;

    @Autowired
    PlayerStatusRepository playerStatusRepository;

    @Autowired
    LobbyRepository lobbyRepository;

    public Player savePlayer(Player player) {
        try {
            return playerRepository.save(player);
        } catch (Exception e) {
            return null;
        }
    }

    public Chat createChat(){
        return chatManager.createChat();
    }

    public ChatMessage createMessage(Player player, String message) {
        ChatMessage result = new ChatMessage();
        result.setPlayerId(player);
        result.setMessage(message);
        return result;
    }

    public Chat addMessageToChat(Chat chat, ChatMessage message) {
        return chatManager.addMessageToChat(chat, message);
    }

    public Lobby addMessageToChat(Lobby lobby, ChatMessage message) {
        if (lobby != null && lobby.getId() != null) {
            if (lobby.getChat() == null) {
                Optional<Lobby> tmp = lobbyRepository.findById(lobby.getId());
                if (tmp.isPresent())
                    lobby = tmp.get();
                else
                    return lobby;
            }

            lobby.setChat(addMessageToChat(lobby.getChat(), message));
        }
        return lobby;
    }


    public PlayerStatus createPlayerStatus(Player player, Lobby lobby) {
        if (player == null || player.getId() == null) return null;

        PlayerStatus result = new PlayerStatus(player, 0, lobby);
        try {
            return playerStatusRepository.save(result);
        }catch (Exception e) {
            Optional<PlayerStatus> tmp = playerStatusRepository.findByPlayerId(player);
            if (tmp.isPresent())
                //TODO: create id when player gaming in online game or not lobby
                if (tmp.get().getStatus() > -1 && (tmp.get().getLobbyId() == null || !tmp.get().getLobbyId().getOnline()) && (tmp.get().getGameId() == null || !tmp.get().getGameId().getOnline()))
                    return tmp.get();

            return new PlayerStatus();
        }
    }

    public Lobby saveLobby(Lobby lobby) {
        Optional<Player> master = playerRepository.findById(lobby.getMasterId().getId());
        if (master.isPresent()){
            lobby.setMasterId(master.get());
            List<PlayerStatus> statuses = lobby.getPlayerStatus();
            lobby.setPlayerStatus(new ArrayList<>());
            lobby.setChat(createChat());
            Lobby result = lobbyRepository.save(lobby);
            for (PlayerStatus status : statuses) {
                PlayerStatus tmp = createPlayerStatus(status.getPlayerId(), result);
                if (tmp != null)
                    result.getPlayerStatus().add(tmp);
                else {
                    lobbyRepository.setOffline(result.getId());
                    return new Lobby();
                }
            }

            PlayerStatus masterStatus = createPlayerStatus(master.get(), result);
            if (masterStatus == null || masterStatus.getId() == null) {
                lobbyRepository.setOffline(result.getId());
                return new Lobby();
            } else
                result.getPlayerStatus().add(masterStatus);

            return result;
        }
        return lobby;
    }

    public Game createGame(Lobby lobby) {
        if (lobby != null && lobby.getId() != null && lobby.getPlayerStatus() != null && lobby.getPlayerStatus().size() > 1) {
            Game result = new Game();
        }
        return new Game();
    }
}

package ru.liveproduction.victoria.server;

import com.google.gson.JsonObject;
import javafx.util.Pair;
import ru.liveproduction.victoria.api.Game;
import ru.liveproduction.victoria.api.Lobby;
import ru.liveproduction.victoria.api.User;

import javax.jws.soap.SOAPBinding;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;

public class GameManager {

    private volatile int gameId = 0;
    PackManager packManager;
    UserManager userManager = new UserManager();

    {
        try {
            packManager = new PackManager("/home/dantes/IdeaProjects/Victoria/pack.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    List<Game> games;
    List<Lobby> lobbes;

    public GameManager(){
        games = new ArrayList<>();
        lobbes = new ArrayList<>();
    }

    public int createLobby(String name, int maxPlayers, int timeRead, int timeWrite, int id_park, boolean easy, boolean middle, boolean hard) {
        if (lobbes.stream().anyMatch((obj) -> obj.getName().equals(name))) return -1;
        int tmp = gameId++;
        lobbes.add(new Lobby(name, id_park, maxPlayers, timeWrite, timeRead, easy, middle, hard));
        return tmp;
    }

    public List<Lobby> getLoddyes(String name) {

        List<Lobby> lobbies = new ArrayList<>();
        for (Lobby obj : lobbes) {
            if (obj.getName().replace(" ","").toLowerCase().equals(name.replace(" ","").toLowerCase()))
                lobbies.add(obj);
        }

        return lobbies;
    }

    public boolean addUserToLobby(String name, User user) {
        for (Lobby lobby : lobbes) {
            if (lobby.getName().equals(name)) {
                return lobby.addUserToLobby(user) >= 0;
            }
        }

        return false;
    }

    public boolean deleteUserFromLobby(String name, User user) {
        for (Lobby lobby : lobbes) {
            if (lobby.getName().equals(name)) {
                lobby.exitFromLobby(user);
                return true;
            }
        }
        return false;
    }


    public User createUserForRequest() {
        return userManager.createUserForRequest();
    }

    public User getUserFromId(int userId) throws SQLException {
        return userManager.getUserFromId(userId);
    }

    public JsonObject getPackManager(){
        return packManager.toJson();
    }

    public boolean checkMarket(Integer valueOf, int packId) {
        return userManager.checkMarket(valueOf, packId);
    }

    public boolean deleteLobby(String name, User userFromId) {
        for (Lobby lobby : lobbes) {
            if (lobby.getName().equals(name) && lobby.getPlayers().get(0).equals(userFromId)) {
                lobbes.remove(lobby);
                return true;
            }
        }
        return false;
    }

    public void userReady(User user) {
        for (Lobby lobby : lobbes) {
            if (lobby.readyUser(user)) {
                games.add(lobby.startGame(packManager));
                lobbes.remove(lobby);
            }
        }
    }

    public void userUnReady(User user) {
        for (Lobby lobby : lobbes) {
            for (Map.Entry<User, Boolean> tmp : lobby.getPlayers()){
                if (tmp.getKey().equals(user)){
                    tmp.setValue(false);
                    return;
                }
            }
        }
    }
}

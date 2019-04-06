package ru.liveproduction.victoria.server;

import javafx.util.Pair;
import ru.liveproduction.victoria.api.Game;
import ru.liveproduction.victoria.api.Lobby;
import ru.liveproduction.victoria.api.User;

import javax.jws.soap.SOAPBinding;
import java.io.FileNotFoundException;
import java.util.*;

public class GameManager {

    private volatile int gameId = 0;
    PackManager packManager;

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

    public int createLobby(String name, int maxPlayers, int timeRead, int timeWrite, int id_park) {
        int tmp = gameId++;
        lobbes.add(new Lobby(name, id_park, maxPlayers, timeWrite, timeRead));
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
            if (lobby.getName().equals(name))
                if (lobby.addUserToLobby(user) == 0){
                    games.add(lobby.startGame(manager));
                    lobbes.remove(lobby);
                    return true;
                }
        }

        return false;
    }



}

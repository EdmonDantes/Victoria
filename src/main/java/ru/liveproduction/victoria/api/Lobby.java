package ru.liveproduction.victoria.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.liveproduction.victoria.server.GameManager;
import ru.liveproduction.victoria.server.PackManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lobby {
    String name;
    int packId;
    int maxPlayers;
    int timeRead;
    int timeWrite;
    List<User> players;

    public Lobby(String name, int packId, int maxPlayers, int timeWrite, int timeRead) {
        this.name = name;
        this.packId = packId;
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>(maxPlayers);
        this.timeRead = timeRead;
        this.timeWrite = timeWrite;
    }

    public int addUserToLobby(User user) {
        if (players.size() < maxPlayers) {
            players.add(user);
        }
        return maxPlayers - players.size();
    }

    public void exitFromLobby(User user){
        players.remove(user);
    }

    public String getName() {
        return name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getPackId() {
        return packId;
    }

    public int getTimeRead() {
        return timeRead;
    }

    public int getTimeWrite() {
        return timeWrite;
    }

    public List<User> getPlayers() {
        return players;
    }

    public Game startGame(PackManager packManager){
        Pack pack = packManager.getPackWithId(packId);
        return new Game(players, pack.getQuestion(pack.getCategories(new Random().nextInt(2) + 5), 7), timeRead, timeWrite);
    }

    public JsonObject toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("packId", packId);
        jsonObject.addProperty("maxPlayers", maxPlayers);
        jsonObject.addProperty("timeReader", timeRead);
        jsonObject.addProperty("timeWriter", timeWrite);
        return jsonObject;
    }

}

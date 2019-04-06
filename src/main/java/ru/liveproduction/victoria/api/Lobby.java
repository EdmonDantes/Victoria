package ru.liveproduction.victoria.api;

import com.google.gson.JsonObject;
import javafx.util.Pair;
import ru.liveproduction.victoria.server.PackManager;

import java.util.*;

public class Lobby {

    String name;
    int packId;
    int maxPlayers;
    int timeRead;
    int timeWrite;
    List<Map.Entry<User, Boolean>> players;
    Map<User, Map.Entry<Integer, Long>> actions;
    boolean easy, middle, hard;

    public Lobby(String name, int packId, int maxPlayers, int timeWrite, int timeRead, boolean easy, boolean middle, boolean hard) {
        this.name = name;
        this.packId = packId;
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>(maxPlayers);
        this.actions = new HashMap<>();
        this.timeRead = timeRead;
        this.timeWrite = timeWrite;
        this.easy = easy;
        this.middle = middle;
        this.hard = hard;
    }

    public int addUserToLobby(User user) {
        if (players.size() < maxPlayers) {
            players.add(new AbstractMap.SimpleEntry<>(user, false));
        }
        actions.put(user, new AbstractMap.SimpleEntry<>(4, System.currentTimeMillis()));
        return maxPlayers - players.size();
    }

    public void exitFromLobby(User user){
        players.removeIf((obj) -> obj.getKey().equals(user));
        actions.put(user, new AbstractMap.SimpleEntry<>(5, System.currentTimeMillis()));
    }

    public boolean readyUser(User user) {
        for (Map.Entry<User, Boolean> pair : players) {
            if (pair.getKey().equals(user)){
                pair.setValue(true);
                actions.put(user, new AbstractMap.SimpleEntry<>(7, System.currentTimeMillis()));
                return true;
            }
        }
        return false;
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

    public List<Map.Entry<User, Boolean>> getPlayers() {
        return players;
    }

    public Game startGame(PackManager packManager){
        Pack pack = packManager.getPackWithId(packId).complex(easy, middle, hard);

        List<User> users = new ArrayList<>();

        for (Map.Entry<User, Boolean> pair : players) {
            users.add(pair.getKey());
            actions.put(pair.getKey(), new AbstractMap.SimpleEntry<>(10, System.currentTimeMillis()));
            if (!pair.getValue()) return null;
        }

        return new Game(users, pack.getQuestion(pack.getCategories(new Random().nextInt(maxPlayers / 2) + maxPlayers), 7), timeRead, timeWrite);
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

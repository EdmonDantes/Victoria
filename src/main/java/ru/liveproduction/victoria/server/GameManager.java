package ru.liveproduction.victoria.server;

import com.google.gson.JsonObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import ru.liveproduction.victoria.api.Action;
import ru.liveproduction.victoria.api.Game;
import ru.liveproduction.victoria.api.Lobby;
import ru.liveproduction.victoria.api.User;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;

public class GameManager {

    public interface AnswerToUser{
        void send(User user, Action action);
    }

    private volatile int gameId = 0;
    PackManager packManager;
    UserManager userManager;
    List<Game> games;
    List<Lobby> lobbes;

    AnswerToUser ans;

    public GameManager(AnswerToUser ans, String url, String user, String password, String pack){
        try {
            packManager = new PackManager(pack);
            userManager = new UserManager(url, user, password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.ans = ans;
        games = new ArrayList<>();
        lobbes = new ArrayList<>();
    }

    public int createLobby(String name, int maxPlayers, int timeRead, int timeWrite, int id_park, boolean easy, boolean middle, boolean hard, int userId) {
        if (lobbes.stream().anyMatch((obj) -> obj.getName().equals(name))) return -1;
        int tmp = gameId++;
        Lobby lobby = new Lobby(name, id_park, maxPlayers, timeWrite, timeRead, easy, middle, hard, userId);
        try {
            lobby.addUserToLobby(userManager.getUserFromId(userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lobbes.add(lobby);
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
                if (lobby.addUserToLobby(user) >= 0) {
                    for (Map.Entry<User, Boolean> tmp : lobby.getPlayers()){
                        Action act = new Action(Action.Type.EnterToLobby, user);
                        if (!tmp.getKey().equals(user))
                            this.ans.send(tmp.getKey(), act);
                    }
                } else
                    return false;
            }
        }

        return false;
    }

    public boolean deleteUserFromLobby(String name, User user) {
        for (Lobby lobby : lobbes) {
            if (lobby.getName().equals(name)) {
                if (!lobby.exitFromLobby(user)){
                    lobbes.remove(lobby);
                }
                for (Map.Entry<User, Boolean> tmp : lobby.getPlayers()) {
                    Action act = new Action(Action.Type.ExitFromLobby, user);
                    if (!tmp.getKey().equals(user))
                        this.ans.send(tmp.getKey(), act);
                }
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
            if (lobby.getName().equals(name) && lobby.getOwner() == userFromId.getId()) {
                lobbes.remove(lobby);
                for (Map.Entry<User, Boolean> tmp : lobby.getPlayers()) {
                    Action act = new Action(Action.Type.DeleteLobby, userFromId);
                    if (!tmp.getKey().equals(userFromId))
                        this.ans.send(tmp.getKey(), act);
                }
                return true;
            }
        }
        return false;
    }

    public void userReady(User user) {
        for (Lobby lobby : lobbes) {
            if (lobby.readyUser(user)) {
                Game game = lobby.startGame(packManager);
                games.add(game);
                Action act = new Action(Action.Type.StartGame, user, game.toJSON().toString());
                Action act0 = new Action(Action.Type.Ready, user);
                for (Map.Entry<User, Boolean> tmp : lobby.getPlayers()) {
                    if (!tmp.getKey().equals(user)) {
                        this.ans.send(tmp.getKey(), act0);
                        this.ans.send(tmp.getKey(), act);
                    }
                }

                lobbes.remove(lobby);
            }
        }
    }

    public void userUnReady(User user) {
        for (Lobby lobby : lobbes) {
            for (Map.Entry<User, Boolean> tmp : lobby.getPlayers()){
                if (tmp.getKey().equals(user)){
                    tmp.setValue(false);
                    Action act = new Action(Action.Type.Unready, user);
                    for (Map.Entry<User, Boolean> tmp0 : lobby.getPlayers()) {
                        if (!tmp0.getKey().equals(user))
                            this.ans.send(tmp0.getKey(), act);
                    }
                    return;
                }
            }
        }
    }

    public void choseCell(User user, int x, int y) {
        for (Game game : games) {
            if (game.getStarting().equals(user)) {
                game.getQuestion(ans, x, y, user);
                return;
            }
        }
    }

    public void answer(User user, String answer) {
        for (Game game : games) {

            for (Map.Entry<User, Integer> map : game.getPlayers()) {
                if (map.getKey().equals(user)) {
                    game.answer(user, answer);
                }
            }
        }
    }
}

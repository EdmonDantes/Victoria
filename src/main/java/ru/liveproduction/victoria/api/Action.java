package ru.liveproduction.victoria.api;

import com.google.gson.JsonObject;

public class Action {
    public enum Type {

        //Pre lobby
        EnterToLobby,
        ExitFromLobby,

        //In lobby
        DeleteLobby,
        Ready,
        Unready,

        //After lobby
        StartGame,

        ChoseCell,
        Question,
        Answer,
        WaisteTime

    }
    private Type type;
    private User user;
    private long time;
    String additionalData;

    public Action(Type type, User user) {
        this.type = type;
        this.user = user;
        this.time = System.currentTimeMillis();
        this.additionalData = "";
    }

    public Action(Type type, User user, String data) {
        this(type, user);
        this.additionalData = data;
    }

    public User getUser() {
        return user;
    }

    public long getTime() {
        return time;
    }

    public Type getType() {
        return type;
    }

    public JsonObject toJson(){
        JsonObject result = new JsonObject();
        result.addProperty("type", type.toString());
        result.add("user", user.toJson(false));
        result.addProperty("time", time);
        result.addProperty("data", additionalData);
        return result;
    }
}

package ru.liveproduction.victoria.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.*;
import javafx.util.Pair;
import jdk.nashorn.internal.parser.JSONParser;
import ru.liveproduction.victoria.api.Game;
import ru.liveproduction.victoria.api.Lobby;
import ru.liveproduction.victoria.api.User;
import sun.plugin2.main.server.HeartbeatThread;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VictoriaServer {
    private HttpServer server;
    List<Pair<Long, Pair<User, HttpExchange>>> connectionUser = new ArrayList<>();
    private GameManager gameManager;
    private UserManager userManager;
    private PackManager packManager;

    private static String convert(InputStream inputStream) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }

        return stringBuilder.toString();
    }

    private JsonObject createResponse(String obj) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("response", obj);
        return jsonObject;
    }

    private JsonObject createResponse(Number obj) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("response", obj);
        return jsonObject;
    }

    private JsonObject createResponse(JsonElement json) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("response", json);
        return jsonObject;
    }

    private void writeToStream(HttpExchange http, String str) {
        byte[] bytes = str.toString().getBytes();
        try {
            http.sendResponseHeaders(200, bytes.length);
            http.getResponseBody().write(bytes);
            http.getResponseBody().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JsonObject createError(int code, String message){
        JsonObject error = new JsonObject();
        error.addProperty("code", code);
        error.addProperty("message", message);
        return error;
    }

    private class Auth extends Authenticator{

        @Override
        public Result authenticate(HttpExchange httpExchange) {
            try {
                JsonObject object = new JsonParser().parse(convert(httpExchange.getRequestBody())).getAsJsonObject();

                if (object.has("typeRequest") && object.get("typeRequest").getAsInt() == 2) {
                    User user = userManager.createUserForRequest();
                    return new Success(new HttpPrincipal(object.toString(), String.valueOf(user.getId())));
                }

                if (object.has("data")) {
                    JsonObject data = object.getAsJsonObject("data");
                    if (data.has("userId") && data.has("token")) {
                        if (userManager.getUserFromId(data.get("userId").getAsInt()).getIdentify().equals(data.get("token").getAsString()))
                            return new Success(new HttpPrincipal(object.toString(), data.get("userId").getAsString()));
                    } else return new Failure(401);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                return new Failure(400);
            }

            return new Failure(400);
        }
    }

    private static volatile Map<Integer, HttpExchange> longConnections = new TreeMap<>();

    public VictoriaServer() throws FileNotFoundException {
        gameManager = new GameManager();
        userManager = new UserManager();
        packManager = new PackManager("/home/dantes/IdeaProjects/Victoria/pack.json");
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080),0);
            server.createContext("/", httpExchange -> {
                JsonObject object = new JsonParser().parse(httpExchange.getPrincipal().getName()).getAsJsonObject();

                longConnections.put(Integer.valueOf(httpExchange.getPrincipal().getRealm()), httpExchange);

                if (object.has("typeRequest") && object.has("data")) {
                    JsonObject data = object.getAsJsonObject("data");
                    switch (object.get("typeRequest").getAsInt()) {

                        //Create lobby
                        case 0:
                            if (data.has("name") && data.has("countPlayers") && data.has("packId") && data.has("complex") && data.has("timeRead") && data.has("timeWrite") && data.has("userId") && data.has("token")) {
                                if (userManager.checkMarket(Integer.valueOf(httpExchange.getPrincipal().getRealm()), data.get("packId").getAsInt())) {
                                    int id = gameManager.createLobby(data.get("name").getAsString(), data.get("countPlayers").getAsInt(), data.get("timeRead").getAsInt(), data.get("timeWrite").getAsInt(), data.get("packId").getAsInt());
                                    writeToStream(httpExchange, createResponse(id).toString());
                                } else {
                                    writeToStream(httpExchange, createError(3, "You must buy this pack").toString());
                                }
                            } else {
                                writeToStream(httpExchange, createError(2, "You haven`t enouht arguments").toString());
                            }
                            break;

                            // Get packs
                        case 1:
                            writeToStream(httpExchange, createResponse(packManager.toJson()).toString());
                            break;
                            // Register
                        case 2:
                            try {
                                writeToStream(httpExchange, String.valueOf(userManager.getUserFromId(Integer.valueOf(httpExchange.getPrincipal().getRealm())).toJson(true).toString()));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;

                            // Get lobby
                        case 3:
                            JsonArray array = new JsonArray();
                            List<Lobby> collections = gameManager.getLoddyes(data.has("nameLobby") ? data.get("nameLobby").getAsString() : "");
                            for (Lobby lobby : collections) {
                                array.add(lobby.toJSON());
                            }
                            writeToStream(httpExchange, createResponse(array).toString());
                            break;

                            // Add to lobby
                        case 4:
                            if (data.has("idLobby")){

                            }
                    }
                } else {
                    if (httpExchange.getPrincipal() != null && httpExchange.getPrincipal().getRealm() != null) {
                        try {
                            writeToStream(httpExchange, String.valueOf(userManager.getUserFromId(Integer.valueOf(httpExchange.getPrincipal().getRealm())).toJson(true).toString()));
                        } catch (SQLException e) {
                            writeToStream(httpExchange, createError(500, "Server error").toString());
                            e.printStackTrace();
                        }
                    }
                    writeToStream(httpExchange, createError(1, "You didn`t use special format").toString());
                }
            }).setAuthenticator(new Auth());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

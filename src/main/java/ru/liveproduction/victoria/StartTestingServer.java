/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 05.04.19 22:45
*/

package ru.liveproduction.victoria;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.liveproduction.victoria.server.VictoriaServer;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class StartTestingServer {

    public static void main(String ...args) throws ClassNotFoundException, SQLException, IOException {
        JsonObject pref = new JsonParser().parse(new FileReader(args[0])).getAsJsonObject();
        new VictoriaServer(pref.get("url").getAsString(), pref.get("user").getAsString(), pref.get("password").getAsString(), pref.get("pack").getAsString());
    }
}

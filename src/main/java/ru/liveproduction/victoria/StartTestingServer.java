/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 05.04.19 22:45
*/

package ru.liveproduction.victoria;

import com.sun.net.httpserver.HttpServer;
import ru.liveproduction.victoria.api.Pack;
import ru.liveproduction.victoria.mysql.MysqlConnection;
import ru.liveproduction.victoria.mysql.MysqlManager;
import ru.liveproduction.victoria.server.PackManager;
import ru.liveproduction.victoria.server.VictoriaServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class StartTestingServer {

    public static void main(String ...args) throws ClassNotFoundException, SQLException, IOException {
        new VictoriaServer();
    }
}

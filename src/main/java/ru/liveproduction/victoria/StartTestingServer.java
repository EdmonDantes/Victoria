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

    private static final String url = "jdbc:mysql://localhost:3306/victoriadb?useUnicode=true&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "1111qazwsxqweasd";
    private final static MysqlManager manager = new MysqlManager(15, url, user, password);

    public static void main(String ...args) throws ClassNotFoundException, SQLException, IOException {

        MysqlManager.printResult.exec(manager.addTaskAsyncGet("select * from `users` where `id` = \'" + 1 + "\';"), 0, null);
        new VictoriaServer();
    }
}

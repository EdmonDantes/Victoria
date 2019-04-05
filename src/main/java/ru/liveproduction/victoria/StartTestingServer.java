/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 05.04.19 22:45
*/

package ru.liveproduction.victoria;

import ru.liveproduction.victoria.mysql.MysqlManager;

import java.sql.SQLException;

public class StartTestingServer {
    public static void main(String ...args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/victoriadb?useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String password = "1111qazwsxqweasd";

        MysqlManager manager = new MysqlManager(10, url, user, password);
    }
}

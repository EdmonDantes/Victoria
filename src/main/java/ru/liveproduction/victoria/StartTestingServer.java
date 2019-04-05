/*
Copyright © 2019 Ilya Loginov. All rights reserved. 
Please email dantes2104@gmail.com if you would like permission to do something with the contents of this repository

Created dantes on 05.04.19 22:45
*/

package ru.liveproduction.victoria;

import ru.liveproduction.victoria.mysql.MysqlManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StartTestingServer {
    public static void main(String ...args) throws ClassNotFoundException, SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/testApi?useUnicode=true&characterEncoding=UTF-8&user=root&password=1111qazwsxqweasd";

        MysqlManager.MysqlConnection connection = new MysqlManager.MysqlConnection("jdbc:mysql://localhost:3306/testApi?useUnicode=true&characterEncoding=UTF-8", "root", "1111qazwsxqweasd");
        long tmp = connection.addTask("show tables");

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ResultSet rs = connection.getResult(tmp);

        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }
}

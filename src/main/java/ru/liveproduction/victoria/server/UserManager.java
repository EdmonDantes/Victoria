package ru.liveproduction.victoria.server;

import ru.liveproduction.victoria.mysql.MysqlManager;

public class UserManager {
    private static final String url = "jdbc:mysql://localhost:3306/victoriadb?useUnicode=true&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "1111qazwsxqweasd";
    public final static MysqlManager manager = new MysqlManager(15, url, user, password);
    static {
        manager.addTaskAsyncUpdate("create table if not exists `users`(`id` integer auto_increment, `name` varchar(17), `indentify` varchar(128), `date` integer, primary key (`id`));");
    }
}

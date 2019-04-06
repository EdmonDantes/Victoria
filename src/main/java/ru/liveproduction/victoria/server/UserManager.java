package ru.liveproduction.victoria.server;

import ru.liveproduction.victoria.api.User;
import ru.liveproduction.victoria.mysql.MysqlManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class UserManager {
    private static final String url = "jdbc:mysql://localhost:3306/victoriadb?useUnicode=true&characterEncoding=UTF-8";
    private static final String user = "root";
    private static final String password = "1111qazwsxqweasd";
    private final static MysqlManager manager = new MysqlManager(15, url, user, password);
    private final static Random rand = new Random();
    static {
        manager.addTaskAsyncUpdate("create table if not exists `users`(`id` integer unsigned auto_increment, `name` varchar(17) binary, `indentify` varchar(128), `date` integer unsigned, primary key (`id`));");
        manager.addTaskAsyncUpdate("create table if not exists 'market`(`id_user` integer unsigned, `id_pack` integer unsigned, primary key(id_user));");
    }

    public UserManager(){}

    private String createName(){
        return "player" + rand.nextInt();
    }

    public User createUserForRequest(String identify){
        String name = createName();
        long date = System.currentTimeMillis();
        int id = manager.addTaskAsyncUpdate("insert into `users`(`name`,`indentify`, `date`) values(\'" + name + "\',\'" + identify + "\',\'" + date + "\');");
        return new User(id, name, identify, date);
    }

    public User getUserFromId(int id) throws SQLException {
        ResultSet rs = manager.addTaskAsyncGet("select * from `users` where `id` = \'" + id + "\';");
        if (rs != null)
            return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getLong(4));
        return null;
    }

    public boolean checkMarket(int id_user, int id_pack) {
        //TODO: create method to check buy thing in google play market
        return true;
    }
}
